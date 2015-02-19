package com.purchasing.support.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author vanessa
 */
public class Conversor {

    public static String converterDateInString(Date date) {
        String dateString = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dateString = simpleDateFormat.format(date);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dateString;
    }

    public static  Date converterStringForDate(String data){;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        if (data != null){
            String dataFormatar = data.replace("-", "/");
            try {
                date = simpleDateFormat.parse(dataFormatar);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    public static String converterDateTimeInString(Date date) {
        String dateString = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            dateString = simpleDateFormat.format(date);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dateString;
    }

    public static  Date converterStringForDateTime(String data){;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = null;
        if (data != null){
            String dataFormatar = data.replace("-", "/");
            try {
                date = simpleDateFormat.parse(dataFormatar);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    public static String converterDateInStringForDocument(Date date) {
            String dateString = null;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MM_yyyy");
            try {
                dateString = simpleDateFormat.format(date);
            } catch (Exception ex) {
                System.out.println(ex);
            }
            return dateString;
        }
}
