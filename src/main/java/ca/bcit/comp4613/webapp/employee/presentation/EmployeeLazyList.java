package ca.bcit.comp4613.webapp.employee.presentation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ca.bcit.comp4613.webapp.employee.domain.Employee;
import ca.bcit.comp4613.webapp.employee.service.EmployeeServices;
import ca.bcit.comp4613.webapp.employee.service.EmployeeServicesImpl;

public class EmployeeLazyList extends LazyDataModel<Employee> {

	private static final long serialVersionUID = 7351702956095530271L;
	private EmployeeServices employeeServices;
	private List<Employee> employees;
	
	public EmployeeLazyList() throws ClassNotFoundException, SQLException {
		employeeServices = new EmployeeServicesImpl();
		employees = employeeServices.getEmployeeList();
	}
	
	@Override
	public List<Employee> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		
		if ( sortField!=null ){
			Collections.sort(employees, new LazySorter(sortField, sortOrder));
		}
		
		if ( filters!=null && filters.size()>0 ){
			if ( filters.keySet().contains("firstName" )){
				List<Employee> localCopyOfEmployees =  employees.stream().collect(Collectors.toList());
				localCopyOfEmployees = filterByFirstName( localCopyOfEmployees, filters.get("firstName") );
				setRowCount(employees.size());
				setPageSize(pageSize);
				return localCopyOfEmployees;
			}
		}
		
		setRowCount(employees.size());
		setPageSize(pageSize);
		
		if ( employees.size()>pageSize ){
			try{
				return new ArrayList<>(employees.subList(first, first+pageSize));
			}catch (IndexOutOfBoundsException e ){
				return new ArrayList<>(employees.subList(first,  first+(employees.size()%pageSize)));
			}
		}
		else{
			return new ArrayList<>(employees);
		}
	}
	
	private List<Employee> filterByFirstName(List<Employee> list, Object object) {
		String firstName = (String)object;
		
		return employees.stream()
			.filter( e -> e.getFirstName().contains(firstName) )
			.collect(Collectors.toList());
	}
	
	@Override
	public Object getRowKey(Employee employee) {
		return employee.getId();
	}

	@Override
	public Employee getRowData(String employeeId) {
		Integer id = Integer.valueOf(employeeId);
		for (Employee employee : employees) {
			if (id.equals(employee.getId())) {
				return employee;
			}
		}
		return null;
	}
	
	public List<Employee> getEmployees(){
		return employees;
	}
	
	@Override
	public int getRowCount() {
		return employees.size();
	}
	
	
	@Override
	//Overrode this method because I want my row numbers start from 1
	public int getRowIndex() {
		return super.getRowIndex()+1;
	}
}
