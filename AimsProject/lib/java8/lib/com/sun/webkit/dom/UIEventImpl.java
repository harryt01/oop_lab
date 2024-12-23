/*
 * Copyright (c) 2013, 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.webkit.dom;

import org.w3c.dom.events.UIEvent;
import org.w3c.dom.views.AbstractView;

public class UIEventImpl extends EventImpl implements UIEvent {
    UIEventImpl(long peer) {
        super(peer);
    }

    static UIEvent getImpl(long peer) {
        return (UIEvent)create(peer);
    }


// Attributes
    @Override
    public AbstractView getView() {
        return DOMWindowImpl.getImpl(getViewImpl(getPeer()));
    }
    native static long getViewImpl(long peer);

    @Override
    public int getDetail() {
        return getDetailImpl(getPeer());
    }
    native static int getDetailImpl(long peer);

    public int getKeyCode() {
        return getKeyCodeImpl(getPeer());
    }
    native static int getKeyCodeImpl(long peer);

    public int getCharCode() {
        return getCharCodeImpl(getPeer());
    }
    native static int getCharCodeImpl(long peer);

    public int getLayerX() {
        return getLayerXImpl(getPeer());
    }
    native static int getLayerXImpl(long peer);

    public int getLayerY() {
        return getLayerYImpl(getPeer());
    }
    native static int getLayerYImpl(long peer);

    public int getPageX() {
        return getPageXImpl(getPeer());
    }
    native static int getPageXImpl(long peer);

    public int getPageY() {
        return getPageYImpl(getPeer());
    }
    native static int getPageYImpl(long peer);

    public int getWhich() {
        return getWhichImpl(getPeer());
    }
    native static int getWhichImpl(long peer);


// Functions
    @Override
    public void initUIEvent(String type
        , boolean canBubble
        , boolean cancelable
        , AbstractView view
        , int detail)
    {
        initUIEventImpl(getPeer()
            , type
            , canBubble
            , cancelable
            , DOMWindowImpl.getPeer(view)
            , detail);
    }
    native static void initUIEventImpl(long peer
        , String type
        , boolean canBubble
        , boolean cancelable
        , long view
        , int detail);


}

