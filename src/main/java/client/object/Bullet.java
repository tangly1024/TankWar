package client.object;

import java.awt.*;

/**
 * USER：tangly
 * DATE：2017/4/24
 * TIME：13:46
 */
public class Bullet extends GameObject {

    private client.object.Tank tank;//引用发出这颗子弹的坦克
    private int width = 5;
    private int height = 5;
    private int XSPEED = 4;
    private int YSPEED = 5;

    public Bullet(client.object.Tank tank) {
        super(tank.getX(),tank.getY(),4,5);
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




}
