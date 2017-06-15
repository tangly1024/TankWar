package com.tlyong1992.thread;

import com.tlyong1992.view.MainView;
import org.apache.log4j.Logger;

/**
 * 绘图线程
 * USER：tangly
 * DATE：2017/5/22
 * TIME：16:20
 */
public class PaintThread implements Runnable {

    Logger logger = Logger.getLogger(this.getClass());

    private MainView frame;

    public PaintThread(MainView frame) {
        this.frame = frame;
    }

    @Override
    public void run() {
        logger.info("启动绘图线程");
        while (true) {
            frame.repaint();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
