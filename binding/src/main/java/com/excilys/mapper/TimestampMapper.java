package com.excilys.mapper;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class TimestampMapper {
	
	private TimestampMapper() {}

	public static Optional<Timestamp> stringToTimestamp(String timestampStr) {
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

	public static String timestampToString(Timestamp timestamp) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Date date = new Date(timestamp.getTime());
		return formatter.format(formatter.parse(date.toString()));
	}

	public static Timestamp currentTimeToTimestamp() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDateTime = now.format(formatter);
		return Timestamp.valueOf(formatDateTime);
	}

}
