package com.tlyong1992.model;

import com.tlyong1992.constant.Dir;
import com.tlyong1992.factory.BulletFactory;
import com.tlyong1992.repository.ObjectManager;
import com.tlyong1992.view.MainView;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * USER：tangly
 * DATE：2017/4/21
 * TIME：16:12
 */
public class BaseTank extends BaseObject {

    private int width = 30;
    private int height = 30;

    private boolean good;
    private boolean live = true;

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    private Gun gun = new Gun();
    private boolean bU = false;
    private boolean bL = false;
    private boolean bR = false;
    private boolean bD = false;

    public BaseTank(boolean bGood, int x, int y, int tankMoveSpeedX, int tankMoveSpeedY, int tankWidth, int tankHeight, Dir dir) {
        super(x, y, tankMoveSpeedX, tankMoveSpeedY, tankWidth, tankHeight);
        this.good = bGood;
        this.dir = dir;
        changeGunDir(Dir.D); //初始化炮筒方向
    }

    public void draw(Graphics g, MainView mainView) {
        Color c = g.getColor();
        if (this.good) {
            g.setColor(Color.BLUE);
        } else {
            g.setColor(Color.GREEN);
        }
        g.fillOval(positionX, positionY, width, height); //坦克身体是一个圆
        g.setColor(Color.RED);
        //画出炮筒
        gun.draw(g);
        g.setColor(c);
    }

    //坦克的炮筒是一个内部类
    private class Gun {
        Dir gunDir; //炮口方向

        void draw(Graphics g) {
            switch (gunDir) {
                case L:
                    g.drawLine(positionX, positionY + height / 2, positionX + width / 2, positionY + height / 2);
                    break;
                case LU:
                    g.drawLine((int) (positionX + (width / 2) - (width / 2) / Math.sqrt(2)) - 3, (int) (positionY + (height / 2) / Math.sqrt(2)) - 5, positionX + width / 2, positionY + height / 2);
                    break;
                case U:
                    g.drawLine(positionX + (width / 2), positionY, positionX + width / 2, positionY + height / 2);
                    break;
                case RU:
                    g.drawLine((int) (positionX + (width / 2) + (width / 2) / Math.sqrt(2) + 3), (int) (positionY + (height / 2) / Math.sqrt(2)) - 5, positionX + width / 2, positionY + height / 2);
                    break;
                case R:
                    g.drawLine(positionX + width, positionY + height / 2, positionX + width / 2, positionY + height / 2);
                    break;
                case RD:
                    g.drawLine((int) (positionX + (width / 2) + (width / 2) / Math.sqrt(2)), (int) (positionY + height / 2 + (height / 2) / Math.sqrt(2) + 2), positionX + width / 2, positionY + height / 2);
                    break;
                case D:
                    g.drawLine(positionX + width / 2, positionY + height, positionX + width / 2, positionY + height / 2);
                    break;
                case LD:
                    g.drawLine((int) (positionX + (width / 2) - (width / 2) / Math.sqrt(2)), (int) (positionY + height / 2 + (height / 2) / Math.sqrt(2) + 2), positionX + width / 2, positionY + height / 2);
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
            dir = Dir.STOP;
        }
        if (!bL && !bR && !bU && bD) {
            dir = Dir.D;
        }
        if (bL && !bR && !bU && !bD) {
            dir = Dir.L;
        }
        if (!bL && bR && !bU && !bD) {
            dir = Dir.R;
        }
        if (!bL && !bR && bU && !bD) {
            dir = Dir.U;
        }
        if (bL && !bR && !bU && bD) {
            dir = Dir.LD;
        }
        if (bL && !bR && bU && !bD) {
            dir = Dir.LU;
        }
        if (!bL && bR && bU && !bD) {
            dir = Dir.RU;
        }
        if (!bL && bR && !bU && bD) {
            dir = Dir.RD;
        }
        //改变炮口方向
        if (dir != Dir.STOP) {
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
            shoot();
        }
        locateDirection();
    }

    /**
     * 子弹发射
     */
    public void shoot() {
        Bullet bullet = BulletFactory.buildDefaultBullet(this);
        ObjectManager.singleTon.getBulletList().add(bullet);
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

    public void changeGunDir(Dir dir) {
        this.gun.gunDir = dir;
    }

    public Dir getGunDir() {
        return this.gun.gunDir;
    }
}
