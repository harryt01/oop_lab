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

import org.w3c.dom.html.HTMLDListElement;

public class HTMLDListElementImpl extends HTMLElementImpl implements HTMLDListElement {
    HTMLDListElementImpl(long peer) {
        super(peer);
    }

    static HTMLDListElement getImpl(long peer) {
        return (HTMLDListElement)create(peer);
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

}

