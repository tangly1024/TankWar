package com.tlyong1992.client.model;

import com.tlyong1992.client.view.MainView;

import java.awt.*;

/**
 * USER：tangly
 * DATE：2017/4/24
 * TIME：13:46
 */
public class Bullet extends BaseObject {

    private BaseTank tank;//引用发出这颗子弹的坦克

    boolean live = true;

    public void setLive(boolean live) {
        this.live = live;
    }

    public Bullet(BaseTank tank, int speedX, int speedY, int width, int height) {
        super(tank.getPositionX(), tank.getPositionY(), speedX, speedY, width, height);
        this.tank = tank;
        this.dir = tank.getGunDir();
    }

    //子弹自己的draw方法
    public void draw(Graphics g, MainView mainView) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(tank.getWidth() / 2 + positionX, tank.getHeight() / 2 + positionY, width, height);
        g.setColor(c);
        move(mainView);
    }

    @Override
    public void move(MainView mainView) {
        //TODO 子弹右侧的消失还有问题
        switch (dir) {
            case L:
                if(positionX - mainView.getOffsetX() + tank.getWidth() / 2 > 0 ){
                    positionX -= speedX;
                }else{
                    live = false;
                }
                break;
            case LU:
                if(positionX - mainView.getOffsetX() + tank.getWidth() / 2 > 0 ){
                    positionX -= speedX;
                }else{
                    live = false;
                }
                if(positionY - mainView.getOffsetY() + tank.getHeight() / 2 > 0){
                    positionY -= speedY;
                }else{
                    live = false;
                }
                break;
            case RU:
                if(positionX + width + mainView.getOffsetX() < mainView.getWidth() ){
                    positionX += speedX;
                }else{
                    live = false;
                }
                if(positionY - mainView.getOffsetY() + tank.getHeight() / 2 > 0){
                    positionY -= speedY;
                }else{
                    live = false;
                }
                break;
            case R:
                if(positionX + width + mainView.getOffsetX() < mainView.getWidth() ){
                    positionX += speedX;
                }else{
                    live = false;
                }
                break;
            case RD:
                if(positionX + width + mainView.getOffsetX() < mainView.getWidth() ){
                    positionX += speedX;
                }else{
                    live = false;
                }
                if(positionY + height + 2 * (mainView.getOffsetY() - mainView.getTitleBsrHeight())  < mainView.getHeight()){
                    positionY += speedY;
                }else{
                    live = false;
                }
                break;
            case D:
                if(positionY + height + 2 * (mainView.getOffsetY() - mainView.getTitleBsrHeight())  < mainView.getHeight()){
                    positionY += speedY;
                }else{
                    live = false;
                }
                break;
            case LD:
                if(positionX - mainView.getOffsetX() + tank.getWidth() / 2 > 0 ){
                    positionX -= speedX;
                }else{
                    live = false;
                }
                if(positionY + height + 2 * (mainView.getOffsetY() - mainView.getTitleBsrHeight())  < mainView.getHeight()){
                    positionY += speedY;
                }else{
                    live = false;
                }
                break;
            case U:
                if(positionY - mainView.getOffsetY() + tank.getHeight() / 2 > 0){
                    positionY -= speedY;
                }else{
                    live = false;
                }
                break;
            case STOP:
                break;
        }
    }
}
