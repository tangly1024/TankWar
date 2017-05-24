package com.tlyong1992.client.model;

import com.tlyong1992.client.constant.Direction;

/**
 * USER：tangly
 * DATE：2017/5/24
 * TIME：10:58
 */
public class MyTank extends BaseTank {
    public MyTank(boolean bGood, int x, int y, int tankMoveSpeedX, int tankMoveSpeedY, int tankWidth, int tankHeight, Direction dir) {
        super(bGood, x, y, tankMoveSpeedX, tankMoveSpeedY, tankWidth, tankHeight, dir);
    }
}
