/*
 * Copyright (c) 2011, 2020, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.sun.javafx.tk.quantum;

import com.sun.glass.ui.Application;
import com.sun.glass.ui.Window;
import com.sun.glass.ui.View;

import com.sun.javafx.tk.AppletWindow;
import com.sun.javafx.tk.TKStage;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import javafx.stage.Stage;

/**
 * Implementation class for AppletWindow using a Glass Window
 */
class GlassAppletWindow implements AppletWindow {
    private final Window glassWindow;
    private WeakReference<Stage> topStage;
    private String serverName;

    GlassAppletWindow(long nativeParent, String serverName) {
        if (0 == nativeParent) {
            if (serverName != null) {
                throw new RuntimeException("GlassAppletWindow constructor used incorrectly.");
            }
            glassWindow = Application.GetApplication().createWindow(null, Window.NORMAL);
        } else {
            this.serverName = serverName;
            glassWindow = Application.GetApplication().createWindow(nativeParent);
        }
    }

    Window getGlassWindow() {
        return glassWindow;
    }

    @Override
    public void setBackgroundColor(final int color) {
        Application.invokeLater(() -> {
            float RR = (float) ((color >> 16) & 0xff) / 255f;
            float GG = (float) ((color >> 8) & 0xff) / 255f;
            float BB = (float) (color & 0xff) / 255f;
            glassWindow.setBackground(RR, GG, BB);
        });
    }

    @Override
    public void setForegroundColor(int color) {
        // Do nothing, foreground color is not supported
    }

    @Override
    public void setVisible(final boolean state) {
        Application.invokeLater(() -> glassWindow.setVisible(state));
    }

    @Override
    public void setSize(final int width, final int height) {
        Application.invokeLater(() -> glassWindow.setSize(width, height));
    }

    @Override
    public int getWidth() {
        final AtomicInteger width = new AtomicInteger(0);
        Application.invokeAndWait(() -> width.set(glassWindow.getWidth()));
        return width.get();
    }

    @Override
    public int getHeight() {
        final AtomicInteger height = new AtomicInteger(0);
        Application.invokeAndWait(() -> height.set(glassWindow.getHeight()));
        return height.get();
    }

    @Override
    public void setPosition(final int x, final int y) {
        Application.invokeLater(() -> glassWindow.setPosition(x, y));
    }

    @Override
    public int getPositionX() {
        final AtomicInteger x = new AtomicInteger(0);
        Application.invokeAndWait(() -> x.set(glassWindow.getX()));
        return x.get();
    }

    @Override
    public int getPositionY() {
        final AtomicInteger y = new AtomicInteger(0);
        Application.invokeAndWait(() -> y.set(glassWindow.getY()));
        return y.get();
    }

    @Override
    public float getPlatformScaleX() {
        final AtomicReference<Float> pScale = new AtomicReference<Float>(0.0f);
        Application.invokeAndWait(() -> pScale.set(glassWindow.getPlatformScaleX()));
        return pScale.get();
    }

    @Override
    public float getPlatformScaleY() {
        final AtomicReference<Float> pScale = new AtomicReference<Float>(0.0f);
        Application.invokeAndWait(() -> pScale.set(glassWindow.getPlatformScaleY()));
        return pScale.get();
    }

    void dispose() {
        QuantumToolkit.runWithRenderLock(() -> {
            glassWindow.close();
            //TODO - should update glass scene view state
            //TODO - doesn't matter because we are disposing
            return null;
        });
    }

    @Override
    public void setStageOnTop(Stage topStage) {
        if (null != topStage) {
            this.topStage = new WeakReference<Stage>(topStage);
        } else {
            this.topStage = null;
        }
    }

    @Override
    public int getRemoteLayerId() {
        final AtomicInteger id = new AtomicInteger(-1);
        Application.invokeAndWait(() -> {
            View view = glassWindow.getView();
            if (view != null) {
                id.set(view.getNativeRemoteLayerId(serverName));
            }
        });
        return id.get();
    }

    @Override
    public void dispatchEvent(final Map eventInfo) {
        Application.invokeAndWait(() -> glassWindow.dispatchNpapiEvent(eventInfo));
    }

    /**
     * Call when a child stage becomes visible so we can make sure topStage
     * is pushed to the front where it should be.
     */
    void assertStageOrder() {
        if (null != topStage) {
            Stage ts = topStage.get();
            if (null != ts) {
                TKStage tsp = ts.impl_getPeer();
                if (tsp instanceof WindowStage && ((WindowStage)tsp).isVisible()) {
                    // call the underlying Glass window toFront to bypass
                    // the check in WindowStage.toFront or we'll create an
                    // infinite loop
                    Window pw = ((WindowStage)tsp).getPlatformWindow();
                    if (null != pw) {
                        pw.toFront();
                    }
                }
            }
        }
    }
}
