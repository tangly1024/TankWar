package com.tlyong1992.factory;

import com.tlyong1992.constant.Constant;
import com.tlyong1992.model.BaseTank;
import com.tlyong1992.model.Bullet;

/**
 * USER：tangly
 * DATE：2017/5/27
 * TIME：13:50
 */
public class BulletFactory {

    public static Bullet buildDefaultBullet(BaseTank tank){
        Bullet bullet = new Bullet(tank, Constant.BULLET_MOVE_SPEED_X, Constant.BULLET_MOVE_SPEED_Y, Constant.BULLET_WIDTH, Constant.BULLET_HEIGHT);
        return bullet;
    }
}
