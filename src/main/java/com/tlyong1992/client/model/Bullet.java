package com.tlyong1992.client.model;

import com.tlyong1992.client.view.MainView;

import java.awt.*;

/**
 * USER：tangly
 * DATE：2017/4/24
 * TIME：13:46
 */
public class Bullet extends BaseObject {

    private Tank tank;//引用发出这颗子弹的坦克

    public Bullet(Tank tank, int speedX, int speedY, int width, int height) {
        super(tank.getPositionX(), tank.getPositionY(), speedX, speedY, width, height);
        this.tank = tank;
        this.dir = tank.getGunDir();
    }

    //子弹自己的draw方法
    public void draw(Graphics g, MainView mainView) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(tank.getWidth() / 2 + mainView.getOffsetX() + positionX, tank.getHeight() / 2 + mainView.getOffsetY() + positionY, width, height);
        g.setColor(c);
        move();
    }

}
