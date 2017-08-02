package com.tlyong1992.controller;

import com.tlyong1992.net.NetManager;
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

    @Resource
    private NetManager netManager;

    @Override
    public void windowClosing(WindowEvent e) {
        netManager.disconnect();
        System.exit(0);
    }
}
