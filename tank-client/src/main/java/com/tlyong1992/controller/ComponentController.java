package com.tlyong1992.controller;

import com.tlyong1992.view.impl.ClientMainWindow;
import org.springframework.stereotype.Component;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * USER：tangly
 * DATE：2017/8/16
 * TIME：16:30
 */
@Component
public class ComponentController extends ComponentAdapter {
    @Override
    public void componentResized(ComponentEvent e) {
        ClientMainWindow cmw = (ClientMainWindow) e.getComponent();
        cmw.resize();
    }
}
