/*
 * Copyright (c) 2010, 2022, Oracle and/or its affiliates. All rights reserved.
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
package com.sun.glass.ui;

import java.nio.IntBuffer;

import java.security.AccessController;
import java.security.AllPermission;
import java.security.Permission;

public abstract class Robot {

    private final static Permission allPermission = new AllPermission();

    final static public int MOUSE_LEFT_BTN   = 1;
    final static public int MOUSE_RIGHT_BTN  = 2;
    final static public int MOUSE_MIDDLE_BTN = 4;

    protected abstract void _create();
    protected Robot() {
        // Fix for RT-22633
        // Ideally, we should restrict user access to Glass packages at all,
        // but that's not the case right now. To prevent applications from
        // using Robot, we check for AllPermission here as we don't have
        // (and will unlikely have in the future) specific permission for
        // this functionality
        final SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(allPermission);
        }
        Application.checkEventThread();
        _create();
    }

    protected abstract void _destroy();
    public void destroy() {
        Application.checkEventThread();
        _destroy();
    }

    protected abstract void _keyPress(int code);
    /**
     * Generate a key pressed event.
     * @param code key code for this event
     */
    public void keyPress(int code) {
        Application.checkEventThread();
        _keyPress(code);
    }

    protected abstract void _keyRelease(int code);
    /**
     * Generate a key released event.
     *
     * @param code key code for this event
     */
    public void keyRelease(int code) {
        Application.checkEventThread();
        _keyRelease(code);
    }

    protected abstract void _mouseMove(int x, int y);
    /**
     * Generate a mouse moved event.
     *
     * @param x screen coordinate x
     * @param y screen coordinate y
     */
    public void mouseMove(int x, int y) {
        Application.checkEventThread();
        _mouseMove(x, y);
    }

    protected abstract void _mousePress(int buttons);
    /**
     * Generate a mouse press event with specified buttons mask.
     *
     * Up to 32-buttons mice are supported. Other buttons are inaccessible
     * by the robot. Bits 0, 1, and 2 mean LEFT, RIGHT, and MIDDLE mouse buttons
     * respectively.
     *
     * @param buttons buttons to have generated the event
     */
    public void mousePress(int buttons) {
        Application.checkEventThread();
        _mousePress(buttons);
    }

    protected abstract void _mouseRelease(int buttons);
    /**
     * Generate a mouse release event with specified buttons mask.
     *
     * @param buttons buttons to have generated the event
     */
    public void mouseRelease(int buttons) {
        Application.checkEventThread();
        _mouseRelease(buttons);
    }

    protected abstract void _mouseWheel(int wheelAmt);
    /**
     * Generate a mouse wheel event.
     *
     * @param wheelAmt amount the wheel has turned of wheel turning
     */
    public void mouseWheel(int wheelAmt) {
        Application.checkEventThread();
        _mouseWheel(wheelAmt);
    }

    protected abstract int _getMouseX();
    public int getMouseX() {
        Application.checkEventThread();
        return _getMouseX();
    }

    protected abstract int _getMouseY();
    public int getMouseY() {
        Application.checkEventThread();
        return _getMouseY();
    }

    protected abstract int _getPixelColor(int x, int y);
    /**
     * Returns pixel color at specified screen coordinates in IntARGB format.
     */
    public int getPixelColor(int x, int y) {
        Application.checkEventThread();
        return _getPixelColor(x, y);
    }

    protected abstract Pixels _getScreenCapture(int x, int y, int width, int height, boolean isHiDPI);
    /**
     * Returns a capture of the specified rectangular area of the screen.
     *
     * If {@code isHiDPI} argument is {@code true}, the returned Pixels object
     * dimensions may differ from the requested {@code width} and {@code
     * height} depending on how many physical pixels the area occupies on the
     * screen.  E.g. in HiDPI mode on the Mac (aka Retina display) the pixels
     * are doubled, and thus a screen capture of an area of size (10x10) pixels
     * will result in a Pixels object with dimensions (20x20). Calling code
     * should use the returned objects's getWidth() and getHeight() methods
     * to determine the image size.
     *
     * If (@code isHiDPI) is {@code false}, the returned Pixels object is of
     * the requested size. Note that in this case the image may be scaled in
     * order to fit to the requested dimensions if running on a HiDPI display.
     */
    public Pixels getScreenCapture(int x, int y, int width, int height, boolean isHiDPI) {
        Application.checkEventThread();

        if (width <= 0) {
            throw new IllegalArgumentException("width must be > 0");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("height must be > 0");
        }
        if (width >= (Integer.MAX_VALUE / height)) {
            throw new IllegalArgumentException("invalid capture size");
        }

        return _getScreenCapture(x, y, width, height, isHiDPI);
    }

    /**
     * Returns a capture of the specified area of the screen.
     * It is equivalent to calling getScreenCapture(x, y, width, height, false),
     * i.e. this method takes a "LowDPI" screen shot.
     */
    public Pixels getScreenCapture(int x, int y, int width, int height) {
        return getScreenCapture(x, y, width, height, false);
    }
}
