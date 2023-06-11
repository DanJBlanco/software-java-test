package com.danielblanco.supplierintegrations.domain.utils;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Service
public class TimestampUtils {

    public long getMinutesBetweenTimestamps(String timestamp1, String timestamp2) throws ParseException {

        DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
        Instant instant1 = Instant.from(formatter.parse(timestamp1));
        Instant instant2 = Instant.from(formatter.parse(timestamp2));

        Duration duration = Duration.between(instant1, instant2);
        return Math.abs(duration.toMinutes());
    }
}
