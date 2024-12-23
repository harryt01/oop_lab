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

import org.w3c.dom.views.AbstractView;

public class KeyboardEventImpl extends UIEventImpl {
    KeyboardEventImpl(long peer) {
        super(peer);
    }

    static KeyboardEventImpl getImpl(long peer) {
        return (KeyboardEventImpl)create(peer);
    }


// Constants
    public static final int KEY_LOCATION_STANDARD = 0x00;
    public static final int KEY_LOCATION_LEFT = 0x01;
    public static final int KEY_LOCATION_RIGHT = 0x02;
    public static final int KEY_LOCATION_NUMPAD = 0x03;

// Attributes
    public String getKeyIdentifier() {
        return getKeyIdentifierImpl(getPeer());
    }
    native static String getKeyIdentifierImpl(long peer);

    public int getLocation() {
        return getLocationImpl(getPeer());
    }
    native static int getLocationImpl(long peer);

    public int getKeyLocation() {
        return getKeyLocationImpl(getPeer());
    }
    native static int getKeyLocationImpl(long peer);

    public boolean getCtrlKey() {
        return getCtrlKeyImpl(getPeer());
    }
    native static boolean getCtrlKeyImpl(long peer);

    public boolean getShiftKey() {
        return getShiftKeyImpl(getPeer());
    }
    native static boolean getShiftKeyImpl(long peer);

    public boolean getAltKey() {
        return getAltKeyImpl(getPeer());
    }
    native static boolean getAltKeyImpl(long peer);

    public boolean getMetaKey() {
        return getMetaKeyImpl(getPeer());
    }
    native static boolean getMetaKeyImpl(long peer);

    public boolean getAltGraphKey() {
        return getAltGraphKeyImpl(getPeer());
    }
    native static boolean getAltGraphKeyImpl(long peer);

    @Override
    public int getKeyCode() {
        return getKeyCodeImpl(getPeer());
    }
    native static int getKeyCodeImpl(long peer);

    @Override
    public int getCharCode() {
        return getCharCodeImpl(getPeer());
    }
    native static int getCharCodeImpl(long peer);


// Functions
    public boolean getModifierState(String keyIdentifierArg)
    {
        return getModifierStateImpl(getPeer()
            , keyIdentifierArg);
    }
    native static boolean getModifierStateImpl(long peer
        , String keyIdentifierArg);


    public void initKeyboardEvent(String type
        , boolean canBubble
        , boolean cancelable
        , AbstractView view
        , String keyIdentifier
        , int location
        , boolean ctrlKey
        , boolean altKey
        , boolean shiftKey
        , boolean metaKey
        , boolean altGraphKey)
    {
        initKeyboardEventImpl(getPeer()
            , type
            , canBubble
            , cancelable
            , DOMWindowImpl.getPeer(view)
            , keyIdentifier
            , location
            , ctrlKey
            , altKey
            , shiftKey
            , metaKey
            , altGraphKey);
    }
    native static void initKeyboardEventImpl(long peer
        , String type
        , boolean canBubble
        , boolean cancelable
        , long view
        , String keyIdentifier
        , int location
        , boolean ctrlKey
        , boolean altKey
        , boolean shiftKey
        , boolean metaKey
        , boolean altGraphKey);


    public void initKeyboardEventEx(String type
        , boolean canBubble
        , boolean cancelable
        , AbstractView view
        , String keyIdentifier
        , int location
        , boolean ctrlKey
        , boolean altKey
        , boolean shiftKey
        , boolean metaKey)
    {
        initKeyboardEventExImpl(getPeer()
            , type
            , canBubble
            , cancelable
            , DOMWindowImpl.getPeer(view)
            , keyIdentifier
            , location
            , ctrlKey
            , altKey
            , shiftKey
            , metaKey);
    }
    native static void initKeyboardEventExImpl(long peer
        , String type
        , boolean canBubble
        , boolean cancelable
        , long view
        , String keyIdentifier
        , int location
        , boolean ctrlKey
        , boolean altKey
        , boolean shiftKey
        , boolean metaKey);


}

