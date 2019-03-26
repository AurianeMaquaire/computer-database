package com.excilys.mapper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampMapper {
	
	public static Timestamp stringToTimestamp (String timestamp) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate = dateFormat.parse(timestamp);
		return new Timestamp(parsedDate.getTime());
	}
	
	public static String timestampToString (Timestamp timestamp) {
		return new SimpleDateFormat("yyyy.MM.dd").format(timestamp);
	}
	
}
