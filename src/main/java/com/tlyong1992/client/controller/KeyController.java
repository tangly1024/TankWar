package com.tlyong1992.client.controller;

import com.tlyong1992.client.factory.TankFactory;
import com.tlyong1992.client.repository.ObjectManager;
import org.springframework.stereotype.Component;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * USER：tangly
 * DATE：2017/5/27
 * TIME：14:42
 */
@Component
public class KeyController extends KeyAdapter {


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT
                || e.getKeyCode() == KeyEvent.VK_LEFT
                || e.getKeyCode() == KeyEvent.VK_UP
                || e.getKeyCode() == KeyEvent.VK_DOWN
                || e.getKeyCode() == KeyEvent.VK_SPACE ) {
            ObjectManager.singleTon.getMyTank().keyPressed(e);
        }
        switch (e.getKeyCode()){
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_SPACE:
                ObjectManager.singleTon.getMyTank().keyPressed(e);
                break;
            case KeyEvent.VK_R:
                TankFactory.generateRandomEnemy();
                break;
            case KeyEvent.VK_E:
                ObjectManager.singleTon.getMyTank().setLive(true);
                break;
            default:return;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        ObjectManager.singleTon.getMyTank().keyReleased(e);
    }
}
