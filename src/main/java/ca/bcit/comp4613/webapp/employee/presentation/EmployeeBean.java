package ca.bcit.comp4613.webapp.employee.presentation;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import ca.bcit.comp4613.webapp.employee.domain.Employee;

@ManagedBean
@ViewScoped
public class EmployeeBean implements Serializable 
{
	private static final long serialVersionUID = -6878343285554228481L;
	private Employee employee;
	private static final Logger logger = Logger.getLogger(EmployeeBean.class);
	
	private LazyDataModel<Employee> employees;

	@PostConstruct
	public void init(){
		try {
			employees = new EmployeeLazyList();
		} catch (ClassNotFoundException | SQLException e) {
			logger.error("Couldn't get the list of employees " , e );
		}
	}
	
	public LazyDataModel<Employee> getAllEmployees() throws ClassNotFoundException, SQLException{
		
		return employees;
	}


	public Employee getEmployee() {
		if ( employee==null ){
			employee = new Employee();
		}
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	public void onDateSelect(SelectEvent event ){
		List<Employee> list = (List) employees.getWrappedData();
		var data = new ArrayList<Employee>();
		
		Date date = (Date) event.getObject();
		for ( Employee e: list) {
			if ( e.getDob().after(date)) {
				data.add(e);
			}
		}
		
		System.out.println(">>>> Filtered List by date: " + data );
	}
	
	
	public List<String> completeText(String query) {
		/*List<String> results = new ArrayList<String>();
        for (Employee e: employees.getEmployees() ){
        	if ( e.getFirstName().contains(query)){
        		results.add(e.getFirstName());
        	}
        	
        }
        */
		List<Employee> list =(List) employees.getWrappedData();
        return list.stream()
        			.filter( e -> e.getFirstName().contains(query) )
        			.map(e -> e.getFirstName() )
        			.collect(Collectors.toList());
        
    }
	
	public void logout( ActionEvent e ) throws IOException{
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.invalidateSession();
	    ec.redirect("index.xhtml");
	}
}
