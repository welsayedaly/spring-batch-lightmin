package org.tuxdevelop.spring.batch.lightmin.util;

import java.util.Date;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DurationHelperTest {

	@Test
	public void createDurationValueMillisTest() {
		final Long duration = 123L;
		final Date startTime = new Date(0);
		final Date endTime = new Date(duration);
		final String durationValue = DurationHelper.createDurationValue(startTime, endTime);
		assertThat(durationValue).isEqualTo(String.valueOf(duration));
	}

	@Test
	public void createDurationValueSecondsMillisTest() {
		final Long duration = 1024L;
		final String expectedValue = "01:024";
		final Date startTime = new Date(0);
		final Date endTime = new Date(duration);
		final String durationValue = DurationHelper.createDurationValue(startTime, endTime);
		assertThat(durationValue).isEqualTo(expectedValue);
	}

	@Test
	public void createDurationValueMinutesSecondsMillisTest() {
		final Long duration = 3400199L;
		final String expectedValue = "56:40:199";
		final Date startTime = new Date(0);
		final Date endTime = new Date(duration);
		final String durationValue = DurationHelper.createDurationValue(startTime, endTime);
		assertThat(durationValue).isEqualTo(expectedValue);
	}

	@Test
	public void createDurationValueHoursMinutesSecondsMillisTest() {
		final Long duration = 5000199L;
		final String expectedValue = "02:23:20:199";
		final Date startTime = new Date(0);
		final Date endTime = new Date(duration);
		final String durationValue = DurationHelper.createDurationValue(startTime, endTime);
		assertThat(durationValue).isEqualTo(expectedValue);
	}

	@Test
	public void createDurationValueStarTimeAndEndTimeNullTest() {
		final String expectedValue = "000";
		final String durationValue = DurationHelper.createDurationValue(null, null);
		assertThat(durationValue).isEqualTo(expectedValue);
	}

	@Test(expected=IllegalArgumentException.class)
	public void createDurationValueStarTimeNullTest() {
		final Long duration = 5000199L;
		final Date endTime = new Date(duration);
		DurationHelper.createDurationValue(null, endTime);
	}

	@Test(expected=IllegalArgumentException.class)
	public void createDurationValueStarTimeAfterEndTimeTest() {
		final Long duration = 5000199L;
		final Date startTime = new Date(5000200L);
		final Date endTime = new Date(duration);
		DurationHelper.createDurationValue(startTime, endTime);
	}

}
