package serialization;

import service.serialization.IDateParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Parses date strings.
 */
public class DateParser implements IDateParser {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Parses a date string received from a remote source.
	 *
	 * @param value The string to parse.
	 * @return The parsed Date object.
	 * @throws ParseException Thrown if the string given doesn't parse correctly.
	 */
	public Date parseRemote(String value) {
		try {
			String timezone = value.substring(value.indexOf('(')+1, value.indexOf(')'));
			value = value.substring(0, value.indexOf("GMT")) + timezone;

			SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss z");
			return (Date)formatter.parse(value);
		} catch (ParseException e) {
			return null;
		}

		//Sat Feb 26 2011 18:25:47 GMT-0700 (MST)
		//Sat Feb 26 2011 18:25:47 MST
	}

}
