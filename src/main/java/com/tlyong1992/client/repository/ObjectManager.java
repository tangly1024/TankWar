package com.tlyong1992.client.repository;

import com.tlyong1992.client.model.Tank;

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
    private List<Tank> enemyTankList; //对象内存
    private Tank myTank = null; //主坦克

    public Map<String, String> getParamMap() {
        return paramMap;
    }
    public List<Tank> getEnemyTankList() {
        return enemyTankList;
    }

    public void setEnemyTankList(List<Tank> enemyTankList) {
        this.enemyTankList = enemyTankList;
    }

    public Tank getMyTank() {
        return myTank;
    }

    public void setMyTank(Tank myTank) {
        this.myTank = myTank;
    }

    ObjectManager(){
        paramMap = new ConcurrentHashMap<>();
        enemyTankList = Collections.synchronizedList(new ArrayList());
    }

}
