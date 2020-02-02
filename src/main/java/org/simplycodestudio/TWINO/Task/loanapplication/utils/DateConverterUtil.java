package org.simplycodestudio.TWINO.Task.loanapplication.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverterUtil {

    public static String formatDate(Date requestDate) {
        String pattern = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(requestDate);
    }
}
