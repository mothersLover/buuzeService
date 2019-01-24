package com.simple.util;

import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.exolab.castor.mapping.ValidityException;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Properties;

public class DateTimeFieldHandler extends GeneralizedFieldHandler {
    private static String dateFormatPattern;

    @Override
    public void setConfiguration(Properties config) throws ValidityException {
        dateFormatPattern = config.getProperty("date-format");
    }

    @Override
    public Object convertUponGet(Object o) {
        DateTime dateTime = (DateTime) o;
        return format(dateTime);
    }

    @Override
    public Object convertUponSet(Object o) {
        String dateTimeString = (String) o;
        return parse(dateTimeString);
    }

    @Override
    public Class<DateTime> getFieldType() {
        return DateTime.class;
    }

    private static String format(final DateTime dateTime) {
        String dateTimeString = "";

        if (dateTime != null) {
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormat.forPattern(dateFormatPattern);
            dateTimeString = dateTimeFormatter.print(dateTime);
        }
        return dateTimeString;
    }

    private static DateTime parse(final String dateTimeString) {
        return getDateTimeFromString(dateTimeString, dateFormatPattern);
    }

    public static DateTime getDateTimeFromString(String dateTimeString, String dateFormatPattern) {
        if(dateTimeString != null) {
            if (dateFormatPattern == null) {
                dateFormatPattern = "yyyy-MM-dd";
            }
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormat.forPattern(dateFormatPattern);
            return dateTimeFormatter.parseDateTime(dateTimeString);
        }
        return new DateTime();
    }

    public static DateTime getNewDate() {
        LocalDate localDate = new DateTime().toLocalDate();
        return parse(localDate.toString());
    }

    public static String getStringViewOfDate(DateTime dateTime) {
        return dateTime.toLocalDate().toString();
    }
}
