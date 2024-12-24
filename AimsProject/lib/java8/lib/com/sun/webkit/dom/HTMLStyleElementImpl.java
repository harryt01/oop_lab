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

import org.w3c.dom.html.HTMLStyleElement;
import org.w3c.dom.stylesheets.StyleSheet;

public class HTMLStyleElementImpl extends HTMLElementImpl implements HTMLStyleElement {
    HTMLStyleElementImpl(long peer) {
        super(peer);
    }

    static HTMLStyleElement getImpl(long peer) {
        return (HTMLStyleElement)create(peer);
    }


// Attributes
    @Override
    public boolean getDisabled() {
        return getDisabledImpl(getPeer());
    }
    native static boolean getDisabledImpl(long peer);

    @Override
    public void setDisabled(boolean value) {
        setDisabledImpl(getPeer(), value);
    }
    native static void setDisabledImpl(long peer, boolean value);

    @Override
    public String getMedia() {
        return getMediaImpl(getPeer());
    }
    native static String getMediaImpl(long peer);

    @Override
    public void setMedia(String value) {
        setMediaImpl(getPeer(), value);
    }
    native static void setMediaImpl(long peer, String value);

    @Override
    public String getType() {
        return getTypeImpl(getPeer());
    }
    native static String getTypeImpl(long peer);

    @Override
    public void setType(String value) {
        setTypeImpl(getPeer(), value);
    }
    native static void setTypeImpl(long peer, String value);

    public StyleSheet getSheet() {
        return StyleSheetImpl.getImpl(getSheetImpl(getPeer()));
    }
    native static long getSheetImpl(long peer);

}

