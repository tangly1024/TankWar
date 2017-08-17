package com.tlyong1992.repository;

import com.tlyong1992.model.Client;

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
    private List<Client> clientList; //对象内存

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    ObjectManager() {
        paramMap = new ConcurrentHashMap<>();
        clientList = Collections.synchronizedList(new ArrayList<Client>());
    }

}
