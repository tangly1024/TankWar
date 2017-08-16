package com.tlyong1992.view;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * USER：tangly
 * DATE：2017/4/21
 * TIME：10:40
 */
public interface MainView {

    public void initWindow();

    public void paint(Graphics g);

    public void drawBackground(Graphics gImage);

    public void drawObject(Graphics g);

    public int getOffsetY();

    public int getOffsetX();

    public int getHeight();

    public int getWidth();

    public int getTitleBsrHeight();

    public void showLog(Logger logger, Object o);

    public JPanel getGamePanel();

    public JTextArea getTextArea();

    public Graphics getGraphics();

    public void resize();

}
