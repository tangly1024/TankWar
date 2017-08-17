package com.tlyong1992.constant;

import java.util.Random;

/**
 * USER：tangly
 * DATE：2017/8/7
 * TIME：17:35
 */
public class ClientConstant {

    public static String CLIENT_WINDOW_TITLE = "TankWar";
    public static int CLIENT_WINDOW_POSITION_X = 600;
    public static int CLIENT_WINDOW_POSITION_Y = 300;

    public static int CLIENT_WINDOW_WIDTH = 800;
    public static int CLIENT_WINDOW_HEIGHT = 600;

    public static String SERVER_ADDRESS = "127.0.0.1";
    public static int SERVER_TCP_PORT = 1025;
    public static int CLIENT_UDP_PORT = 1026 + new Random().nextInt(2000);//本地段初始UDP端口1026~3026随机端口
}
