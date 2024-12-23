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

import org.w3c.dom.DOMException;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLTableSectionElement;

public class HTMLTableSectionElementImpl extends HTMLElementImpl implements HTMLTableSectionElement {
    HTMLTableSectionElementImpl(long peer) {
        super(peer);
    }

    static HTMLTableSectionElement getImpl(long peer) {
        return (HTMLTableSectionElement)create(peer);
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
    public String getCh() {
        return getChImpl(getPeer());
    }
    native static String getChImpl(long peer);

    @Override
    public void setCh(String value) {
        setChImpl(getPeer(), value);
    }
    native static void setChImpl(long peer, String value);

    @Override
    public String getChOff() {
        return getChOffImpl(getPeer());
    }
    native static String getChOffImpl(long peer);

    @Override
    public void setChOff(String value) {
        setChOffImpl(getPeer(), value);
    }
    native static void setChOffImpl(long peer, String value);

    @Override
    public String getVAlign() {
        return getVAlignImpl(getPeer());
    }
    native static String getVAlignImpl(long peer);

    @Override
    public void setVAlign(String value) {
        setVAlignImpl(getPeer(), value);
    }
    native static void setVAlignImpl(long peer, String value);

    @Override
    public HTMLCollection getRows() {
        return HTMLCollectionImpl.getImpl(getRowsImpl(getPeer()));
    }
    native static long getRowsImpl(long peer);


// Functions
    @Override
    public HTMLElement insertRow(int index) throws DOMException
    {
        return HTMLElementImpl.getImpl(insertRowImpl(getPeer()
            , index));
    }
    native static long insertRowImpl(long peer
        , int index);


    @Override
    public void deleteRow(int index) throws DOMException
    {
        deleteRowImpl(getPeer()
            , index);
    }
    native static void deleteRowImpl(long peer
        , int index);


}

