package com.tlyong1992.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * USER：tangly
 * DATE：2017/5/22
 * TIME：11:15
 */
@Component
public class ServerConstant {

    public static String WINDOW_TITLE = "TankWar-Server";
    public static int WINDOW_POSITION_X = 600;
    public static int WINDOW_POSITION_Y = 300;

    public static int SERVER_TCP_PORT = 1025;
    public static int SERVER_UDP_PORT = 1024;

    public static int TANK_POSITION_DEFAULT_X = 100;
    public static int TANK_POSITION_DEFAULT_Y = 100;
    public static int TANK_MOVE_SPEED_X = 3;
    public static int TANK_MOVE_SPEED_Y = 3;
    public static int TANK_WIDTH = 30;
    public static int TANK_HEIGHT = 30;

    public static int BULLET_MOVE_SPEED_X = 4;
    public static int BULLET_MOVE_SPEED_Y = 4;
    public static int BULLET_WIDTH = 3;
    public static int BULLET_HEIGHT = 3;

    public static int WINDOW_WIDTH = 800;
    public static int WINDOW_HEIGHT = 600;

    @Value("${udpPort}")
    public static String UDP_PORT;

}
