package com.tlyong1992.client.thread;

import javax.swing.*;

/**
 * 绘图线程
 * USER：tangly
 * DATE：2017/5/22
 * TIME：16:20
 */
public class PaintThread implements Runnable {

    private JFrame frame;

    public PaintThread(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void run() {
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
