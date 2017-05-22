package com.tlyong1992.client.model;

import com.tlyong1992.client.constant.Direction;
import com.tlyong1992.client.view.MainView;

import java.awt.*;

/**
 * USER：tangly
 * DATE：2017/4/24
 * TIME：15:12
 */
public abstract class BaseObject {

    protected int positionX;
    protected int positionY;
    protected int speedX;
    protected int speedY;
    protected int width;
    protected int height;
    protected Direction dir = Direction.STOP;//移动方向

    public BaseObject(int x, int y, int xSpeed, int ySpeed, int width,int height) {
        this.positionX = x;
        this.positionY = y;
        this.speedX = xSpeed;
        this.speedY = ySpeed;
        this.width = width;
        this.height = height;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public abstract void draw(Graphics g, MainView mainView);

    //处理物体移动的函数
    public void move() {
        switch (dir) {
            case L:
                positionX -= speedX;
                break;
            case LU:
                positionX -= speedX;
                positionY -= speedY;
                break;
            case RU:
                positionX += speedX;
                positionY -= speedY;
                break;
            case R:
                positionX += speedX;
                break;
            case RD:
                positionX += speedX;
                positionY += speedY;
                break;
            case D:
                positionY += speedY;
                break;
            case LD:
                positionX -= speedX;
                positionY += speedY;
                break;
            case U:
                positionY -= speedY;
                break;
            case STOP:
                break;
        }
    }
}
