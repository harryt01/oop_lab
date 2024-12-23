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

import org.w3c.dom.Document;
import org.w3c.dom.html.HTMLIFrameElement;
import org.w3c.dom.views.AbstractView;

public class HTMLIFrameElementImpl extends HTMLElementImpl implements HTMLIFrameElement {
    HTMLIFrameElementImpl(long peer) {
        super(peer);
    }

    static HTMLIFrameElement getImpl(long peer) {
        return (HTMLIFrameElement)create(peer);
    }


// Attributes
    @Override
    public String getAlign() {
        return getAlignImpl(getPeer());
    }
    native static String getAlignImpl(long peer);

    @Override
    public void setAlign(String value) {
        setAlignImpl(getPeer(), value);
    }
    native static void setAlignImpl(long peer, String value);

    @Override
    public String getFrameBorder() {
        return getFrameBorderImpl(getPeer());
    }
    native static String getFrameBorderImpl(long peer);

    @Override
    public void setFrameBorder(String value) {
        setFrameBorderImpl(getPeer(), value);
    }
    native static void setFrameBorderImpl(long peer, String value);

    @Override
    public String getHeight() {
        return getHeightImpl(getPeer());
    }
    native static String getHeightImpl(long peer);

    @Override
    public void setHeight(String value) {
        setHeightImpl(getPeer(), value);
    }
    native static void setHeightImpl(long peer, String value);

    @Override
    public String getLongDesc() {
        return getLongDescImpl(getPeer());
    }
    native static String getLongDescImpl(long peer);

    @Override
    public void setLongDesc(String value) {
        setLongDescImpl(getPeer(), value);
    }
    native static void setLongDescImpl(long peer, String value);

    @Override
    public String getMarginHeight() {
        return getMarginHeightImpl(getPeer());
    }
    native static String getMarginHeightImpl(long peer);

    @Override
    public void setMarginHeight(String value) {
        setMarginHeightImpl(getPeer(), value);
    }
    native static void setMarginHeightImpl(long peer, String value);

    @Override
    public String getMarginWidth() {
        return getMarginWidthImpl(getPeer());
    }
    native static String getMarginWidthImpl(long peer);

    @Override
    public void setMarginWidth(String value) {
        setMarginWidthImpl(getPeer(), value);
    }
    native static void setMarginWidthImpl(long peer, String value);

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
    public String getScrolling() {
        return getScrollingImpl(getPeer());
    }
    native static String getScrollingImpl(long peer);

    @Override
    public void setScrolling(String value) {
        setScrollingImpl(getPeer(), value);
    }
    native static void setScrollingImpl(long peer, String value);

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

    public String getSrcdoc() {
        return getSrcdocImpl(getPeer());
    }
    native static String getSrcdocImpl(long peer);

    public void setSrcdoc(String value) {
        setSrcdocImpl(getPeer(), value);
    }
    native static void setSrcdocImpl(long peer, String value);

    @Override
    public String getWidth() {
        return getWidthImpl(getPeer());
    }
    native static String getWidthImpl(long peer);

    @Override
    public void setWidth(String value) {
        setWidthImpl(getPeer(), value);
    }
    native static void setWidthImpl(long peer, String value);

    @Override
    public Document getContentDocument() {
        return DocumentImpl.getImpl(getContentDocumentImpl(getPeer()));
    }
    native static long getContentDocumentImpl(long peer);

    public AbstractView getContentWindow() {
        return DOMWindowImpl.getImpl(getContentWindowImpl(getPeer()));
    }
    native static long getContentWindowImpl(long peer);

}

