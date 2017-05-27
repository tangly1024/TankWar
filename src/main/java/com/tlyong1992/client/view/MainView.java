package com.tlyong1992.client.view;

import com.tlyong1992.client.constant.Constant;
import com.tlyong1992.client.controller.KeyController;
import com.tlyong1992.client.controller.WindowController;
import com.tlyong1992.client.model.Bullet;
import com.tlyong1992.client.model.EnemyTank;
import com.tlyong1992.client.repository.ObjectManager;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
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

    @Resource
    private KeyController keyController;

    @Resource
    private WindowController windowController;

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
        this.addWindowListener(windowController);
        this.addKeyListener(keyController);

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

        if(ObjectManager.singleTon.getMyTank()!=null && ObjectManager.singleTon.getMyTank().isLive()){
            ObjectManager.singleTon.getMyTank().draw(g, this);
        }

        Iterator<EnemyTank> tankIt = ObjectManager.singleTon.getEnemyTankList().iterator();
        while (tankIt.hasNext()){
            EnemyTank enemyTank = tankIt.next();
            if(enemyTank.isLive()){
                enemyTank.draw(g,this);
            }else{
                tankIt.remove();
            }
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
