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

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.TypeInfo;

public class AttrImpl extends NodeImpl implements Attr {
    AttrImpl(long peer) {
        super(peer);
    }

    static Attr getImpl(long peer) {
        return (Attr)create(peer);
    }


// Attributes
    @Override
    public String getName() {
        return getNameImpl(getPeer());
    }
    native static String getNameImpl(long peer);

    @Override
    public boolean getSpecified() {
        return getSpecifiedImpl(getPeer());
    }
    native static boolean getSpecifiedImpl(long peer);

    @Override
    public String getValue() {
        return getValueImpl(getPeer());
    }
    native static String getValueImpl(long peer);

    @Override
    public void setValue(String value) throws DOMException {
        setValueImpl(getPeer(), value);
    }
    native static void setValueImpl(long peer, String value);

    @Override
    public Element getOwnerElement() {
        return ElementImpl.getImpl(getOwnerElementImpl(getPeer()));
    }
    native static long getOwnerElementImpl(long peer);

    @Override
    public boolean isId() {
        return isIdImpl(getPeer());
    }
    native static boolean isIdImpl(long peer);


//stubs
    @Override
    public TypeInfo getSchemaTypeInfo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

