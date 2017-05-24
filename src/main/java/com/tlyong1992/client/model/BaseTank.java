package com.tlyong1992.client.model;

import com.tlyong1992.client.constant.Constant;
import com.tlyong1992.client.constant.Direction;
import com.tlyong1992.client.view.MainView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * USER：tangly
 * DATE：2017/4/21
 * TIME：16:12
 */
public class BaseTank extends BaseObject {

    private int width = 30;
    private int height = 30;

    private boolean bGood;

    private Gun gun = new Gun();
    private boolean bU = false;
    private boolean bL = false;
    private boolean bR = false;
    private boolean bD = false;

    List<Bullet> bulletList = Collections.synchronizedList(new ArrayList());

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public BaseTank(boolean bGood, int x, int y, int tankMoveSpeedX, int tankMoveSpeedY, int tankWidth, int tankHeight , Direction dir) {
        super(x, y, tankMoveSpeedX, tankMoveSpeedY, tankWidth, tankHeight);
        this.bGood = bGood;
        this.dir = dir;
    }

    public void draw(Graphics g, MainView mainView) {
        Color c = g.getColor();
        if (this.bGood) {
            g.setColor(Color.BLUE);
        } else {
            g.setColor(Color.GREEN);
        }
//        g.drawString("导弹数量 : " + bulletList.size(), mainView.getOffsetX() + 20, mainView.getOffsetY() + 20);
//        g.fillOval(mainView.getOffsetX() + positionX, mainView.getOffsetY() + positionY, width, height); //坦克身体是一个圆
        g.fillOval(positionX, positionY, width, height); //坦克身体是一个圆
        g.setColor(Color.RED);
        //画出炮筒
        gun.draw(g);
        //TODO 画出坦克的子弹
        for (Bullet bullet : bulletList) {
            bullet.draw(g, mainView);
        }
        //TODO 画出边框
        g.setColor(c);
    }

    //坦克的炮筒是一个内部类
    private class Gun {
        Direction gunDir = Direction.U; //炮口方向
        void draw(Graphics g) {
            switch (gunDir) {
                case L:
                    g.drawLine( positionX,  positionY + height / 2,  positionX + width / 2,  positionY + height / 2);
                    break;
                case LU:
                    g.drawLine((int) ( positionX + (width / 2) - (width / 2) / Math.sqrt(2)) - 3, (int) ( positionY + (height / 2) / Math.sqrt(2)) - 5,  positionX + width / 2,  positionY + height / 2);
                    break;
                case U:
                    g.drawLine( positionX + (width / 2),  positionY,  positionX + width / 2,  positionY + height / 2);
                    break;
                case RU:
                    g.drawLine((int) ( positionX + (width / 2) + (width / 2) / Math.sqrt(2) + 3), (int) ( positionY + (height / 2) / Math.sqrt(2)) - 5,  positionX + width / 2,  positionY + height / 2);
                    break;
                case R:
                    g.drawLine( positionX + width,  positionY + height / 2,  positionX + width / 2,  positionY + height / 2);
                    break;
                case RD:
                    g.drawLine((int) ( positionX + (width / 2) + (width / 2) / Math.sqrt(2)), (int) ( positionY + height / 2 + (height / 2) / Math.sqrt(2) + 2),  positionX + width / 2,  positionY + height / 2);
                    break;
                case D:
                    g.drawLine( positionX + width / 2,  positionY + height,  positionX + width / 2,  positionY + height / 2);
                    break;
                case LD:
                    g.drawLine((int) ( positionX + (width / 2) - (width / 2) / Math.sqrt(2)), (int) ( positionY + height / 2 + (height / 2) / Math.sqrt(2) + 2),  positionX + width / 2,  positionY + height / 2);
                    break;
                case STOP:
                    break;
                default:
                    break;
            }
        }

    }

    private void locateDirection() {
        if (!bL && !bR && !bU && !bD) {
            dir = Direction.STOP;
        }
        if (!bL && !bR && !bU && bD) {
            dir = Direction.D;
        }
        if (bL && !bR && !bU && !bD) {
            dir = Direction.L;
        }
        if (!bL && bR && !bU && !bD) {
            dir = Direction.R;
        }
        if (!bL && !bR && bU && !bD) {
            dir = Direction.U;
        }
        if (bL && !bR && !bU && bD) {
            dir = Direction.LD;
        }
        if (bL && !bR && bU && !bD) {
            dir = Direction.LU;
        }
        if (!bL && bR && bU && !bD) {
            dir = Direction.RU;
        }
        if (!bL && bR && !bU && bD) {
            dir = Direction.RD;
        }
        //改变炮口方向
        if (dir != Direction.STOP) {
            this.gun.gunDir = dir;
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            bR = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            bL = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            bU = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            bD = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bulletList.add(new Bullet(this, Constant.BULLET_MOVE_SPEED_X,Constant.BULLET_MOVE_SPEED_Y,Constant.BULLET_WIDTH,Constant.BULLET_HEIGHT));
        }
        locateDirection();
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            bR = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            bL = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            bU = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            bD = false;
        }
        locateDirection();
    }


    public Direction getGunDir() {
        return gun.gunDir;
    }
}
