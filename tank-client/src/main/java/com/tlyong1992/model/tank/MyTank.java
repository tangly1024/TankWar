package com.tlyong1992.model.tank;

import com.tlyong1992.constant.Dir;
import com.tlyong1992.view.MainView;

import java.awt.*;

/**
 * 本机客户端的坦克，作为自己的坦克自然要与众不同
 * USER：tangly
 * DATE：2017/5/24
 * TIME：10:58
 */
public class MyTank extends BaseTank {
    public MyTank(boolean bGood, int x, int y, int tankMoveSpeedX, int tankMoveSpeedY, int tankWidth, int tankHeight, Dir dir) {
        super(bGood, x, y, tankMoveSpeedX, tankMoveSpeedY, tankWidth, tankHeight, dir);
    }

    @Override
    public void draw(Graphics g, MainView mainView) {
        Color c = g.getColor();
        g.setColor(Color.BLUE);
        g.fillOval(positionX, positionY, width, height); //坦克身体是一个圆
        g.setColor(Color.RED);
        g.drawString(String.valueOf(getId()), positionX + width / 2, positionY); //写出坦克的id
        getGun().draw(g);//画出炮筒,画出炮筒的时机由炮筒的宿主坦克控制
        g.setColor(c);
    }

}
