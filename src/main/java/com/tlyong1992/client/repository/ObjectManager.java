package com.tlyong1992.client.repository;

import com.tlyong1992.client.model.BaseTank;
import com.tlyong1992.client.model.EnemyTank;

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
    private Map<String, String> paramMap; //参数内存
    private List<EnemyTank> enemyTankList; //对象内存
    private BaseTank myTank = null; //主坦克

    public Map<String, String> getParamMap() {
        return paramMap;
    }
    public List<EnemyTank> getEnemyTankList() {
        return enemyTankList;
    }

    public BaseTank getMyTank() {
        return myTank;
    }

    public void setMyTank(BaseTank myTank) {
        this.myTank = myTank;
    }

    ObjectManager(){
        paramMap = new ConcurrentHashMap<>();
        enemyTankList = Collections.synchronizedList(new ArrayList());
    }

}
