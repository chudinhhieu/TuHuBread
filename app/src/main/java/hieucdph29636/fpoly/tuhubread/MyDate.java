package hieucdph29636.fpoly.tuhubread;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {
    @SuppressLint("SimpleDateFormat")
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    static SimpleDateFormat vn = new SimpleDateFormat("dd/MM/yyyy");
    public static String toString(Date date) {
        return sdf.format(date);
    }
    public static String toStringVn(Date date) {
        return vn.format(date);
    }

    public static Date toDate(String strDate) throws ParseException {
        return sdf.parse(strDate);
    }
}
