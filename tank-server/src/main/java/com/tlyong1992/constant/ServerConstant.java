package com.tlyong1992.constant;

import org.springframework.beans.factory.annotation.Value;

/**
 * USER：tangly
 * DATE：2017/5/22
 * TIME：11:15
 */
public class ServerConstant {

    public static int SERVER_TCP_PORT = 1025;
    public static int SERVER_UDP_PORT = 1024;

    @Value("${udpPort}")
    public static String UDP_PORT;

}
