package user;

import base.GetQuery;
import dataaccess.IQuery;
import domain.User;

//
//	A static class containing user related queries.
//
public class UserQueries {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods
	
	//
	//	A query used to describe the retrieval of a user by email address and password.
	//	emailAddress:			The user's email address.
	//	password:				The user's password.
	//
	public static IQuery<User> getByEmailAddressAndPassword(String emailAddress, String password) {
		IQuery<User> query = new GetQuery<User>("account/signin", User.class);
		query.addData("emailAddress", emailAddress);
		query.addData("password", password);
		return query;
	}
	
}
