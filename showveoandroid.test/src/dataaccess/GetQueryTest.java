package dataaccess;

import base.GetQuery;
import domain.User;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Tests the base.GetQuery class.
 */
public class GetQueryTest {

	/**
	 * Tests that the GetMethod property returns "GET".
	 */
	@Test
	public void ShouldReturnGetOnGetMethod() {
		IQuery query = new GetQuery<User>("test", User.class);
		Assert.assertEquals("GET", query.getMethod());
	}

	/**
	 * Tests that the GetQueryString property returns the expected query string.
	 */
	@Test
	public void ShouldReturnQueryString() {
		IQuery query = new GetQuery<User>("test", User.class);
		Assert.assertEquals("test", query.getQueryString());
	}

	/**
	 * Tests that the GetQueryString property returns the expected query string after data has been added.
	 */
	@Test
	public void ShouldReturnQueryStringWithData() {
		IQuery query = new GetQuery<User>("test", User.class);
		query.addData("key", "value");
		Assert.assertEquals("test?key=value", query.getQueryString());
	}
}
