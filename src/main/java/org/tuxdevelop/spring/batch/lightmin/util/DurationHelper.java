package org.tuxdevelop.spring.batch.lightmin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DurationHelper {

	private static final long MILLIS_UPPER_BOUND = 1000;
	private static final long SECONDS_UPPER_BOUND = 60000;
	private static final long MINUTES_UPPER_BOUND = 3600000;

	private static final String MILLIS_FORMAT = "SSS";
	private static final String SECONDS_MILLIS_FORMAT = "ss:SSS";
	private static final String MINUTES_SECONDS_MILLIS_FORMAT = "mm:ss:SSS";
	private static final String HOURS_MINUTES_SECONDS_MILLIS_FORMAT = "hh:mm:ss:SSS";

	public static String createDurationValue(Date startTime, Date endTime) {
		final Date current = new Date();
		if (startTime == null) {
			startTime = current;
			log.info("startTime was null, set to current date");
		}
		if (endTime == null) {
			endTime = current;
			log.info("endTime was null, set to current date");
		}
		final Long duration = endTime.getTime() - startTime.getTime();
		if (duration < 0) {
			throw new IllegalArgumentException("The duration may not be negativ! Values [starteTime:" + startTime
					+ "], [endTime:" + endTime + "], [duration:" + duration + "]");
		}
		return format(new Date(duration));
	}

	private static String format(final Date date) {
		final long duration = date.getTime();
		final SimpleDateFormat simpleDateFormat;
		if (duration < MILLIS_UPPER_BOUND) {
			simpleDateFormat = new SimpleDateFormat(MILLIS_FORMAT);
		} else if (duration < SECONDS_UPPER_BOUND) {
			simpleDateFormat = new SimpleDateFormat(SECONDS_MILLIS_FORMAT);
		} else if (duration < MINUTES_UPPER_BOUND) {
			simpleDateFormat = new SimpleDateFormat(MINUTES_SECONDS_MILLIS_FORMAT);
		} else {
			simpleDateFormat = new SimpleDateFormat(HOURS_MINUTES_SECONDS_MILLIS_FORMAT);
		}
		return simpleDateFormat.format(date);
	}
}
