package com.tlyong1992.client.factory;

import com.tlyong1992.client.constant.Constant;
import com.tlyong1992.client.model.BaseTank;
import com.tlyong1992.client.model.Bullet;

/**
 * USER：tangly
 * DATE：2017/5/27
 * TIME：13:50
 */
public class BulletFactory {

    public static Bullet buildMyBullet(BaseTank tank){
        Bullet bullet = new Bullet(tank, Constant.BULLET_MOVE_SPEED_X, Constant.BULLET_MOVE_SPEED_Y, Constant.BULLET_WIDTH, Constant.BULLET_HEIGHT);
        return bullet;
    }
}
