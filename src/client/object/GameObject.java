package client.object;

import client.constant.Direction;

/**
 * USER：tangly
 * DATE：2017/4/24
 * TIME：15:12
 */
public class GameObject {
    protected int x;
    protected int y;
    protected int xSpeed;
    protected int ySpeed;
    protected Direction dir = Direction.STOP;//移动方向

    public GameObject(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    //处理物体移动的函数
    public void move() {
        switch (dir) {
            case L:
                x -= xSpeed;
                break;
            case LU:
                x -= xSpeed;
                y -= ySpeed;
                break;
            case RU:
                x += xSpeed;
                y -= ySpeed;
                break;
            case R:
                x += xSpeed;
                break;
            case RD:
                x += xSpeed;
                y += ySpeed;
                break;
            case D:
                y += ySpeed;
                break;
            case LD:
                x -= xSpeed;
                y += ySpeed;
                break;
            case U:
                y -= ySpeed;
                break;
            case STOP:
                break;
        }
    }
}
