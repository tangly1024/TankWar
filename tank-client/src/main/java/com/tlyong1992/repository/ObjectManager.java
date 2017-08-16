package com.tlyong1992.repository;

import com.tlyong1992.constant.Param;
import com.tlyong1992.model.EnemyTank;
import com.tlyong1992.model.MyTank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 对象管理枚举单例
 */
public enum ObjectManager {
    singleTon;
    private Map<Param, Object> paramMap; //参数内存
    private List<EnemyTank> enemyTankList; //对象内存

    private MyTank myTank = null; //主坦克

    public Map<Param, Object> getParamMap() {
        return paramMap;
    }

    public List<EnemyTank> getEnemyTankList() {
        return enemyTankList;
    }


    public MyTank getMyTank() {
        return myTank;
    }

    public void setMyTank(MyTank myTank) {
        this.myTank = myTank;
    }

    ObjectManager() {
        paramMap = new ConcurrentHashMap<>();
        enemyTankList = Collections.synchronizedList(new ArrayList<EnemyTank>());
    }


}
