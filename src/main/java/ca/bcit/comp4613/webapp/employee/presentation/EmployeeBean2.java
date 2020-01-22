package ca.bcit.comp4613.webapp.employee.presentation;

import java.io.Serializable;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;

import ca.bcit.comp4613.webapp.employee.domain.Employee;

@ManagedBean
@ViewScoped
public class EmployeeBean2 implements Serializable {


	private static final long serialVersionUID = 3790702488086353768L;
	private static final Logger logger = Logger.getLogger(EmployeeBean2.class);
	
	
	private LazyDataModel<Employee> lazyModel;
	
	@PostConstruct
    public void init() {
        try {
			lazyModel = new LazyEmployeeDataModel();
		} catch (ClassNotFoundException | SQLException e) {
			logger.error(e);
		}
    }
	
	public LazyDataModel<Employee> getLazyModel() {
        return lazyModel;
    }
	
	
	@PreDestroy
	public void destroy() {
		System.out.println(this.getClass().getName() + " got destroyed");
	}
	
}
