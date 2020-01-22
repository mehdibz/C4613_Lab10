package ca.bcit.comp4613.webapp.employee.data;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ca.bcit.comp4613.webapp.employee.domain.Employee;


public class EmployeeDaoImpl implements EmployeeDao, Serializable
{
	private static final long serialVersionUID = -8465578238077523392L;

	@Override
	public List<Employee> getEmployeeList() throws ClassNotFoundException,SQLException
	{
		var employees = new ArrayList<Employee>();
		Statement statement = Database.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet resultSet = statement.executeQuery( SELECT_ALL_EMPLOYEES );
		while ( resultSet.next() )
		{
			Employee emp = new Employee();
			emp.setId( resultSet.getString("ID") );
			
			emp.setFirstName( resultSet.getString("firstName"));
			emp.setLastName( resultSet.getString("lastName"));
			emp.setDob( resultSet.getDate("dob"));
			employees.add(emp);
		}
		return employees;
	}


	
}
