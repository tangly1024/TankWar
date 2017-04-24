package client;

import client.unit.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * USER：tangly
 * DATE：2017/4/21
 * TIME：10:40
 */
public class MainClient extends JFrame {

    public static final int WINDOWWIDTH = 800;
    public static final int WINDOWHEIGHT = 600;
    public static final String TITLE = "TankWar";
    Image offScreenImage = null;
    public int offsetY; //窗口边沿偏移值
    public int offsetX; //窗口边沿偏移值

    Tank myTank;


    public void initWindow() {
        this.setSize(WINDOWWIDTH, WINDOWHEIGHT);
        this.setTitle(TITLE);
        this.setVisible(true);
//        this.setResizable(false); //不可缩放窗口
        this.setLocation(300, 200);
        offsetY = WINDOWHEIGHT - this.getContentPane().getHeight();
        offsetX = WINDOWWIDTH - this.getContentPane().getWidth();
        myTank = new Tank(0, 0);
        //AddListener
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyAdapter());
        new Thread(new PaintThread()).start();
    }

    @Override
    public void paint(Graphics g) {

        if (offScreenImage == null) {
            offScreenImage = this.createImage(WINDOWWIDTH, WINDOWHEIGHT);
        }
        Graphics gImage = offScreenImage.getGraphics();
        //清屏
        Color c = gImage.getColor();
        gImage.setColor(Color.GREEN);
        gImage.fillRect(0,0,WINDOWWIDTH,WINDOWHEIGHT);
        gImage.setColor(c);
        drawMyTank(gImage);

        //将画布内容同步到屏幕上
        g.drawImage(offScreenImage, 0, 0, null);
    }

    void drawMyTank(Graphics g) {
        myTank.draw(g, offsetX, offsetY);
    }


    public static void main(String[] args) {
        MainClient mc = new MainClient();
        mc.initWindow();
    }

    private class PaintThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class KeyAdapter extends java.awt.event.KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            myTank.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }
    }

}
