package com.tlyong1992.client.view;

import com.tlyong1992.client.constant.Constant;
import com.tlyong1992.client.model.BaseTank;
import com.tlyong1992.client.model.Bullet;
import com.tlyong1992.client.repository.ObjectManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;

/**
 * USER：tangly
 * DATE：2017/4/21
 * TIME：10:40
 */
@org.springframework.stereotype.Component
public class MainView extends JFrame {

    private int windowWidth = Constant.WINDOW_WIDTH;
    private int windowHeight = Constant.WINDOW_HEIGHT;
    private int windowPositionX = Constant.WINDOW_POSITION_X;
    private int windowPositionY = Constant.WINDOW_POSITION_Y;
    private String TITLE = Constant.WINDOW_TITLE;
    private int offsetY; //窗口边沿偏移值
    private int offsetX; //窗口边沿偏移值
    private int titleBsrHeight; //标题栏高度

    Image offScreenImage = null;

    public void initWindow() {
        this.setSize(windowWidth, windowHeight);
        this.setTitle(TITLE);
        this.setVisible(true);
        this.setResizable(false); //不可缩放窗口
        this.setLocation(windowPositionX, windowPositionY);
        offsetY = windowHeight - this.getContentPane().getHeight();
        offsetX = windowWidth - this.getContentPane().getWidth();
        titleBsrHeight =this.getInsets().top;

        //AddListener
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyAdapter());

    }

    @Override
    public void paint(Graphics g) {

        if (offScreenImage == null) {
            offScreenImage = this.createImage(windowWidth, windowHeight);
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
        gImage.fillRect(0, 0, windowWidth, windowHeight);
        gImage.setColor(Color.WHITE);
        //顶部水平线
        gImage.drawLine(offsetX, offsetY, windowWidth - offsetX, offsetY);
        //底部水平线
        gImage.drawLine(offsetX, windowHeight - 2 * (offsetY - titleBsrHeight), windowWidth - offsetX, windowHeight - 2 * (offsetY - +titleBsrHeight));
        //左侧垂直线
        gImage.drawLine(offsetX,offsetY,offsetX, windowHeight - 2 * (offsetY - titleBsrHeight));
        //右侧垂直线
        gImage.drawLine(windowWidth - offsetX, offsetY,windowWidth - offsetX, windowHeight - 2 * (offsetY - +titleBsrHeight));

        gImage.drawString("导弹数量 : " + ObjectManager.singleTon.getBulletList().size(), offsetX + 20, offsetY + 20);

        gImage.setColor(c);
    }

    void drawObject(Graphics g) {

        ObjectManager.singleTon.getMyTank().draw(g, this);
        for(BaseTank enemyTank : ObjectManager.singleTon.getEnemyTankList()){
            enemyTank.draw(g,this);
        }

        Iterator<Bullet> it = ObjectManager.singleTon.getBulletList().iterator();
        while (it.hasNext()) {
            Bullet bullet = it.next();
            if (bullet.isLive()) {
                bullet.draw(g, this); //画出活着的子弹
            } else {
                it.remove();
            }
        }

    }


    private class KeyAdapter extends java.awt.event.KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            ObjectManager.singleTon.getMyTank().keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            ObjectManager.singleTon.getMyTank().keyReleased(e);
        }
    }

    public int getOffsetY() {
        return offsetY;
    }
    public int getOffsetX() {
        return offsetX;
    }
    public int getTitleBsrHeight() {
        return titleBsrHeight;
    }
}
