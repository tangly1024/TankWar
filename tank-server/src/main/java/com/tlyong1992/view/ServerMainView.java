package com.tlyong1992.view;

import com.tlyong1992.constant.ServerConstant;

import javax.swing.*;
import java.awt.*;

/**
 * USER：tangly
 * DATE：2017/4/21
 * TIME：10:40
 */
@org.springframework.stereotype.Component
public class ServerMainView extends JFrame {

    private int windowWidth = ServerConstant.WINDOW_WIDTH;
    private int windowHeight = ServerConstant.WINDOW_HEIGHT;
    private int windowPositionX = ServerConstant.WINDOW_POSITION_X;
    private int windowPositionY = ServerConstant.WINDOW_POSITION_Y;
    private String TITLE = ServerConstant.WINDOW_TITLE;

    private JTextArea textArea;

//    Image offScreenImage = null;

    public void initWindow() {

        textArea = new JTextArea();
        textArea.setAutoscrolls(true);

        this.setLayout(new BorderLayout());
        this.setFont(new Font("Helvetica", Font.PLAIN, 14));
//        this.getContentPane().add(new JButton("North"), "North");
        this.getContentPane().add(new JButton("South"), "South");
        this.getContentPane().add(new JButton("West"), "West");
        this.getContentPane().add(new JButton("East"), "East");
        this.getContentPane().add(textArea, "Center");
//        this.pack();

        JMenu menu = new JMenu("选项");     //创建JMenu菜单对象
        JMenuItem item1 = new JMenuItem("菜单1");  //菜单项
        JMenuItem item2 = new JMenuItem("菜单2");//菜单项
        menu.add(item1);   //将菜单项目添加到菜单
        menu.add(item2);    //将菜单项目添加到菜单
        JMenuBar menuBar = new JMenuBar();  //创建菜单工具栏
        menuBar.add(menu);      //将菜单增加到菜单工具栏
        this.setJMenuBar(menuBar);  //为窗体设置菜单工具栏

        this.setSize(windowWidth, windowHeight);
        this.setTitle(TITLE);
        this.setVisible(true);
//        this.setResizable(false); //不可缩放窗口
        this.setLocation(windowPositionX, windowPositionY);

    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
