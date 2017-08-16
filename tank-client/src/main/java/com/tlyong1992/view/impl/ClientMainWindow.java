/*
 * Created by JFormDesigner on Wed Aug 16 14:57:47 CST 2017
 */

package com.tlyong1992.view.impl;

import com.tlyong1992.view.MainView;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * @author tang
 */
@org.springframework.stereotype.Component
public class ClientMainWindow extends JFrame implements MainView {

    @Override
    public void initWindow() {
        initComponents();
        setVisible(true);
        gamePanel = new GamePanel(this);//强制覆盖原有的panel用自定义的
    }

    @Override
    public void paint(Graphics g) {
        gamePanel.paint(g);
        super.paint(g);
    }

    @Override
    public JPanel getGamePanel() {
        return gamePanel;
    }

    @Override
    public JTextArea getTextArea() {
        return textArea1;
    }

    @Override
    public void resize() {
        ((GamePanel)gamePanel).doResize();
    }

    @Override
    public void drawBackground(Graphics gImage) {

    }

    @Override
    public void drawObject(Graphics g) {

    }

    @Override
    public int getOffsetY() {
        return 0;
    }

    @Override
    public int getOffsetX() {
        return 0;
    }

    @Override
    public int getTitleBsrHeight() {
        return 0;
    }

    @Override
    public void showLog(final Logger logger, final Object o) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                logger.info(o.toString());
                textArea1.setText(textArea1.getText() + "[系 统] " + o + "\n");
            }
        });
    }


    /**
     * 测试窗口
     * @param args
     */
    public static void main(String[] args) {
        ClientMainWindow cmw = new ClientMainWindow();
        cmw.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        gamePanel = new JPanel();

        //======== this ========
        setMinimumSize(new Dimension(800, 600));
        setTitle("\u5ba2\u6237\u7aef");
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(1, 1));

        //======== panel1 ========
        {
            panel1.setPreferredSize(new Dimension(800, 600));
            panel1.setLayout(new BorderLayout());

            //======== scrollPane1 ========
            {

                //---- textArea1 ----
                textArea1.setRows(3);
                textArea1.setMinimumSize(new Dimension(0, 100));
                scrollPane1.setViewportView(textArea1);
            }
            panel1.add(scrollPane1, BorderLayout.SOUTH);

            //======== gamePanel ========
            {
                gamePanel.setLayout(new BorderLayout());
            }
            panel1.add(gamePanel, BorderLayout.CENTER);
        }
        contentPane.add(panel1);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JPanel gamePanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
