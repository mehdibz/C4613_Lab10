package ca.bcit.comp4613.webapp.employee.service;

import java.sql.SQLException;
import java.util.List;

import ca.bcit.comp4613.webapp.employee.domain.Employee;

public interface EmployeeServices
{
	List<Employee> getEmployeeList() throws ClassNotFoundException, SQLException ;
}
