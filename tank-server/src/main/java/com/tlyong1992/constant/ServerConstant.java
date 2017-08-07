package com.tlyong1992.constant;

import org.springframework.beans.factory.annotation.Value;

/**
 * USER：tangly
 * DATE：2017/5/22
 * TIME：11:15
 */
public class ServerConstant {

    public static String WINDOW_TITLE = "TankWar-Server";
    public static int WINDOW_POSITION_X = 600;
    public static int WINDOW_POSITION_Y = 300;

    public static int SERVER_TCP_PORT = 1025;
    public static int SERVER_UDP_PORT = 1024;

    public static int WINDOW_WIDTH = 800;
    public static int WINDOW_HEIGHT = 600;

    @Value("${udpPort}")
    public static String UDP_PORT;

}
