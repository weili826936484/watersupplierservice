package com.wx.watersupplierservice.handle;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class A {
    public static void main(String[] args) {
        String start = "2020-12-30 16:00:00";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
            Date strtodate = formatter.parse(start, pos);

        checkLessonTime(strtodate);
        System.out.println(""+checkLessonTime(strtodate));
    }
    private static boolean checkLessonTime(Date startTime) {
        if (Objects.isNull(startTime)){
            return false;
        }
        return startTime.before(new Date());
    }
}
