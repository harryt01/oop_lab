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
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSRuleList;

public class CSSRuleListImpl implements CSSRuleList {
    private static class SelfDisposer implements DisposerRecord {
        private final long peer;
        SelfDisposer(final long peer) {
            this.peer = peer;
        }

        @Override
        public void dispose() {
            CSSRuleListImpl.dispose(peer);
        }
    }

    CSSRuleListImpl(long peer) {
        this.peer = peer;
        Disposer.addRecord(this, new SelfDisposer(peer));
    }

    static CSSRuleList create(long peer) {
        if (peer == 0L) return null;
        return new CSSRuleListImpl(peer);
    }

    private final long peer;

    long getPeer() {
        return peer;
    }

    @Override public boolean equals(Object that) {
        return (that instanceof CSSRuleListImpl) && (peer == ((CSSRuleListImpl)that).peer);
    }

    @Override public int hashCode() {
        long p = peer;
        return (int) (p ^ (p >> 17));
    }

    static long getPeer(CSSRuleList arg) {
        return (arg == null) ? 0L : ((CSSRuleListImpl)arg).getPeer();
    }

    native private static void dispose(long peer);

    static CSSRuleList getImpl(long peer) {
        return (CSSRuleList)create(peer);
    }


// Attributes
    @Override
    public int getLength() {
        return getLengthImpl(getPeer());
    }
    native static int getLengthImpl(long peer);


// Functions
    @Override
    public CSSRule item(int index)
    {
        return CSSRuleImpl.getImpl(itemImpl(getPeer()
            , index));
    }
    native static long itemImpl(long peer
        , int index);


}

