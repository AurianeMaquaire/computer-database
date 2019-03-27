package com.excilys.mapper;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampMapper {
	
	public static Timestamp stringToTimestamp (String timestampStr) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate = dateFormat.parse(timestampStr);
		return new Timestamp(parsedDate.getTime());
//	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//	    LocalDateTime formatDateTime = LocalDateTime.parse(timestampStr, formatter);
//	    return Timestamp.valueOf(formatDateTime.format(formatter));
	}
	
	public static String timestampToString (Timestamp timestamp) {
		return new SimpleDateFormat("yyyy-MM-dd").format(timestamp);
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
//		LocalDateTime localDateTime = timestamp.toLocalDateTime();
//		return localDateTime.format(formatter);
	}
	
	public static Timestamp currentTimeToTimestamp () {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = df.format(new Date());
		return Timestamp.valueOf(currentTime);
//		LocalDateTime now = LocalDateTime.now();
//	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//	    String formatDateTime = now.format(formatter);
//		return Timestamp.valueOf(formatDateTime);
	}
	
}
