package com.tlyong1992.thread;

import com.tlyong1992.view.MainView;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.*;

/**
 * 绘图线程
 * USER：tangly
 * DATE：2017/5/22
 * TIME：16:20
 */
@Component
public class PaintThread implements Runnable {

    @Resource
    private MainView clientMainWindow;

    @Override
    public void run() {
        Logger logger = Logger.getLogger(PaintThread.class);

        clientMainWindow.showLog(logger, "启动绘图线程");
        while (true) {
            try {
                JPanel gp = clientMainWindow.getGamePanel();
                gp.update(clientMainWindow.getGraphics());
                Thread.sleep(30);
            } catch (Exception e) {
                clientMainWindow.showLog(logger, e);
            }
        }
    }

}
