package com.tlyong1992.model.tank;

import com.tlyong1992.constant.Dir;
import com.tlyong1992.factory.BulletFactory;
import com.tlyong1992.model.effect.Explore;
import com.tlyong1992.model.base.BaseObject;
import com.tlyong1992.model.effect.Bullet;
import com.tlyong1992.model.effect.Gun;
import com.tlyong1992.view.MainView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 普通坦克
 * USER：tangly
 * DATE：2017/4/21
 * TIME：16:12
 */
public class BaseTank extends BaseObject {

    private int id = 0;//默认坦克id为0
    private boolean good = true;
    private boolean live = true;

    private Gun gun;

    public boolean isGood(){
        return good;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Gun getGun(){
        return gun;
    }

    private List<Explore> exploreList = Collections.synchronizedList(new ArrayList<Explore>()); //爆炸列表
    private List<Bullet> bulletList = Collections.synchronizedList(new ArrayList<Bullet>());//子弹列表

    public List<Explore> getExploreList() {
        return exploreList;
    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    private boolean bU = false;
    private boolean bL = false;
    private boolean bR = false;
    private boolean bD = false;

    public BaseTank(boolean bGood, int x, int y, int tankMoveSpeedX, int tankMoveSpeedY, int tankWidth, int tankHeight, Dir dir) {
        super(x, y, tankMoveSpeedX, tankMoveSpeedY, tankWidth, tankHeight);
        this.good = bGood;
        this.dir = dir;
        gun = new Gun(this);
    }

    public BaseTank(int id, int x, int y, int tankMoveSpeedX, int tankMoveSpeedY, int tankWidth, int tankHeight, Dir dir) {
        super(x, y, tankMoveSpeedX, tankMoveSpeedY, tankWidth, tankHeight);
        this.id = id;
        this.dir = dir;
        gun = new Gun(this);
    }

    public void draw(Graphics g, MainView mainView){
        Color c = g.getColor();
        g.setColor(Color.YELLOW); //默认坦克颜色用黄色画出
        g.fillOval(positionX, positionY, width, height); //坦克身体是一个圆
        g.setColor(Color.RED);
        g.drawString(String.valueOf(getId()), positionX + width / 2, positionY); //写出坦克的id
        getGun().draw(g);//画出炮筒,画出炮筒的时机由炮筒的宿主坦克控制
        g.setColor(c);
    }

    public void setId(int id) {
        synchronized (this){
            this.id = id;
        }
    }

    public int getId(){
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

    public void shoot() {
        Bullet bullet = BulletFactory.buildDefaultBullet(this);
        bulletList.add(bullet);
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
