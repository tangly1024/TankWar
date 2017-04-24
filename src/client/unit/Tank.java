package client.unit;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * USER：tangly
 * DATE：2017/4/21
 * TIME：16:12
 */
public class Tank extends GameUnit {

    private int xSpeed = 2;
    private int ySpeed = 2;
    private int width = 30;
    private int height = 30;

    private Direction dir = Direction.STOP; // 移动方向
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
        move(dir, xSpeed, ySpeed);
        Color c = g.getColor();
        g.setColor(Color.BLUE);

        g.fillOval(offsetX + x, offsetY + y, width, height); //坦克身体是一个圆
        g.setColor(Color.RED);
        this.gun.draw(g, offsetX, offsetY);
        //画出坦克的子弹
        for (Bullet bullet : bulletList) {
            //画子弹的偏移初始位置
            bullet.draw(g, offsetX + width / 2 , offsetY + height / 2);
        }
        g.setColor(c);
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

    public Direction getDir() {
        return dir;
    }

    public Direction getGunDir() {
        return gun.gunDir;
    }
}
