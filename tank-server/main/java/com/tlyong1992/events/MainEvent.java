package com.tlyong1992.events;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * USER：tangly
 * DATE：2017/6/22
 * TIME：15:14
 */
public class MainEvent {

    public static Stage stage;

    @FXML
    public javafx.scene.control.TextArea testTextArea;

    public void test(ActionEvent actionEvent) {
        testTextArea.setText(testTextArea.getText() + "测试文字\n");
    }

    public final void addText(String text) {
        testTextArea.setText(testTextArea.getText() + text + "\n");
    }
}
