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

import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLFormElement;
import org.w3c.dom.html.HTMLLabelElement;

public class HTMLLabelElementImpl extends HTMLElementImpl implements HTMLLabelElement {
    HTMLLabelElementImpl(long peer) {
        super(peer);
    }

    static HTMLLabelElement getImpl(long peer) {
        return (HTMLLabelElement)create(peer);
    }


// Attributes
    @Override
    public HTMLFormElement getForm() {
        return HTMLFormElementImpl.getImpl(getFormImpl(getPeer()));
    }
    native static long getFormImpl(long peer);

    @Override
    public String getHtmlFor() {
        return getHtmlForImpl(getPeer());
    }
    native static String getHtmlForImpl(long peer);

    @Override
    public void setHtmlFor(String value) {
        setHtmlForImpl(getPeer(), value);
    }
    native static void setHtmlForImpl(long peer, String value);

    public HTMLElement getControl() {
        return HTMLElementImpl.getImpl(getControlImpl(getPeer()));
    }
    native static long getControlImpl(long peer);

    @Override
    public String getAccessKey() {
        return getAccessKeyImpl(getPeer());
    }
    native static String getAccessKeyImpl(long peer);

    @Override
    public void setAccessKey(String value) {
        setAccessKeyImpl(getPeer(), value);
    }
    native static void setAccessKeyImpl(long peer, String value);

}

