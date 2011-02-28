package service.serialization;

import java.util.Date;

/**
 * Defines a class used to parse date strings.
 */
public interface IDateParser {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Parses a date string received from a remote source.
	 * @param value The string to parse.
	 * @return The parsed Date object.
	 */
	Date parseRemote(String value);

}
