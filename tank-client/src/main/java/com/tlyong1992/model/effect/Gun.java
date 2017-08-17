package com.tlyong1992.model.effect;

import com.tlyong1992.constant.Dir;
import com.tlyong1992.model.tank.BaseTank;

import java.awt.*;

/**
 * 坦克炮筒
 */
public class Gun {

    public Gun(BaseTank baseTank) {
        this.baseTank = baseTank;
        gunDir = Dir.D;
    }

    private BaseTank baseTank;
    public Dir gunDir; //炮口方向

    public void draw(Graphics g) {
        switch (gunDir) {
            case L:
                g.drawLine(baseTank.getPositionX(), baseTank.getPositionY() + baseTank.getHeight() / 2, baseTank.getPositionX() + baseTank.getWidth() / 2, baseTank.getPositionY() + +baseTank.getHeight() / 2);
                break;
            case LU:
                g.drawLine((int) (baseTank.getPositionX() + (baseTank.getWidth() / 2) - (baseTank.getWidth() / 2) / Math.sqrt(2)) - 3, (int) (baseTank.getPositionY() + +(baseTank.getHeight() / 2) / Math.sqrt(2)) - 5, baseTank.getPositionX() + baseTank.getWidth() / 2, baseTank.getPositionY() + +baseTank.getHeight() / 2);
                break;
            case U:
                g.drawLine(baseTank.getPositionX() + (baseTank.getWidth() / 2), baseTank.getPositionY(), baseTank.getPositionX() + baseTank.getWidth() / 2, baseTank.getPositionY() + +baseTank.getHeight() / 2);
                break;
            case RU:
                g.drawLine((int) (baseTank.getPositionX() + (baseTank.getWidth() / 2) + (baseTank.getWidth() / 2) / Math.sqrt(2) + 3), (int) (baseTank.getPositionY() + +(baseTank.getHeight() / 2) / Math.sqrt(2)) - 5, baseTank.getPositionX() + baseTank.getWidth() / 2, baseTank.getPositionY() + +baseTank.getHeight() / 2);
                break;
            case R:
                g.drawLine(baseTank.getPositionX() + baseTank.getWidth(), baseTank.getPositionY() + +baseTank.getHeight() / 2, baseTank.getPositionX() + baseTank.getWidth() / 2, baseTank.getPositionY() + +baseTank.getHeight() / 2);
                break;
            case RD:
                g.drawLine((int) (baseTank.getPositionX() + (baseTank.getWidth() / 2) + (baseTank.getWidth() / 2) / Math.sqrt(2)), (int) (baseTank.getPositionY() + +baseTank.getHeight() / 2 + (baseTank.getHeight() / 2) / Math.sqrt(2) + 2), baseTank.getPositionX() + baseTank.getWidth() / 2, baseTank.getPositionY() + +baseTank.getHeight() / 2);
                break;
            case D:
                g.drawLine(baseTank.getPositionX() + baseTank.getWidth() / 2, baseTank.getPositionY() + +baseTank.getHeight(), baseTank.getPositionX() + baseTank.getWidth() / 2, baseTank.getPositionY() + +baseTank.getHeight() / 2);
                break;
            case LD:
                g.drawLine((int) (baseTank.getPositionX() + (baseTank.getWidth() / 2) - (baseTank.getWidth() / 2) / Math.sqrt(2)), (int) (baseTank.getPositionY() + +baseTank.getHeight() / 2 + (baseTank.getHeight() / 2) / Math.sqrt(2) + 2), baseTank.getPositionX() + baseTank.getWidth() / 2, baseTank.getPositionY() + +baseTank.getHeight() / 2);
                break;
            case STOP:
                break;
            default:
                break;
        }
    }
}