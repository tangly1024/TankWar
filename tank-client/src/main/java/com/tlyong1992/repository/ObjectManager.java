package com.tlyong1992.repository;

import com.tlyong1992.model.*;

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

    private List<Bullet> bulletList;//子弹列表

    private List<Explore> exploreList;//爆炸列表

    private MyTank myTank = null; //主坦克

    public Map<String, String> getParamMap() {
        return paramMap;
    }
    public List<EnemyTank> getEnemyTankList() {
        return enemyTankList;
    }
    public List<Explore> getExploreList() {
        return exploreList;
    }
    public List<Bullet> getBulletList() {
        return bulletList;
    }
    public MyTank getMyTank() {
        return myTank;
    }

    public void setMyTank(MyTank myTank) {
        this.myTank = myTank;
    }

    ObjectManager(){
        paramMap = new ConcurrentHashMap<>();
        enemyTankList = Collections.synchronizedList(new ArrayList<EnemyTank>());
        bulletList = Collections.synchronizedList(new ArrayList<Bullet>());
        exploreList = Collections.synchronizedList(new ArrayList<Explore>());
    }


}
