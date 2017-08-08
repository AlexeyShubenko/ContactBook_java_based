package com.test.javaproject;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alexey on 08.08.2017.
 */
public class Main {


    public static void main(String[] args) {
        Pattern p = Pattern.compile("^[A-z]+[0-9]+$");
        Matcher m = p.matcher("");
        System.out.println(m.matches());

    }

}
