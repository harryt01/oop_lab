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

import com.sun.webkit.Disposer;
import com.sun.webkit.DisposerRecord;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class NamedNodeMapImpl implements NamedNodeMap {
    private static class SelfDisposer implements DisposerRecord {
        private final long peer;
        SelfDisposer(final long peer) {
            this.peer = peer;
        }

        @Override
        public void dispose() {
            NamedNodeMapImpl.dispose(peer);
        }
    }

    NamedNodeMapImpl(long peer) {
        this.peer = peer;
        Disposer.addRecord(this, new SelfDisposer(peer));
    }

    static NamedNodeMap create(long peer) {
        if (peer == 0L) return null;
        return new NamedNodeMapImpl(peer);
    }

    private final long peer;

    long getPeer() {
        return peer;
    }

    @Override public boolean equals(Object that) {
        return (that instanceof NamedNodeMapImpl) && (peer == ((NamedNodeMapImpl)that).peer);
    }

    @Override public int hashCode() {
        long p = peer;
        return (int) (p ^ (p >> 17));
    }

    static long getPeer(NamedNodeMap arg) {
        return (arg == null) ? 0L : ((NamedNodeMapImpl)arg).getPeer();
    }

    native private static void dispose(long peer);

    static NamedNodeMap getImpl(long peer) {
        return (NamedNodeMap)create(peer);
    }


// Attributes
    @Override
    public int getLength() {
        return getLengthImpl(getPeer());
    }
    native static int getLengthImpl(long peer);


// Functions
    @Override
    public Node getNamedItem(String name)
    {
        return NodeImpl.getImpl(getNamedItemImpl(getPeer()
            , name));
    }
    native static long getNamedItemImpl(long peer
        , String name);


    @Override
    public Node setNamedItem(Node node) throws DOMException
    {
        return NodeImpl.getImpl(setNamedItemImpl(getPeer()
            , NodeImpl.getPeer(node)));
    }
    native static long setNamedItemImpl(long peer
        , long node);


    @Override
    public Node removeNamedItem(String name) throws DOMException
    {
        return NodeImpl.getImpl(removeNamedItemImpl(getPeer()
            , name));
    }
    native static long removeNamedItemImpl(long peer
        , String name);


    @Override
    public Node item(int index)
    {
        return NodeImpl.getImpl(itemImpl(getPeer()
            , index));
    }
    native static long itemImpl(long peer
        , int index);


    @Override
    public Node getNamedItemNS(String namespaceURI
        , String localName)
    {
        return NodeImpl.getImpl(getNamedItemNSImpl(getPeer()
            , namespaceURI
            , localName));
    }
    native static long getNamedItemNSImpl(long peer
        , String namespaceURI
        , String localName);


    @Override
    public Node setNamedItemNS(Node node) throws DOMException
    {
        return NodeImpl.getImpl(setNamedItemNSImpl(getPeer()
            , NodeImpl.getPeer(node)));
    }
    native static long setNamedItemNSImpl(long peer
        , long node);


    @Override
    public Node removeNamedItemNS(String namespaceURI
        , String localName) throws DOMException
    {
        return NodeImpl.getImpl(removeNamedItemNSImpl(getPeer()
            , namespaceURI
            , localName));
    }
    native static long removeNamedItemNSImpl(long peer
        , String namespaceURI
        , String localName);


}

