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
import org.w3c.dom.css.CSSValue;

public class CSSValueImpl implements CSSValue {
    private static class SelfDisposer implements DisposerRecord {
        private final long peer;
        SelfDisposer(final long peer) {
            this.peer = peer;
        }

        @Override
        public void dispose() {
            CSSValueImpl.dispose(peer);
        }
    }

    CSSValueImpl(long peer) {
        this.peer = peer;
        Disposer.addRecord(this, new SelfDisposer(peer));
    }

    static CSSValue create(long peer) {
        if (peer == 0L) return null;
        switch (CSSValueImpl.getCssValueTypeImpl(peer)) {
        case CSS_PRIMITIVE_VALUE: return new CSSPrimitiveValueImpl(peer);
        case CSS_VALUE_LIST: return new CSSValueListImpl(peer);
        }
        return new CSSValueImpl(peer);
    }

    private final long peer;

    long getPeer() {
        return peer;
    }

    @Override public boolean equals(Object that) {
        return (that instanceof CSSValueImpl) && (peer == ((CSSValueImpl)that).peer);
    }

    @Override public int hashCode() {
        long p = peer;
        return (int) (p ^ (p >> 17));
    }

    static long getPeer(CSSValue arg) {
        return (arg == null) ? 0L : ((CSSValueImpl)arg).getPeer();
    }

    native private static void dispose(long peer);

    static CSSValue getImpl(long peer) {
        return (CSSValue)create(peer);
    }


// Constants
    public static final int CSS_INHERIT = 0;
    public static final int CSS_PRIMITIVE_VALUE = 1;
    public static final int CSS_VALUE_LIST = 2;
    public static final int CSS_CUSTOM = 3;

// Attributes
    @Override
    public String getCssText() {
        return getCssTextImpl(getPeer());
    }
    native static String getCssTextImpl(long peer);

    @Override
    public void setCssText(String value) throws DOMException {
        setCssTextImpl(getPeer(), value);
    }
    native static void setCssTextImpl(long peer, String value);

    @Override
    public short getCssValueType() {
        return getCssValueTypeImpl(getPeer());
    }
    native static short getCssValueTypeImpl(long peer);

}

