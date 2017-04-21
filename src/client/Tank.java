package client;

import java.awt.*;
import java.awt.event.KeyEvent;

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


    private Direction dir = Direction.STOP;
    private boolean bU = false;
    private boolean bL = false;
    private boolean bR = false;
    private boolean bD = false;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g, int startX, int startY) {
        Color c = g.getColor();
        g.setColor(Color.BLUE);
        move();
        g.fillOval(startX + x, startY + y, width, height);
        g.setColor(c);
    }

    private void move() {
        System.out.println("U:"+bU+" D:"+bD+" L:"+bL+" R:"+bR+" dir:"+dir);
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
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            bR = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            bL = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            bU = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            bD = true;
        }
        locateDirection();
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            bR = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            bL = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            bU = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            bD = false;
        }
        locateDirection();
    }

    enum Direction {L,LU,U,RU,R,RD,D,LD,STOP}
}
