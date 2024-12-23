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

import org.w3c.dom.html.HTMLMetaElement;

public class HTMLMetaElementImpl extends HTMLElementImpl implements HTMLMetaElement {
    HTMLMetaElementImpl(long peer) {
        super(peer);
    }

    static HTMLMetaElement getImpl(long peer) {
        return (HTMLMetaElement)create(peer);
    }


// Attributes
    @Override
    public String getContent() {
        return getContentImpl(getPeer());
    }
    native static String getContentImpl(long peer);

    @Override
    public void setContent(String value) {
        setContentImpl(getPeer(), value);
    }
    native static void setContentImpl(long peer, String value);

    @Override
    public String getHttpEquiv() {
        return getHttpEquivImpl(getPeer());
    }
    native static String getHttpEquivImpl(long peer);

    @Override
    public void setHttpEquiv(String value) {
        setHttpEquivImpl(getPeer(), value);
    }
    native static void setHttpEquivImpl(long peer, String value);

    @Override
    public String getName() {
        return getNameImpl(getPeer());
    }
    native static String getNameImpl(long peer);

    @Override
    public void setName(String value) {
        setNameImpl(getPeer(), value);
    }
    native static void setNameImpl(long peer, String value);

    @Override
    public String getScheme() {
        return getSchemeImpl(getPeer());
    }
    native static String getSchemeImpl(long peer);

    @Override
    public void setScheme(String value) {
        setSchemeImpl(getPeer(), value);
    }
    native static void setSchemeImpl(long peer, String value);

}

