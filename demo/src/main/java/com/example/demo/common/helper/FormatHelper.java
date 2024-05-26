package com.example.demo.common.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatHelper {
	
	private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSS");
	
	public static String dateTimeFormat(LocalDateTime dateTime) {
		return dateTime.format(DATE_TIME_FORMAT);
	}
	
	private FormatHelper() {
		throw new IllegalStateException("Utility class");
	}
}
