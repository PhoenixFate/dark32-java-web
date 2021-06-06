package com.phoenix.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestLocalDate {

    public static void main(String[] args) {
        LocalDate localDate= LocalDate.now();
        System.out.println(localDate);
        System.out.println(localDate.plusYears(1));
        String strDate1=localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(strDate1);



        LocalDateTime localDateTime=LocalDateTime.now();
        System.out.println(localDateTime);
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String strDate2 = dtf2.format(localDateTime);
        System.out.println(strDate2);
        String strDate3=localDateTime.format(dtf2);
        System.out.println(strDate3);

    }

}
