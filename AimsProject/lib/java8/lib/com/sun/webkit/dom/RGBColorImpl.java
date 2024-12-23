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
import org.w3c.dom.css.RGBColor;

public class RGBColorImpl implements RGBColor {
    private static class SelfDisposer implements DisposerRecord {
        private final long peer;
        SelfDisposer(final long peer) {
            this.peer = peer;
        }

        @Override
        public void dispose() {
            RGBColorImpl.dispose(peer);
        }
    }

    RGBColorImpl(long peer) {
        this.peer = peer;
        Disposer.addRecord(this, new SelfDisposer(peer));
    }

    static RGBColor create(long peer) {
        if (peer == 0L) return null;
        return new RGBColorImpl(peer);
    }

    private final long peer;

    long getPeer() {
        return peer;
    }

    @Override public boolean equals(Object that) {
        return (that instanceof RGBColorImpl) && (peer == ((RGBColorImpl)that).peer);
    }

    @Override public int hashCode() {
        long p = peer;
        return (int) (p ^ (p >> 17));
    }

    static long getPeer(RGBColor arg) {
        return (arg == null) ? 0L : ((RGBColorImpl)arg).getPeer();
    }

    native private static void dispose(long peer);

    static RGBColor getImpl(long peer) {
        return (RGBColor)create(peer);
    }


// Attributes
    @Override
    public CSSPrimitiveValue getRed() {
        return CSSPrimitiveValueImpl.getImpl(getRedImpl(getPeer()));
    }
    native static long getRedImpl(long peer);

    @Override
    public CSSPrimitiveValue getGreen() {
        return CSSPrimitiveValueImpl.getImpl(getGreenImpl(getPeer()));
    }
    native static long getGreenImpl(long peer);

    @Override
    public CSSPrimitiveValue getBlue() {
        return CSSPrimitiveValueImpl.getImpl(getBlueImpl(getPeer()));
    }
    native static long getBlueImpl(long peer);

    public CSSPrimitiveValue getAlpha() {
        return CSSPrimitiveValueImpl.getImpl(getAlphaImpl(getPeer()));
    }
    native static long getAlphaImpl(long peer);

}

