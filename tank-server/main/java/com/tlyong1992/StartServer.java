package com.tlyong1992;

import com.tlyong1992.container.ComponentContainer;
import com.tlyong1992.events.MainEvent;
import com.tlyong1992.factory.ContainerFactory;
import com.tlyong1992.thread.AcceptThread;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * USER：tangly
 * DATE：2017/5/22
 * TIME：11:00
 */
public class StartServer extends Application {

    public static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(StartServer.class);

    @Override
    public void init() throws Exception {
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = ContainerFactory.getMainWindow();//布局
        ComponentContainer._MAIN_WINDOW = root;

        Scene scene = new Scene(root, 300, 275);
        stage.setTitle("TankWar-Server");
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
        MainEvent.stage = stage;
//运行接收线程
        new Thread(new AcceptThread()).start();

    }

    public static void main(String[] args) {
        logger.info("初始化服务器");
        launch();
    }
}
