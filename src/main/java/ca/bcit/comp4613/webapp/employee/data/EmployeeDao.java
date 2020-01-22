package ca.bcit.comp4613.webapp.employee.data;

import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import ca.bcit.comp4613.webapp.employee.domain.Employee;
import ca.bcit.comp4613.webapp.employee.util.PropertiesUtil;

public interface EmployeeDao
{
	
	
	static final String DB_BUNDLE_NAME = "db";
	static final ResourceBundle DB_RESOURCE_BUNDLE = ResourceBundle.getBundle( DB_BUNDLE_NAME );
	final static String DRIVER =		PropertiesUtil.getString(DB_RESOURCE_BUNDLE, "driver");
	final static String USER = 			PropertiesUtil.getString(DB_RESOURCE_BUNDLE, "db.user");
	final static String PASSWORD = 		PropertiesUtil.getString(DB_RESOURCE_BUNDLE, "password");
	final static String URL = 			PropertiesUtil.getString(DB_RESOURCE_BUNDLE, "url");
	
	final static String SELECT_ALL_EMPLOYEES = "SELECT * from Employees";
	
	
	List<Employee> getEmployeeList() throws ClassNotFoundException, SQLException;
	
}
