package com.usa.weather.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.springframework.stereotype.Component;

@Component
public class DateUtilImpl implements DateUtil {

	@Override
	public boolean compareTwoDates(Date startDate, Date endDate) {
		return DateTimeComparator.getDateOnlyInstance().compare(startDate, endDate) == 0 ? true : false;
	}

	@Override
	public Date getTomorrowDate(DateTime today) {
		return today.plusDays(1).withTimeAtStartOfDay().toDate();
	}

	@Override
	public String dateFormatter(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm");
		return dateFormat.format(date);
	}

}
