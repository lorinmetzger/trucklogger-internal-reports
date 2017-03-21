package com.trucklogger.internal.reports;

import java.util.Date;
import java.util.Calendar;

public class TimeUtil
{
  public static Date yesterday()
  {
    return new Date((System.currentTimeMillis() - (24 * 60 * 60000)));
  }
  
  public static Date theDayBeforeYesterday()
  {
    return new Date((System.currentTimeMillis() - (48 * 60 * 60000)));
  }

  public static Date startOfDay(Date day)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime( day );
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
  }

  public static Date endOfDay(Date day)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime( day );
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.MILLISECOND, 999);
    return cal.getTime();
  }
}
