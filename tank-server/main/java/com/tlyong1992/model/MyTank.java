package com.tlyong1992.model;

import com.tlyong1992.constant.Dir;

/**
 * USER：tangly
 * DATE：2017/5/24
 * TIME：10:58
 */
public class MyTank extends BaseTank {
    public MyTank(boolean bGood, int x, int y, int tankMoveSpeedX, int tankMoveSpeedY, int tankWidth, int tankHeight, Dir dir) {
        super(bGood, x, y, tankMoveSpeedX, tankMoveSpeedY, tankWidth, tankHeight, dir);
    }
}
