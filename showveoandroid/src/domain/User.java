package domain;

//
//	A DTO for user information.
//
public class User {

	private String _firstName;
	private String _lastName;
	private String _emailAddress;
	private String _identity;
	
	public String getFirstName() { return _firstName; }
	public void setFirsTName(String firstName) { _firstName = firstName; }
	
	public String getLastName() { return _lastName; }
	public void setLastName(String lastName) { _lastName = lastName; }
	
	public String getEmailAddress() { return _emailAddress; }
	public void setEmailAddress(String emailAddress) { _emailAddress = emailAddress; }
	
	public String getIdentity() { return _identity; }
	public void setIdentity(String identity) { _identity = identity; }
}
