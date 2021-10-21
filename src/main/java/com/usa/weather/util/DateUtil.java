package com.usa.weather.util;

import java.util.Date;

import org.joda.time.DateTime;

public interface DateUtil {
	  public boolean compareTwoDates(Date startDate, Date endDate);
	  public String dateFormatter(Date date);
	  public Date getTomorrowDate(DateTime today);


}
