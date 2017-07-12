/**
 * @author Yashar Rahvar
 * Date: 10/Sep/2016
 * Project: SOAP Web Service Project Client
 * Class: EmployeeObj
 * This is a pojo class. 
 */
package ca.bcit.comp4656.data;

import java.util.Date;

public class EmployeeObj {

	private String id;
	
	private String firstName;
	private String lastName;
	private Date dob;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	
}
