/**
 * @author Yashar Rahvar
 * Date: 10/Sep/2016
 * Project: SOAP Web Service Project 
 * Class: DateDecorator
 * This is a helper class to decorate employee dob. 
 */
package ca.bcit.comp4656.decorators;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.displaytag.decorator.TableDecorator;
import ca.bcit.comp4656.data.EmployeeObj;

public class DateDecorator extends TableDecorator {
	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
	public String getDob()
	{
		Date dob = ( (EmployeeObj ) ( this.getCurrentRowObject()) ).getDob();
		if ( dob==null || "".equals( dob ) )
		{
			return dateFormatter.format( new Date() );
		}
		
		return dateFormatter.format(dob);
	}

}
