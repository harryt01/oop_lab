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

import org.w3c.dom.Node;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;

public class MouseEventImpl extends UIEventImpl implements MouseEvent {
    MouseEventImpl(long peer) {
        super(peer);
    }

    static MouseEvent getImpl(long peer) {
        return (MouseEvent)create(peer);
    }


// Attributes
    @Override
    public int getScreenX() {
        return getScreenXImpl(getPeer());
    }
    native static int getScreenXImpl(long peer);

    @Override
    public int getScreenY() {
        return getScreenYImpl(getPeer());
    }
    native static int getScreenYImpl(long peer);

    @Override
    public int getClientX() {
        return getClientXImpl(getPeer());
    }
    native static int getClientXImpl(long peer);

    @Override
    public int getClientY() {
        return getClientYImpl(getPeer());
    }
    native static int getClientYImpl(long peer);

    @Override
    public boolean getCtrlKey() {
        return getCtrlKeyImpl(getPeer());
    }
    native static boolean getCtrlKeyImpl(long peer);

    @Override
    public boolean getShiftKey() {
        return getShiftKeyImpl(getPeer());
    }
    native static boolean getShiftKeyImpl(long peer);

    @Override
    public boolean getAltKey() {
        return getAltKeyImpl(getPeer());
    }
    native static boolean getAltKeyImpl(long peer);

    @Override
    public boolean getMetaKey() {
        return getMetaKeyImpl(getPeer());
    }
    native static boolean getMetaKeyImpl(long peer);

    @Override
    public short getButton() {
        return getButtonImpl(getPeer());
    }
    native static short getButtonImpl(long peer);

    @Override
    public EventTarget getRelatedTarget() {
        return (EventTarget)NodeImpl.getImpl(getRelatedTargetImpl(getPeer()));
    }
    native static long getRelatedTargetImpl(long peer);

    public int getOffsetX() {
        return getOffsetXImpl(getPeer());
    }
    native static int getOffsetXImpl(long peer);

    public int getOffsetY() {
        return getOffsetYImpl(getPeer());
    }
    native static int getOffsetYImpl(long peer);

    public int getX() {
        return getXImpl(getPeer());
    }
    native static int getXImpl(long peer);

    public int getY() {
        return getYImpl(getPeer());
    }
    native static int getYImpl(long peer);

    public Node getFromElement() {
        return NodeImpl.getImpl(getFromElementImpl(getPeer()));
    }
    native static long getFromElementImpl(long peer);

    public Node getToElement() {
        return NodeImpl.getImpl(getToElementImpl(getPeer()));
    }
    native static long getToElementImpl(long peer);


// Functions
    @Override
    public void initMouseEvent(String type
        , boolean canBubble
        , boolean cancelable
        , AbstractView view
        , int detail
        , int screenX
        , int screenY
        , int clientX
        , int clientY
        , boolean ctrlKey
        , boolean altKey
        , boolean shiftKey
        , boolean metaKey
        , short button
        , EventTarget relatedTarget)
    {
        initMouseEventImpl(getPeer()
            , type
            , canBubble
            , cancelable
            , DOMWindowImpl.getPeer(view)
            , detail
            , screenX
            , screenY
            , clientX
            , clientY
            , ctrlKey
            , altKey
            , shiftKey
            , metaKey
            , button
            , NodeImpl.getPeer((NodeImpl)relatedTarget));
    }
    native static void initMouseEventImpl(long peer
        , String type
        , boolean canBubble
        , boolean cancelable
        , long view
        , int detail
        , int screenX
        , int screenY
        , int clientX
        , int clientY
        , boolean ctrlKey
        , boolean altKey
        , boolean shiftKey
        , boolean metaKey
        , short button
        , long relatedTarget);


}

