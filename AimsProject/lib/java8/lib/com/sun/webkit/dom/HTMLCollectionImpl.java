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
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLCollection;

public class HTMLCollectionImpl implements HTMLCollection {
    private static class SelfDisposer implements DisposerRecord {
        private final long peer;
        SelfDisposer(final long peer) {
            this.peer = peer;
        }

        @Override
        public void dispose() {
            HTMLCollectionImpl.dispose(peer);
        }
    }

    HTMLCollectionImpl(long peer) {
        this.peer = peer;
        Disposer.addRecord(this, new SelfDisposer(peer));
    }

    static HTMLCollection create(long peer) {
        if (peer == 0L) return null;
        switch (HTMLCollectionImpl.getCPPTypeImpl(peer)) {
        case TYPE_HTMLOptionsCollection: return new HTMLOptionsCollectionImpl(peer);
        }
        return new HTMLCollectionImpl(peer);
    }

    private final long peer;

    long getPeer() {
        return peer;
    }

    @Override public boolean equals(Object that) {
        return (that instanceof HTMLCollectionImpl) && (peer == ((HTMLCollectionImpl)that).peer);
    }

    @Override public int hashCode() {
        long p = peer;
        return (int) (p ^ (p >> 17));
    }

    static long getPeer(HTMLCollection arg) {
        return (arg == null) ? 0L : ((HTMLCollectionImpl)arg).getPeer();
    }

    native private static void dispose(long peer);

    private static final int TYPE_HTMLOptionsCollection = 1;
    native private static int getCPPTypeImpl(long peer);

    static HTMLCollection getImpl(long peer) {
        return (HTMLCollection)create(peer);
    }


// Attributes
    @Override
    public int getLength() {
        return getLengthImpl(getPeer());
    }
    native static int getLengthImpl(long peer);


// Functions
    @Override
   public Node item(int index)
    {
        return NodeImpl.getImpl(itemImpl(getPeer()
            , index));
    }
    native static long itemImpl(long peer
        , int index);


    @Override
    public Node namedItem(String name)
    {
        return NodeImpl.getImpl(namedItemImpl(getPeer()
            , name));
    }
    native static long namedItemImpl(long peer
        , String name);


}

