package ca.bcit.comp4613.webapp.employee.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException 
	{
		if (connection!=null && !connection.isClosed())
		{
			return connection;
		}
		else
		{
			Class.forName( EmployeeDao.DRIVER);
			Connection connection = DriverManager.getConnection(EmployeeDao.URL, EmployeeDao.USER, EmployeeDao.PASSWORD);
			
			
			return connection;
		}
		
	}
}
