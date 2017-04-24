package client.unit;

/**
 * USER：tangly
 * DATE：2017/4/24
 * TIME：15:12
 */
public class GameUnit {
    protected int x;
    protected int y;

    //处理物体移动的函数
    protected void move(Direction dir, int xSpeed, int ySpeed) {
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
