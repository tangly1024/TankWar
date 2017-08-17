package com.tlyong1992.repository;

import com.tlyong1992.model.tank.BaseTank;
import com.tlyong1992.model.tank.MyTank;

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

    private List<BaseTank> otherTankList;

    private MyTank myTank = null; //主坦克

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public List<BaseTank> getOtherTanks() {
        return otherTankList;
    }


    public MyTank getMyTank() {
        return myTank;
    }

    public void setMyTank(MyTank myTank) {
        this.myTank = myTank;
    }

    ObjectManager() {
        paramMap = new ConcurrentHashMap<>();
        otherTankList = Collections.synchronizedList(new ArrayList<BaseTank>());
    }


}
