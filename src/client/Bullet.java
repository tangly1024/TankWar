package client;

import client.util.Direction;

import java.awt.*;

/**
 * USER：tangly
 * DATE：2017/4/24
 * TIME：13:46
 */
public class Bullet {

    private Tank tank;//引用发出这颗子弹的坦克
    private int x;
    private int y;
    private static final int XSPEED = 4;
    private static final int YSPEED = 4;
    private int width = 5;
    private int height = 5;

    private Direction dir = Direction.STOP;

    public Bullet(Tank tank) {
        this.tank = tank;
        this.x = tank.getX();
        this.y = tank.getY();
        this.dir = tank.getGunDir();
    }

    //子弹自己的draw方法
    public void draw(Graphics g, int offsetX, int offsetY) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        move();
        g.fillOval(offsetX + x, offsetY + y, width, height);
        g.setColor(c);
    }

    //子弹的移动方法
    private void move() {
//调试移动        System.out.println("U:"+bU+" D:"+bD+" L:"+bL+" R:"+bR+" dir:"+dir);
        switch (dir) {
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

}
