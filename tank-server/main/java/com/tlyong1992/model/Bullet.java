package com.tlyong1992.model;

import com.tlyong1992.repository.ObjectManager;
import com.tlyong1992.view.ServerMainView;
import org.apache.log4j.Logger;

import java.awt.*;

/**
 * USER：tangly
 * DATE：2017/4/24
 * TIME：13:46
 */
public class Bullet extends BaseObject {

    Logger logger = Logger.getLogger(this.getClass());

    private BaseTank tank;//引用发出这颗子弹的坦克

    boolean live = true;

    public boolean isLive(){return live;}
    public void setLive(boolean live) {
        this.live = live;
    }

    public Bullet(BaseTank tank, int speedX, int speedY, int width, int height) {
        super(tank.getPositionX(), tank.getPositionY(), speedX, speedY, width, height);
        this.tank = tank;
        this.dir = tank.getGunDir();
    }

    //子弹自己的draw方法
    public void draw(Graphics g, ServerMainView mainView) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(tank.getWidth() / 2 + positionX, tank.getHeight() / 2 + positionY, width, height);
        g.setColor(c);
        move(mainView);
    }

    @Override
    public void move(ServerMainView mainView) {
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
                if(positionY - mainView.getOffsetY() + tank.getHeight() / 2 > mainView.getOffsetY()- mainView.getTitleBsrHeight()){
                    positionY -= speedY;
                }else{
                    live = false;
                }
                break;
            case RU:
                if(positionX + width + mainView.getOffsetX() < mainView.getWidth() - mainView.getOffsetX()){
                    positionX += speedX;
                }else{
                    live = false;
                }
                if(positionY - mainView.getOffsetY() + tank.getHeight() / 2 > mainView.getOffsetY()- mainView.getTitleBsrHeight()){
                    positionY -= speedY;
                }else{
                    live = false;
                }
                break;
            case R:
                if(positionX + width + mainView.getOffsetX() < mainView.getWidth() - mainView.getOffsetX()){
                    positionX += speedX;
                }else{
                    live = false;
                }
                break;
            case RD:
                if(positionX + width + mainView.getOffsetX() < mainView.getWidth() - mainView.getOffsetX()){
                    positionX += speedX;
                }else{
                    live = false;
                }
                if(positionY + height  < mainView.getHeight() - 4 * (mainView.getOffsetY() - mainView.getTitleBsrHeight())){
                    positionY += speedY;
                }else{
                    live = false;
                }
                break;
            case D:
                if(positionY + height  < mainView.getHeight() - 4 * (mainView.getOffsetY() - mainView.getTitleBsrHeight())){
                    positionY += speedY;
                }else{
                    live = false;
                }
                break;
            case LD:
                if(positionX - mainView.getOffsetX() + tank.getWidth() / 2 > mainView.getOffsetY()- mainView.getTitleBsrHeight() ){
                    positionX -= speedX;
                }else{
                    live = false;
                }
                if(positionY + height  < mainView.getHeight() - 4 * (mainView.getOffsetY() - mainView.getTitleBsrHeight())){
                    positionY += speedY;
                }else{
                    live = false;
                }
                break;
            case U:
                if(positionY - mainView.getOffsetY() + tank.getHeight() / 2 > mainView.getOffsetY()- mainView.getTitleBsrHeight()){
                    positionY -= speedY;
                }else{
                    live = false;
                }
                break;
            case STOP:
                break;
        }
    }

    public boolean attackTank(BaseTank enemy) {

        //TODO 有一定概率打不死，碰撞检测有异常
        //TODO 有一定概率突然所有坦克都不能移动

        if (enemy.getRect().intersects(getRect())) {
            if (enemy == tank) {
                return false;
            }
            ObjectManager.singleTon.getExploreList().add(new Explore(this));
             return true;
        }

        return false;
    }

}
