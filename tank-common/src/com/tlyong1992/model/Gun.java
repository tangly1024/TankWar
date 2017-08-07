package com.tlyong1992.model;

import com.tlyong1992.constant.Dir;

import java.awt.*;

class Gun {

    Gun(BaseTank baseTank) {
        this.baseTank = baseTank;
        gunDir = Dir.D;
    }

    private BaseTank baseTank;
    Dir gunDir; //炮口方向

    void draw(Graphics g) {
        switch (gunDir) {
            case L:
                g.drawLine(baseTank.positionX, baseTank.positionY + baseTank.height / 2, baseTank.positionX + baseTank.width / 2, baseTank.positionY + +baseTank.height / 2);
                break;
            case LU:
                g.drawLine((int) (baseTank.positionX + (baseTank.width / 2) - (baseTank.width / 2) / Math.sqrt(2)) - 3, (int) (baseTank.positionY + +(baseTank.height / 2) / Math.sqrt(2)) - 5, baseTank.positionX + baseTank.width / 2, baseTank.positionY + +baseTank.height / 2);
                break;
            case U:
                g.drawLine(baseTank.positionX + (baseTank.width / 2), baseTank.positionY, baseTank.positionX + baseTank.width / 2, baseTank.positionY + +baseTank.height / 2);
                break;
            case RU:
                g.drawLine((int) (baseTank.positionX + (baseTank.width / 2) + (baseTank.width / 2) / Math.sqrt(2) + 3), (int) (baseTank.positionY + +(baseTank.height / 2) / Math.sqrt(2)) - 5, baseTank.positionX + baseTank.width / 2, baseTank.positionY + +baseTank.height / 2);
                break;
            case R:
                g.drawLine(baseTank.positionX + baseTank.width, baseTank.positionY + +baseTank.height / 2, baseTank.positionX + baseTank.width / 2, baseTank.positionY + +baseTank.height / 2);
                break;
            case RD:
                g.drawLine((int) (baseTank.positionX + (baseTank.width / 2) + (baseTank.width / 2) / Math.sqrt(2)), (int) (baseTank.positionY + +baseTank.height / 2 + (baseTank.height / 2) / Math.sqrt(2) + 2), baseTank.positionX + baseTank.width / 2, baseTank.positionY + +baseTank.height / 2);
                break;
            case D:
                g.drawLine(baseTank.positionX + baseTank.width / 2, baseTank.positionY + +baseTank.height, baseTank.positionX + baseTank.width / 2, baseTank.positionY + +baseTank.height / 2);
                break;
            case LD:
                g.drawLine((int) (baseTank.positionX + (baseTank.width / 2) - (baseTank.width / 2) / Math.sqrt(2)), (int) (baseTank.positionY + +baseTank.height / 2 + (baseTank.height / 2) / Math.sqrt(2) + 2), baseTank.positionX + baseTank.width / 2, baseTank.positionY + +baseTank.height / 2);
                break;
            case STOP:
                break;
            default:
                break;
        }
    }
}