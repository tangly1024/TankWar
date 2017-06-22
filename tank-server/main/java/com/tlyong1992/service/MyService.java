package com.tlyong1992.service;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

class MyService extends Service {
        @Override
        protected Task createTask() {
            return new Task(){  
                @Override  
                protected Object call() throws Exception {  
                    try {
                        System.out.println("测试........");
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }
                    return null;  
                }};  
        }  
    }  