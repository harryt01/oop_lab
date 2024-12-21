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
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;

public class DocumentTypeImpl extends NodeImpl implements DocumentType {
    DocumentTypeImpl(long peer) {
        super(peer);
    }

    static DocumentType getImpl(long peer) {
        return (DocumentType)create(peer);
    }


// Attributes
    @Override
    public String getName() {
        return getNameImpl(getPeer());
    }
    native static String getNameImpl(long peer);

    @Override
    public NamedNodeMap getEntities() {
        return NamedNodeMapImpl.getImpl(getEntitiesImpl(getPeer()));
    }
    native static long getEntitiesImpl(long peer);

    @Override
    public NamedNodeMap getNotations() {
        return NamedNodeMapImpl.getImpl(getNotationsImpl(getPeer()));
    }
    native static long getNotationsImpl(long peer);

    @Override
    public String getPublicId() {
        return getPublicIdImpl(getPeer());
    }
    native static String getPublicIdImpl(long peer);

    @Override
    public String getSystemId() {
        return getSystemIdImpl(getPeer());
    }
    native static String getSystemIdImpl(long peer);

    @Override
    public String getInternalSubset() {
        return getInternalSubsetImpl(getPeer());
    }
    native static String getInternalSubsetImpl(long peer);


// Functions
    public void remove() throws DOMException
    {
        removeImpl(getPeer());
    }
    native static void removeImpl(long peer);


}

