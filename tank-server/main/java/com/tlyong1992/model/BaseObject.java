package com.tlyong1992.model;

import com.tlyong1992.constant.Dir;
import com.tlyong1992.view.ServerMainView;

import java.awt.*;

/**
 * USER：tangly
 * DATE：2017/4/24
 * TIME：15:12
 */
abstract class BaseObject {

    int positionX;
    int positionY;
    int speedX;
    int speedY;
    int width;
    int height;
    Dir dir;//移动方向

    BaseObject(int x, int y, int xSpeed, int ySpeed, int width, int height) {
        this.positionX = x;
        this.positionY = y;
        this.speedX = xSpeed;
        this.speedY = ySpeed;
        this.width = width;
        this.height = height;
    }

    int getPositionX() {
        return positionX;
    }

    int getPositionY() {
        return positionY;
    }

    Rectangle getRect() {
        return new Rectangle(positionX, positionY, width, height);
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    abstract void draw(Graphics g, ServerMainView mainView);

    //处理物体移动的函数
    void move(ServerMainView mainView) {
        switch (dir) {
            case L:
                if (positionX > 0) {
                    positionX -= speedX;
                }
                break;
            case LU:
                if (positionX > 0) {
                    positionX -= speedX;
                }
                if (positionY > 0) {
                    positionY -= speedY;
                }
                break;
            case RU:
                if (positionX + width < mainView.getWidth()) {
                    positionX += speedX;
                }
                if (positionY > 0) {
                    positionY -= speedY;
                }
                break;
            case R:
                if (positionX + width < mainView.getWidth()) {
                    positionX += speedX;
                }
                break;
            case RD:
                if (positionX + width < mainView.getWidth()) {
                    positionX += speedX;
                }
                if (positionY + height < mainView.getHeight()) {
                    positionY += speedY;
                }
                break;
            case D:
                if (positionY + height < mainView.getHeight()) {
                    positionY += speedY;
                }
                break;
            case LD:
                if (positionX > 0) {
                    positionX -= speedX;
                }
                if (positionY + height < mainView.getHeight()) {
                    positionY += speedY;
                }
                break;
            case U:
                if (positionY > 0) {
                    positionY -= speedY;
                }
                break;
            case STOP:
                break;
        }
    }
}
