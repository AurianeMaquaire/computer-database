package com.excilys.mapper;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Optional;

public class TimestampMapper {

	public static Optional<Timestamp> stringToTimestamp (String timestampStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			LocalDate parsedDate = LocalDate.parse(timestampStr, formatter);
			Timestamp time = Timestamp.valueOf(parsedDate.atStartOfDay());
			return Optional.of(time);
		} catch (DateTimeParseException e) {
			e.getStackTrace();
		}
		return Optional.empty();
	}

	public static String timestampToString (Timestamp timestamp) {
		return new SimpleDateFormat("yyyy-MM-dd").format(timestamp);
		//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//		LocalDate localDate = timestamp.toLocalDate();
		//		return localDate.format(formatter);
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

	//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	//LocalDateTime formatDateTime = LocalDateTime.parse(String, formatter);
	//localDateTime.format(formatter);

}
