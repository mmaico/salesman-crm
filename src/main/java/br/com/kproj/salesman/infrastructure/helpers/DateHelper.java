package br.com.kproj.salesman.infrastructure.helpers;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.math.BigDecimal;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class DateHelper {

	private static Log logger = LogFactory.getLog(DateHelper.class);
	
	public static String DEFAULT_BRAZILIAN_PATTERN = "dd/MM/yyyy";

	private static final FastDateFormat DATE_TIME_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss'Z'");

	public static final Map<Integer, String> DAY_OF_WEEK_NAMES = new HashMap<>();

	static {
		DAY_OF_WEEK_NAMES.put(1, "Dom");
		DAY_OF_WEEK_NAMES.put(2, "Seg");
		DAY_OF_WEEK_NAMES.put(3, "Ter");
		DAY_OF_WEEK_NAMES.put(4, "Qua");
		DAY_OF_WEEK_NAMES.put(5, "Qui");
		DAY_OF_WEEK_NAMES.put(6, "Sex");
		DAY_OF_WEEK_NAMES.put(7, "S&aacute;b");
	}
	
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

	public static Date convertToDate(String date, String pattern) {
		Date formatDate = null;
		try {
			formatDate = new SimpleDateFormat(pattern).parse(date);
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

	public static String convertToString(Date date, String pattern) {
		if (date == null) {
			return null;
		}

		Format formatter = new SimpleDateFormat(pattern);

		return formatter.format(date);
	}

	public static String convertToISO8601(Date date) {
		if (date == null) {
			return null;
		}
        return DATE_TIME_FORMAT.format(date);
	}

	public static Date convertToISO8601(String date) {

		try {
			if (date == null) return null;
			return DATE_TIME_FORMAT.parse(date);
		} catch (ParseException e) {
			return null;
		}
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


	public static Boolean is(Greater greater, Than than) {
		return greater.date.after(than.date);
	}

	public static Boolean hasHourOrMinutesSet(Date date) {
		LocalDateTime localDateTime = LocalDateTime.fromDateFields(date);
		int hourOfDay = localDateTime.getHourOfDay();
		int minuteOfHour = localDateTime.getMinuteOfHour();

		return hourOfDay > 0 || minuteOfHour > 0;
	}


	public static Date now() {
		return new Date();
	}

	public static Integer quantityDaysBetween(Date date) {
		Date now = new Date();
		return quantityDaysBetween(now, date);
	}

	public static String getDayName(Date date) {
		if (date == null) {
			return "";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return DAY_OF_WEEK_NAMES.get(calendar.get(Calendar.DAY_OF_WEEK));
	}

	public static Integer quantityDaysBetween(Date startDate, Date endDate) {

		if (startDate == null || endDate == null)
			return 0;

		LocalDate localStartDate = LocalDate.fromDateFields(startDate);
		LocalDate localEndDate = LocalDate.fromDateFields(endDate);
		Days days = Days.daysBetween(localStartDate, localEndDate);

		return days.getDays();
	}

	public static Integer quantityWeeksBetween(Date startDate, Date endDate) {

		if (startDate == null || endDate == null)
			return 0;

		LocalDate localStartDate = LocalDate.fromDateFields(startDate);
		LocalDate localEndDate = LocalDate.fromDateFields(endDate);
		Days days = Days.daysBetween(localStartDate, localEndDate);

		if (days.getDays() < 8) return 0;

		BigDecimal bigDecimal = new BigDecimal(days.getDays()).divide(new BigDecimal(7), 2, BigDecimal.ROUND_HALF_UP)
				.setScale(BigDecimal.ROUND_UP, 0);

		return bigDecimal.intValue();
	}

	public static List<RangeDateDTO> getRangeWeeks(Date startDate, Date endDate) {
		Integer quantityWeeksBetween = DateHelper.quantityWeeksBetween(startDate, endDate);
		List<RangeDateDTO> result = Lists.newArrayList();

		for (int i = 1; i <= quantityWeeksBetween ; i++) {
			result.add(getRangeWeek(startDate, endDate, i));
		}

		if (result.isEmpty()) {
			result.add(RangeDateDTO.build()
					.withStartDate(startDate).withEndDate(endDate));
		}
		return result;
	}

	public static RangeDateDTO getRangeWeek(Date startDate, Date endDate, Integer week) {

		Integer maxQuantityWeeksBetween = quantityWeeksBetween(startDate, endDate);

		if (maxQuantityWeeksBetween < week) {
			return RangeDateDTO.nullObject();
		}

		Integer quantityDaysToStartDate = ((week - 1) * 7 );

		Integer quantityDaysToEndDate = maxQuantityWeeksBetween.equals(week)
				? quantityDaysBetween(startDate, endDate)
				: (week * 7) - 1;

		Date startDateSought = addDayToDate(quantityDaysToStartDate, startDate);
		Date endDateSought = addDayToDate(quantityDaysToEndDate, startDate);


		return RangeDateDTO.build().withStartDate(startDateSought).withEndDate(endDateSought);
	}

	public static class Greater{
		Date date;
		Greater(Date date) {
			this.date = date;

		}
		public static Greater greater(Date date) {
			return new Greater(date);
		}
	}

	public static class Than {
		Date date;
		Than(Date date) {
			this.date = date;
		}
		public static Than than(Date date) {
			return new Than(date);
		}
	}
}
