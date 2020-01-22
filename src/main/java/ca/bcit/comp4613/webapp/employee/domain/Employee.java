package ca.bcit.comp4613.webapp.employee.domain;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.SessionScoped;

@SessionScoped
public class Employee implements Serializable 
{
	private static final long serialVersionUID = -6878343285554228481L;
	protected String id;
	protected String firstName;
	protected String lastName;
	protected Date dob;
	

	public String getId()
	{
		return id;
	}

	public void setId( String value )
	{
		this.id = value;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName( String value )
	{
		this.firstName = value;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName( String value )
	{
		this.lastName = value;
	}

	public Date getDob()
	{
		return dob;
	}

	public void setDob( Date value )
	{
		this.dob = value;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Employee other = ( Employee ) obj;
		if ( id == null )
		{
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + "]";
	}

	
	

}
