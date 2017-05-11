package com.tlyong1992.client;

import client.util.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * USER：tangly
 * DATE：2017/4/21
 * TIME：16:12
 */
public class Tank {
    private int x;
    private int y;
    private static final int XSPEED = 2;
    private static final int YSPEED = 2;
    private int width = 30;
    private int height = 30;


    private Direction moveDir = Direction.STOP; // 移动方向
    private Gun gun = new Gun();

    private boolean bU = false;
    private boolean bL = false;
    private boolean bR = false;
    private boolean bD = false;

    java.util.List<Bullet> bulletList = new ArrayList();

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //坦克的炮筒是一个内部类
    private class Gun {
        Direction gunDir = Direction.U; //炮口方向
        void draw(Graphics g, int startX, int startY) {
            switch (gunDir) {
                case L:
                    g.drawLine(startX + x, startY + y + height / 2, startX + x + width / 2, startY + y + height / 2);
                    break;
                case LU:
                    g.drawLine((int) (startX + x + (width / 2) - (width / 2) / Math.sqrt(2)) - 3, (int) (startY + y + (height / 2) / Math.sqrt(2)) - 5, startX + x + width / 2, startY + y + height / 2);
                    break;
                case U:
                    g.drawLine(startX + x + (width / 2), startY + y, startX + x + width / 2, startY + y + height / 2);
                    break;
                case RU:
                    g.drawLine((int) (startX + x + (width / 2) + (width / 2) / Math.sqrt(2) + 3), (int) (startY + y + (height / 2) / Math.sqrt(2)) - 5, startX + x + width / 2, startY + y + height / 2);
                    break;
                case R:
                    g.drawLine(startX + x + width, startY + y + height / 2, startX + x + width / 2, startY + y + height / 2);
                    break;
                case RD:
                    g.drawLine((int) (startX + x + (width / 2) + (width / 2) / Math.sqrt(2)), (int) (startY + y + height / 2 + (height / 2) / Math.sqrt(2) + 2), startX + x + width / 2, startY + y + height / 2);
                    break;
                case D:
                    g.drawLine(startX + x + width / 2, startY + y + height, startX + x + width / 2, startY + y + height / 2);
                    break;
                case LD:
                    g.drawLine((int) (startX + x + (width / 2) - (width / 2) / Math.sqrt(2)), (int) (startY + y + height / 2 + (height / 2) / Math.sqrt(2) + 2), startX + x + width / 2, startY + y + height / 2);
                    break;
                case STOP:
                    break;
                default:
                    break;
            }
        }

    }

    public void draw(Graphics g, int offsetX, int offsetY) {
        move();
        Color c = g.getColor();
        g.setColor(Color.BLUE);

        g.fillOval(offsetX + x, offsetY + y, width, height); //坦克身体是一个圆
        g.setColor(Color.RED);
        this.gun.draw(g, offsetX, offsetY);
        //画出坦克的子弹
        for (Bullet bullet : bulletList) {
            bullet.draw(g, offsetX, offsetY);
        }
        g.setColor(c);
    }

    private void move() {
//调试移动        System.out.println("U:"+bU+" D:"+bD+" L:"+bL+" R:"+bR+" moveDir:"+moveDir);
        switch (moveDir) {
            case L:
                x -= XSPEED;
                break;
            case LU:
                x -= XSPEED;
                y -= YSPEED;
                break;
            case RU:
                x += XSPEED;
                y -= YSPEED;
                break;
            case R:
                x += XSPEED;
                break;
            case RD:
                x += XSPEED;
                y += YSPEED;
                break;
            case D:
                y += YSPEED;
                break;
            case LD:
                x -= XSPEED;
                y += YSPEED;
                break;
            case U:
                y -= YSPEED;
                break;
            case STOP:
                break;
        }
    }

    private void locateDirection() {
        if (!bL && !bR && !bU && !bD) {
            moveDir = Direction.STOP;
        }
        if (!bL && !bR && !bU && bD) {
            moveDir = Direction.D;
        }
        if (bL && !bR && !bU && !bD) {
            moveDir = Direction.L;
        }
        if (!bL && bR && !bU && !bD) {
            moveDir = Direction.R;
        }
        if (!bL && !bR && bU && !bD) {
            moveDir = Direction.U;
        }
        if (bL && !bR && !bU && bD) {
            moveDir = Direction.LD;
        }
        if (bL && !bR && bU && !bD) {
            moveDir = Direction.LU;
        }
        if (!bL && bR && bU && !bD) {
            moveDir = Direction.RU;
        }
        if (!bL && bR && !bU && bD) {
            moveDir = Direction.RD;
        }
        //改变炮口方向
        if (moveDir != Direction.STOP) {
            this.gun.gunDir = moveDir;
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
            bulletList.add(new Bullet(this));
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getMoveDir() {
        return moveDir;
    }

    public Direction getGunDir() {
        return gun.gunDir;
    }
}
