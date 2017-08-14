package com.tlyong1992.thread;

import com.tlyong1992.view.ServerWindow;

/**
 * 绘图线程
 * USER：tangly
 * DATE：2017/5/22
 * TIME：16:20
 */
public class PaintThread implements Runnable {

    private ServerWindow mainView;

    public PaintThread(ServerWindow mainView) {
        this.mainView = mainView;
    }

    @Override
    public void run() {
        mainView.showLog("绘图线程 启动");
        while (true) {
//            frame.repaint();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
