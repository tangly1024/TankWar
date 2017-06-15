package com.tlyong1992.model;

import com.tlyong1992.constant.Dir;

/**
 * USER：tangly
 * DATE：2017/5/24
 * TIME：10:58
 */
public class EnemyTank extends BaseTank{

    public EnemyTank(boolean bGood, int x, int y, int tankMoveSpeedX, int tankMoveSpeedY, int tankWidth, int tankHeight, Dir dir) {
        super(bGood, x, y, tankMoveSpeedX, tankMoveSpeedY, tankWidth, tankHeight, dir);
    }

    //当前行走的步数
    private int stepCount = 0;
    public void countStep(){
        this.stepCount ++;
    }
    public void changeDir(Dir dir){
        this.changeGunDir(dir);
        this.dir = dir;
    }

    public int getStepCount() {
        return stepCount;
    }
    public void resetStepCount(){stepCount = 0;}
}
