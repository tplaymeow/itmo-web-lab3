package com.tplaymeow.itmoweblab3;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class ClockBean implements Serializable {
    private final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

    public String getNowFormattedString() {
        return LocalDateTime.now().format(this.dateTimeFormatter);
    }
}
