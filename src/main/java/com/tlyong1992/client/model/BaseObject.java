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
    protected Direction dir;//移动方向

    public BaseObject(int x, int y, int xSpeed, int ySpeed, int width, int height) {
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
    public void move(MainView mainView) {
        switch (dir) {
            case L:
                if(positionX - mainView.getOffsetX() > 0 ){
                    positionX -= speedX;
                }
                break;
            case LU:
                if(positionX - mainView.getOffsetX() > 0 ){
                    positionX -= speedX;
                }
                if(positionY - mainView.getOffsetY() > 0){
                    positionY -= speedY;
                }
                break;
            case RU:
                if(positionX + width + mainView.getOffsetX()  < mainView.getWidth() ){
                    positionX += speedX;
                }
                if(positionY - mainView.getOffsetY() > 0){
                    positionY -= speedY;
                }
                break;
            case R:
                if(positionX + width + mainView.getOffsetX()  < mainView.getWidth() ){
                    positionX += speedX;
                }
                break;
            case RD:
                if(positionX + width + mainView.getOffsetX()  < mainView.getWidth() ){
                    positionX += speedX;
                }
                if(positionY + height + 2 * (mainView.getOffsetY() - mainView.getTitleBsrHeight())  < mainView.getHeight()){
                    positionY += speedY;
                }
                break;
            case D:
                if(positionY + height + 2 * (mainView.getOffsetY() - mainView.getTitleBsrHeight())  < mainView.getHeight()){
                    positionY += speedY;
                }
                break;
            case LD:
                if(positionX - mainView.getOffsetX() > 0 ){
                    positionX -= speedX;
                }
                if(positionY + height + 2 * (mainView.getOffsetY() - mainView.getTitleBsrHeight())  < mainView.getHeight()){
                    positionY += speedY;
                }
                break;
            case U:
                if(positionY - mainView.getOffsetY() > 0){
                    positionY -= speedY;
                }
                break;
            case STOP:
                break;
        }
    }
}
