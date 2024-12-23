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

import org.w3c.dom.html.HTMLOListElement;

public class HTMLOListElementImpl extends HTMLElementImpl implements HTMLOListElement {
    HTMLOListElementImpl(long peer) {
        super(peer);
    }

    static HTMLOListElement getImpl(long peer) {
        return (HTMLOListElement)create(peer);
    }


// Attributes
    @Override
    public boolean getCompact() {
        return getCompactImpl(getPeer());
    }
    native static boolean getCompactImpl(long peer);

    @Override
    public void setCompact(boolean value) {
        setCompactImpl(getPeer(), value);
    }
    native static void setCompactImpl(long peer, boolean value);

    @Override
    public int getStart() {
        return getStartImpl(getPeer());
    }
    native static int getStartImpl(long peer);

    @Override
    public void setStart(int value) {
        setStartImpl(getPeer(), value);
    }
    native static void setStartImpl(long peer, int value);

    public boolean getReversed() {
        return getReversedImpl(getPeer());
    }
    native static boolean getReversedImpl(long peer);

    public void setReversed(boolean value) {
        setReversedImpl(getPeer(), value);
    }
    native static void setReversedImpl(long peer, boolean value);

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

}

