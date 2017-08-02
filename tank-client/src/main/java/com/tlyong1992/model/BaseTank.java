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
public abstract class BaseTank extends BaseObject {

    private int id = 0;//默认坦克id为0

    private boolean good = true;
    private boolean live = true;

    private Gun gun;

    boolean isGood(){
        return good;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    Gun getGun(){
        return gun;
    }


    private boolean bU = false;
    private boolean bL = false;
    private boolean bR = false;
    private boolean bD = false;

    BaseTank(boolean bGood, int x, int y, int tankMoveSpeedX, int tankMoveSpeedY, int tankWidth, int tankHeight, Dir dir) {
        super(x, y, tankMoveSpeedX, tankMoveSpeedY, tankWidth, tankHeight);
        this.good = bGood;
        this.dir = dir;
        gun = new Gun(this);
    }

    abstract public void draw(Graphics g, MainView mainView);

    public void setId(int id) {
        synchronized (this){
            this.id = id;
        }
    }

    int getId(){
        return this.id;
    }

    //坦克的炮筒是一个内部类

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

    void changeGunDir(Dir dir) {
        this.gun.gunDir = dir;
    }

    Dir getGunDir() {
        return this.gun.gunDir;
    }
}
