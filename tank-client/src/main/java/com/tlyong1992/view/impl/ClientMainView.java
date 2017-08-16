package com.tlyong1992.view.impl;

import com.tlyong1992.constant.ClientConstant;
import com.tlyong1992.model.Bullet;
import com.tlyong1992.model.EnemyTank;
import com.tlyong1992.model.Explore;
import com.tlyong1992.repository.ObjectManager;
import com.tlyong1992.view.MainView;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

/**
 * USER：tangly
 * DATE：2017/4/21
 * TIME：10:40
 */
@org.springframework.stereotype.Component
public class ClientMainView extends JFrame implements MainView {

    private int windowWidth = ClientConstant.CLIENT_WINDOW_WIDTH;
    private int windowHeight = ClientConstant.CLIENT_WINDOW_HEIGHT;
    private int windowPositionX = ClientConstant.CLIENT_WINDOW_POSITION_X;
    private int windowPositionY = ClientConstant.CLIENT_WINDOW_POSITION_Y;
    private String TITLE = ClientConstant.CLIENT_WINDOW_TITLE;
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

    public void drawBackground(Graphics gImage) {
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
        gImage.drawLine(windowWidth - offsetX, offsetY,windowWidth - offsetX,  windowHeight - 2 * (offsetY - +titleBsrHeight));

        gImage.drawString("坦克数量 : " + ObjectManager.singleTon.getEnemyTankList().size(), offsetX + 20, offsetY + 40);
        gImage.drawString("导弹数量 : " + ObjectManager.singleTon.getMyTank().getBulletList().size(), offsetX + 20, offsetY + 20);
        gImage.drawString("爆炸数量 : " + ObjectManager.singleTon.getMyTank().getExploreList().size(), offsetX + 20, offsetY + 60);

        gImage.setColor(c);
    }

    public void drawObject(Graphics g) {

        if(ObjectManager.singleTon.getMyTank()!=null && ObjectManager.singleTon.getMyTank().isLive()){
            ObjectManager.singleTon.getMyTank().draw(g, this);
            Iterator<Bullet> bullIt = ObjectManager.singleTon.getMyTank().getBulletList().iterator();
            while (bullIt.hasNext()) {
                Bullet bullet = bullIt.next();
                if (bullet.isLive()) {
                    bullet.draw(g, this); //画出活着的子弹
                } else {
                    bullIt.remove();
                }
            }
            Iterator<Explore> expIt = ObjectManager.singleTon.getMyTank().getExploreList().iterator();
            while (expIt.hasNext()) {
                Explore explore = expIt.next();
                if (explore.isLive()) {
                    explore.draw(g, this); //画出活着的子弹
                } else {
                    expIt.remove();
                }
            }
        }
        Iterator<EnemyTank> tankIt = ObjectManager.singleTon.getEnemyTankList().iterator();
        while (tankIt.hasNext()){
            EnemyTank enemyTank = tankIt.next();
            if(enemyTank.isLive()){
                enemyTank.draw(g,this);
            }else{
                tankIt.remove();
            }
            Iterator<Bullet> bullIt = enemyTank.getBulletList().iterator();
            while (bullIt.hasNext()) {
                Bullet bullet = bullIt.next();
                if (bullet.isLive()) {
                    bullet.draw(g, this); //画出活着的子弹
                } else {
                    bullIt.remove();
                }
            }
            Iterator<Explore> expIt = enemyTank.getExploreList().iterator();
            while (expIt.hasNext()) {
                Explore explore = expIt.next();
                if (explore.isLive()) {
                    explore.draw(g, this); //画出活着的子弹
                } else {
                    expIt.remove();
                }
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

    @Override
    public void showLog(Logger logger, Object o) {

    }

    @Override
    public JPanel getGamePanel() {
        return null;
    }

    @Override
    public JTextArea getTextArea() {
        return null;
    }

    @Override
    public void resize() {

    }
}
