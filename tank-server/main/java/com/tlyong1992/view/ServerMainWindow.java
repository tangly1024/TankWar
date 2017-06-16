package com.tlyong1992.view;/**
 * USER：tangly
 * DATE：2017/6/16
 * TIME：16:35
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ServerMainWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("fx/fxml_example.fxml"));
            Scene scene = new Scene(root, 300, 275);
            primaryStage.setTitle("TankWar-Server");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(){
        launch();
    }


    @FXML
    private TextArea actiontarget;
    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText(actiontarget.getText()+"Sign in button pressed\n");
    }

    public TextArea getActiontarget() {
        return actiontarget;
    }

    public void setActiontarget(TextArea actiontarget) {
        this.actiontarget = actiontarget;
    }
}
