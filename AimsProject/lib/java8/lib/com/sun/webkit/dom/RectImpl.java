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
import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.Rect;

public class RectImpl implements Rect {
    private static class SelfDisposer implements DisposerRecord {
        private final long peer;
        SelfDisposer(final long peer) {
            this.peer = peer;
        }

        @Override
        public void dispose() {
            RectImpl.dispose(peer);
        }
    }

    RectImpl(long peer) {
        this.peer = peer;
        Disposer.addRecord(this, new SelfDisposer(peer));
    }

    static Rect create(long peer) {
        if (peer == 0L) return null;
        return new RectImpl(peer);
    }

    private final long peer;

    long getPeer() {
        return peer;
    }

    @Override public boolean equals(Object that) {
        return (that instanceof RectImpl) && (peer == ((RectImpl)that).peer);
    }

    @Override public int hashCode() {
        long p = peer;
        return (int) (p ^ (p >> 17));
    }

    static long getPeer(Rect arg) {
        return (arg == null) ? 0L : ((RectImpl)arg).getPeer();
    }

    native private static void dispose(long peer);

    static Rect getImpl(long peer) {
        return (Rect)create(peer);
    }


// Attributes
    @Override
    public CSSPrimitiveValue getTop() {
        return CSSPrimitiveValueImpl.getImpl(getTopImpl(getPeer()));
    }
    native static long getTopImpl(long peer);

    @Override
    public CSSPrimitiveValue getRight() {
        return CSSPrimitiveValueImpl.getImpl(getRightImpl(getPeer()));
    }
    native static long getRightImpl(long peer);

    @Override
    public CSSPrimitiveValue getBottom() {
        return CSSPrimitiveValueImpl.getImpl(getBottomImpl(getPeer()));
    }
    native static long getBottomImpl(long peer);

    @Override
    public CSSPrimitiveValue getLeft() {
        return CSSPrimitiveValueImpl.getImpl(getLeftImpl(getPeer()));
    }
    native static long getLeftImpl(long peer);

}

