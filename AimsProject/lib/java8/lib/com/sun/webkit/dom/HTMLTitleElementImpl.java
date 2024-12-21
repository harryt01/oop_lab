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

import org.w3c.dom.html.HTMLTitleElement;

public class HTMLTitleElementImpl extends HTMLElementImpl implements HTMLTitleElement {
    HTMLTitleElementImpl(long peer) {
        super(peer);
    }

    static HTMLTitleElement getImpl(long peer) {
        return (HTMLTitleElement)create(peer);
    }


// Attributes
    @Override
    public String getText() {
        return getTextImpl(getPeer());
    }
    native static String getTextImpl(long peer);

    @Override
    public void setText(String value) {
        setTextImpl(getPeer(), value);
    }
    native static void setTextImpl(long peer, String value);

}

