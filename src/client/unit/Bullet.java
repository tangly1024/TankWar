package client.unit;

import java.awt.*;

/**
 * USER：tangly
 * DATE：2017/4/24
 * TIME：13:46
 */
public class Bullet extends GameUnit {

    private Tank tank;//引用发出这颗子弹的坦克
    private int width = 5;
    private int height = 5;
    private int XSPEED = 4;
    private int YSPEED = 5;
    private Direction dir = Direction.D; // 移动方向

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
        move(dir,XSPEED,YSPEED);
        g.fillOval(offsetX + x, offsetY + y, width, height);
        g.setColor(c);
    }




}
