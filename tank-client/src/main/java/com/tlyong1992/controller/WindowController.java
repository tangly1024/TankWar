package com.tlyong1992.controller;

import com.tlyong1992.net.NetManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * USER：tangly
 * DATE：2017/5/27
 * TIME：14:45
 */
@Component
public class WindowController extends WindowAdapter {

    Logger logger = Logger.getLogger(this.getClass());

    @Resource
    private NetManager netManager;

    @Override
    public void windowClosing(WindowEvent e) {
        logger.info("断开连接，并推出..");
        netManager.disconnect();
        System.exit(0);
    }
}
