package com.tlyong1992.view.impl;

import com.tlyong1992.view.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * USER：tangly
 * DATE：2017/8/16
 * TIME：15:47
 */
public class GamePanel extends JPanel {

    BufferedImage compatibleoffScreenImage = null; //双缓冲绘图区
    private MainView mainView;

    public GamePanel(MainView clientMainWindow) {
        this.mainView = clientMainWindow;
    }

    @Override
    public void update(Graphics g) {
        if (compatibleoffScreenImage == null) {
            compatibleoffScreenImage =   GraphicsEnvironment
                    .getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice()
                    .getDefaultConfiguration()
                    .createCompatibleImage(mainView.getWidth(), mainView.getHeight() - mainView.getTextArea().getHeight() - 10, 1);
        }
        Graphics gImage = compatibleoffScreenImage.getGraphics();

        //清屏
        drawBackground(gImage);
        drawObject(gImage);

        //将画布内容同步到屏幕上
        g.drawImage(compatibleoffScreenImage, 0, 0, null);
    }

    private void drawObject(Graphics gImage) {
    }

    private void drawBackground(Graphics gImage) {
    }

    public void doResize() {
        System.out.println(mainView.getHeight());
        System.out.println(mainView.getHeight() - mainView.getTextArea().getHeight());
        compatibleoffScreenImage =   GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .createCompatibleImage(mainView.getWidth(), mainView.getHeight() - mainView.getTextArea().getHeight() - 10, 1);
    }
}
