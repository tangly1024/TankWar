package client;

import javax.swing.*;
import java.awt.*;
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
    public int startY;
    public int startX;

    public int tankX = 0;
    public int tankY = 0;


    public void initWindow(){
        this.setSize(WINDOWWIDTH, WINDOWHEIGHT);
        this.setTitle(TITLE);
        this.setVisible(true);
//        this.setResizable(false); //不可缩放窗口
        this.setLocation(300,200);
        startY = WINDOWHEIGHT - this.getContentPane().getHeight();
        startX = WINDOWWIDTH - this.getContentPane().getWidth();

        //AddListener
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        new Thread(new PaintThread()).start();
        new Thread().start();
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        //清屏
        g.clearRect(0,0,WINDOWWIDTH,WINDOWHEIGHT);
        drawMyTank(g);
        g.setColor(c);
    }

    void drawMyTank(Graphics g){
        //每次调取
tankX ++;
tankY ++;

        g.setColor(Color.BLUE);
        g.fillOval(startX + tankX, startY + tankY,30,30);
    }


    public static void main(String[] args) {
        MainClient mc = new MainClient();
        mc.initWindow();
    }

    private class PaintThread implements Runnable{

        @Override
        public void run() {
            while (true){
                repaint();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
