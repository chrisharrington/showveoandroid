package dataaccess;

import java.util.List;

public interface IService {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods
	
	/*
	 * Makes a remote call to the location specified by the given query.
	 * @param query The query to execute.  Contains locations and data required for execution.
	 * @return The result of the executed query.
	 */
	<T> T execute(IQuery<T> query);

	/**
	 * Makes a remote call to the location specified by the given query.
	 * @param query The query to execute.  Contains locations and data required for execution.
	 * @return The list result of the executed query.
	 */
	<T> List<T> executeList(IQuery<T> query);
}
