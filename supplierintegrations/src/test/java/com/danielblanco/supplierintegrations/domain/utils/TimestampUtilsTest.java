package com.danielblanco.supplierintegrations.domain.utils;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import static org.junit.jupiter.api.Assertions.*;

class TimestampUtilsTest {


    @Test
    public void calculateDifTest() throws ParseException {
        TimestampUtils timestampUtils = new TimestampUtils();
        String timestamp1 = "Thu, 21 Dec 2000 16:01:07 +0200";
        String timestamp2 = "Thu, 21 Dec 2000 17:01:07 +0200";

        long minutes = timestampUtils.getMinutesBetweenTimestamps(timestamp1, timestamp2);

        assertEquals(60, minutes);
    }

    @Test
    public void calculateDifErrorTest() {
        TimestampUtils timestampUtils = new TimestampUtils();
        String invalidTimestamp = "Invalid Timestamp";

        assertThrows(Exception.class, () -> {
            timestampUtils.getMinutesBetweenTimestamps(invalidTimestamp, invalidTimestamp);
        });
    }
}