package com.zhouheng.jdk8.test;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class StringAlignment {

    private static Object[][] people = {
            {"Alicef放假阿", "测试001"},
            {"Bob沙发客", "测试002"},
            {"Carol阿迪上", "测试003"},
            {"Ted发生", "测试004"},
            };

    public static void main(String[] args) {
        String nameFormat = "| %1$-20s | ";
        String dateFormat = " %2$-20s  | %n";
        String format = nameFormat.concat(dateFormat);


        System.out.printf("|%s|%s|%n",
                StringUtils.center("Name", 22),
                StringUtils.center("Birth Date", 16));

        for (Object[] data : people) {
            System.out.printf(format, data[0], data[1]);
        }

        for (int i = 0; i < 5; i++) {
            System.out.printf("%s/%s/%s", "1", "2", "3");
        }
    }
}