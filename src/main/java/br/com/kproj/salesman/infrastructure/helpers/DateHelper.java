package br.com.kproj.salesman.infrastructure.helpers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class DateHelper {

	private static Log logger = LogFactory.getLog(DateHelper.class);
	
	public static String DEFAULT_BRAZILIAN_PATTERN = "dd/MM/yyyy";
	
	public static Boolean isValidDate(String dateStr) {
		
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_BRAZILIAN_PATTERN);
		try {
			sdf.parse(dateStr);
			return TRUE;
		} catch (ParseException e) {
			logger.debug("Error parsing a string " + dateStr + " into a Date object using pattern " + DEFAULT_BRAZILIAN_PATTERN);
			return FALSE;
		}
	}
	
	public static Date convertToDate(String date) {
		Date formatDate = null;
		try {
			formatDate = new SimpleDateFormat(DEFAULT_BRAZILIAN_PATTERN).parse(date);
		} catch (Exception e) {
		}
		return formatDate;
	}

    public static String convertToString(Date date) {
        if (date == null) {
            return null;
        }

        Format formatter = new SimpleDateFormat(DEFAULT_BRAZILIAN_PATTERN);

        return formatter.format(date);
    }

    public static Date addDayToDate(Integer qtdDays, Date date) {

        if (qtdDays == null || qtdDays == 0 || date == null) {
            return date;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, qtdDays);

        return calendar.getTime();

    }
}
