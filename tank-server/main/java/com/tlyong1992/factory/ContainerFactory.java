package com.tlyong1992.factory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

/**
 * USER：tangly
 * DATE：2017/6/22
 * TIME：14:39
 */
public class ContainerFactory {
    private static URL mainWindowUrl;
    static {
        mainWindowUrl = Thread.currentThread().getContextClassLoader().getResource("fx/server_mainWindow.fxml");
    }

    public static Parent getMainWindow() {
        try {
            return FXMLLoader.load(mainWindowUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
