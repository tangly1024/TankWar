package com.tlyong1992;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * USER：tangly
 * DATE：2017/5/22
 * TIME：11:00
 */
public class StartClient {

    static Logger logger = Logger.getLogger(StartClient.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ct = new ClassPathXmlApplicationContext("applicationContext.xml");
        logger.info("客户端环境初始化完毕");
    }
}
