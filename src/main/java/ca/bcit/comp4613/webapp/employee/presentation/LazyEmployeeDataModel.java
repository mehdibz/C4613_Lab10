package ca.bcit.comp4613.webapp.employee.presentation;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ca.bcit.comp4613.webapp.employee.domain.Employee;
import ca.bcit.comp4613.webapp.employee.service.EmployeeServicesImpl;

public class LazyEmployeeDataModel extends LazyDataModel<Employee> {

	private static final long serialVersionUID = 6076761899491989837L;

	List<Employee> employees;

	public LazyEmployeeDataModel() throws ClassNotFoundException, SQLException {
		employees = new EmployeeServicesImpl().getEmployeeList();
	}

	
	
	/**
	 * Since PrimeFaces 3.3, you'd need to explicitly set the lazy attribute of the repeating component to true in order to enable the support for LazyDataModel.
	 * <p:dataTable ... lazy="true">
	 * https://www.primefaces.org/showcase/ui/data/datatable/lazy.xhtml
	 */
	@Override
	public List<Employee> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<Employee> data = new ArrayList<>();

		for (Employee e : employees) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = e.getClass().getDeclaredField(filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field.get(e));

						if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
							match = true;
						} else {
							match = false;
							break;
						}
					} catch (Exception exp) {
						match = false;
					}
				}
			}

			if (match) {
				data.add(e);
			}
		}
		//sort
        if(sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }
 
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return new ArrayList<>(data.subList(first, first + pageSize));
            }
            catch(IndexOutOfBoundsException e) {
                return new ArrayList<>(data.subList(first, first + (dataSize % pageSize)));
            }
        }
        else {
            return new ArrayList<>(data);
        }
    }
	
	@Override
	public Object getRowKey(Employee emp) {
		return emp.getId();
	}
	
}
