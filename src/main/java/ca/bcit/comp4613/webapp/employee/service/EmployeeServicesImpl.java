package ca.bcit.comp4613.webapp.employee.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import ca.bcit.comp4613.webapp.employee.data.EmployeeDao;
import ca.bcit.comp4613.webapp.employee.data.EmployeeDaoImpl;
import ca.bcit.comp4613.webapp.employee.domain.Employee;

public class EmployeeServicesImpl implements EmployeeServices, Serializable
{

	private static final long serialVersionUID = 4856819238895719514L;
	private EmployeeDao empDao;
	
	public  EmployeeServicesImpl()
	{
		empDao = new EmployeeDaoImpl();
	}
	
	
	@Override
	public List<Employee> getEmployeeList() throws ClassNotFoundException, SQLException
	{
		return empDao.getEmployeeList();
	}
}
