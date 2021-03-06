/**
 * @author Yashar Rahvar
 * Date: 10/Sep/2016
 * Project: SOAP Web Service Project 
 * Class: DateConverter
 * This is a helper class to convert date. 
 */
package ca.bcit.comp4656.decorators;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateConverter {
	public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

	
	public static XMLGregorianCalendar toXMLGregorianCalendar(Date date){
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(date);
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
        } catch (DatatypeConfigurationException ex) {
          ex.printStackTrace();
        }
        return xmlCalendar;
    }


	public static Date toDate(XMLGregorianCalendar calendar){
        if(calendar == null) {
            return null;
        }
        return calendar.toGregorianCalendar().getTime();
    }

}
