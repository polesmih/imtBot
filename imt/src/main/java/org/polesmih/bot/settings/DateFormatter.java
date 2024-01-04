package org.polesmih.bot.settings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    public static String formattingDateTime(String tableDate) {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dt.parse(tableDate);
            SimpleDateFormat newDateTime = new SimpleDateFormat("dd.MM.yyyy");
            tableDate = newDateTime.format(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return tableDate;
    }

}
