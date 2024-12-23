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

import org.w3c.dom.html.HTMLScriptElement;

public class HTMLScriptElementImpl extends HTMLElementImpl implements HTMLScriptElement {
    HTMLScriptElementImpl(long peer) {
        super(peer);
    }

    static HTMLScriptElement getImpl(long peer) {
        return (HTMLScriptElement)create(peer);
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

    @Override
    public String getEvent() {
        return getEventImpl(getPeer());
    }
    native static String getEventImpl(long peer);

    @Override
    public void setEvent(String value) {
        setEventImpl(getPeer(), value);
    }
    native static void setEventImpl(long peer, String value);

    @Override
    public String getCharset() {
        return getCharsetImpl(getPeer());
    }
    native static String getCharsetImpl(long peer);

    @Override
    public void setCharset(String value) {
        setCharsetImpl(getPeer(), value);
    }
    native static void setCharsetImpl(long peer, String value);

    public boolean getAsync() {
        return getAsyncImpl(getPeer());
    }
    native static boolean getAsyncImpl(long peer);

    public void setAsync(boolean value) {
        setAsyncImpl(getPeer(), value);
    }
    native static void setAsyncImpl(long peer, boolean value);

    @Override
    public boolean getDefer() {
        return getDeferImpl(getPeer());
    }
    native static boolean getDeferImpl(long peer);

    @Override
    public void setDefer(boolean value) {
        setDeferImpl(getPeer(), value);
    }
    native static void setDeferImpl(long peer, boolean value);

    @Override
    public String getSrc() {
        return getSrcImpl(getPeer());
    }
    native static String getSrcImpl(long peer);

    @Override
    public void setSrc(String value) {
        setSrcImpl(getPeer(), value);
    }
    native static void setSrcImpl(long peer, String value);

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

    public String getCrossOrigin() {
        return getCrossOriginImpl(getPeer());
    }
    native static String getCrossOriginImpl(long peer);

    public void setCrossOrigin(String value) {
        setCrossOriginImpl(getPeer(), value);
    }
    native static void setCrossOriginImpl(long peer, String value);

}

