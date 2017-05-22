package com.tlyong1992.client.view;

import com.tlyong1992.client.constant.Constant;
import com.tlyong1992.client.model.BaseObject;
import com.tlyong1992.client.model.Tank;
import com.tlyong1992.client.thread.EventThread;
import com.tlyong1992.client.thread.PaintThread;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * USER：tangly
 * DATE：2017/4/21
 * TIME：10:40
 */
@org.springframework.stereotype.Component
public class MainView extends JFrame {

    private static final int WINDOWWIDTH = 800;
    private static final int WINDOWHEIGHT = 600;
    private static final String TITLE = "TankWar";
    Image offScreenImage = null;
    private int offsetY; //窗口边沿偏移值
    private int offsetX; //窗口边沿偏移值

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    java.util.List<BaseObject> objectList;
    Tank myTank;
    Tank enemy;

    @PostConstruct
    public void initWindow() {
        this.setSize(WINDOWWIDTH, WINDOWHEIGHT);
        this.setTitle(TITLE);
        this.setVisible(true);
//      this.setResizable(false); //不可缩放窗口
        this.setLocation(300, 200);
        offsetY = WINDOWHEIGHT - this.getContentPane().getHeight();
        offsetX = WINDOWWIDTH - this.getContentPane().getWidth();
        //AddListener
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyAdapter());

        createObject();

        executorService.submit(new PaintThread(this));
        executorService.submit(new EventThread(objectList));

    }

    private void createObject() {
        objectList = new ArrayList();
        myTank = new Tank(true,Constant.TANK_POSITION_DEFAULT_X, Constant.TANK_POSITION_DEFAULT_Y, Constant.TANK_MOVE_SPEED_X, Constant.TANK_MOVE_SPEED_Y,Constant.TANK_WIDTH,Constant.TANK_HEIGHT);
        enemy = new Tank(true,100, 100, Constant.TANK_MOVE_SPEED_X, Constant.TANK_MOVE_SPEED_Y,Constant.TANK_WIDTH,Constant.TANK_HEIGHT);
        objectList.add(myTank);
        objectList.add(enemy);
    }

    @Override
    public void paint(Graphics g) {

        if (offScreenImage == null) {
            offScreenImage = this.createImage(WINDOWWIDTH, WINDOWHEIGHT);
        }
        Graphics gImage = offScreenImage.getGraphics();
        //清屏
        drawBackground(gImage);
        drawObject(gImage);

        //将画布内容同步到屏幕上
        g.drawImage(offScreenImage, 0, 0, null);
    }

    private void drawBackground(Graphics gImage) {
        Color c = gImage.getColor();
        gImage.setColor(Color.black);
        gImage.fillRect(0, 0, WINDOWWIDTH, WINDOWHEIGHT);
        gImage.setColor(c);
    }

    void drawObject(Graphics g) {
        myTank.draw(g, this);
        enemy.draw(g, this);
    }

    private class KeyAdapter extends java.awt.event.KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            myTank.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }
    }

    public int getOffsetY() {
        return offsetY;
    }
    public int getOffsetX() {
        return offsetX;
    }

}
