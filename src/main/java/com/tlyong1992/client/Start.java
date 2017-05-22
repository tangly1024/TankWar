package com.tlyong1992.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * USER：tangly
 * DATE：2017/5/22
 * TIME：11:00
 */
public class Start {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ct = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}
