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

import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLMapElement;

public class HTMLMapElementImpl extends HTMLElementImpl implements HTMLMapElement {
    HTMLMapElementImpl(long peer) {
        super(peer);
    }

    static HTMLMapElement getImpl(long peer) {
        return (HTMLMapElement)create(peer);
    }


// Attributes
    @Override
    public HTMLCollection getAreas() {
        return HTMLCollectionImpl.getImpl(getAreasImpl(getPeer()));
    }
    native static long getAreasImpl(long peer);

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

}

