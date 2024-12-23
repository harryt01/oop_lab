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
import org.w3c.dom.DOMStringList;

public class DOMStringListImpl implements DOMStringList {
    private static class SelfDisposer implements DisposerRecord {
        private final long peer;
        SelfDisposer(final long peer) {
            this.peer = peer;
        }

        @Override
        public void dispose() {
            DOMStringListImpl.dispose(peer);
        }
    }

    DOMStringListImpl(long peer) {
        this.peer = peer;
        Disposer.addRecord(this, new SelfDisposer(peer));
    }

    static DOMStringList create(long peer) {
        if (peer == 0L) return null;
        return new DOMStringListImpl(peer);
    }

    private final long peer;

    long getPeer() {
        return peer;
    }

    @Override public boolean equals(Object that) {
        return (that instanceof DOMStringListImpl) && (peer == ((DOMStringListImpl)that).peer);
    }

    @Override public int hashCode() {
        long p = peer;
        return (int) (p ^ (p >> 17));
    }

    static long getPeer(DOMStringList arg) {
        return (arg == null) ? 0L : ((DOMStringListImpl)arg).getPeer();
    }

    native private static void dispose(long peer);

    static DOMStringList getImpl(long peer) {
        return (DOMStringList)create(peer);
    }


// Attributes
    @Override
    public int getLength() {
        return getLengthImpl(getPeer());
    }
    native static int getLengthImpl(long peer);


// Functions
    @Override
    public String item(int index)
    {
        return itemImpl(getPeer()
            , index);
    }
    native static String itemImpl(long peer
        , int index);


    @Override
    public boolean contains(String string)
    {
        return containsImpl(getPeer()
            , string);
    }
    native static boolean containsImpl(long peer
        , String string);


}

