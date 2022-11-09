package com.kaimingwan.core.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author wanshao create time is  2022/11/9
 **/
public class TimeUtil {

  public static String getDateTimeAsString(LocalDateTime localDateTime, String format) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return localDateTime.format(formatter);
  }

  public static LocalDateTime getLocalDateTimeByMillis(long timestamp) {
    Instant instant = Instant.ofEpochMilli(timestamp);
    ZoneId zone = ZoneId.systemDefault();
    return LocalDateTime.ofInstant(instant, zone);
  }


  public static long getTimestampOfDateTime(LocalDateTime localDateTime) {
    ZoneId zone = ZoneId.systemDefault();
    Instant instant = localDateTime.atZone(zone).toInstant();
    return instant.toEpochMilli();
  }


}
