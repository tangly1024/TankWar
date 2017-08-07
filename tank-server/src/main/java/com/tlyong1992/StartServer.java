package com.tlyong1992;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * USER：tangly
 * DATE：2017/5/22
 * TIME：11:00
 */
public class StartServer {
    public static void main(String[] args) {
        //启动服务器
        ClassPathXmlApplicationContext ct = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}
