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

import org.w3c.dom.html.HTMLBaseFontElement;

public class HTMLBaseFontElementImpl extends HTMLElementImpl implements HTMLBaseFontElement {
    HTMLBaseFontElementImpl(long peer) {
        super(peer);
    }

    static HTMLBaseFontElement getImpl(long peer) {
        return (HTMLBaseFontElement)create(peer);
    }


// Attributes
    @Override
    public String getColor() {
        return getColorImpl(getPeer());
    }
    native static String getColorImpl(long peer);

    @Override
    public void setColor(String value) {
        setColorImpl(getPeer(), value);
    }
    native static void setColorImpl(long peer, String value);

    @Override
    public String getFace() {
        return getFaceImpl(getPeer());
    }
    native static String getFaceImpl(long peer);

    @Override
    public void setFace(String value) {
        setFaceImpl(getPeer(), value);
    }
    native static void setFaceImpl(long peer, String value);

    @Override
    public String getSize() {
        return getSizeImpl(getPeer())+"";
    }
    native static String getSizeImpl(long peer);

    @Override
    public void setSize(String value) {
        setSizeImpl(getPeer(), value);
    }
    native static void setSizeImpl(long peer, String value);

}

