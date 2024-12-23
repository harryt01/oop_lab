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
import org.w3c.dom.xpath.XPathNSResolver;

public class XPathNSResolverImpl implements XPathNSResolver {
    private static class SelfDisposer implements DisposerRecord {
        private final long peer;
        SelfDisposer(final long peer) {
            this.peer = peer;
        }

        @Override
        public void dispose() {
            XPathNSResolverImpl.dispose(peer);
        }
    }

    XPathNSResolverImpl(long peer) {
        this.peer = peer;
        Disposer.addRecord(this, new SelfDisposer(peer));
    }

    static XPathNSResolver create(long peer) {
        if (peer == 0L) return null;
        return new XPathNSResolverImpl(peer);
    }

    private final long peer;

    long getPeer() {
        return peer;
    }

    @Override public boolean equals(Object that) {
        return (that instanceof XPathNSResolverImpl) && (peer == ((XPathNSResolverImpl)that).peer);
    }

    @Override public int hashCode() {
        long p = peer;
        return (int) (p ^ (p >> 17));
    }

    static long getPeer(XPathNSResolver arg) {
        return (arg == null) ? 0L : ((XPathNSResolverImpl)arg).getPeer();
    }

    native private static void dispose(long peer);

    static XPathNSResolver getImpl(long peer) {
        return (XPathNSResolver)create(peer);
    }


// Functions
    @Override
    public String lookupNamespaceURI(String prefix)
    {
        return lookupNamespaceURIImpl(getPeer()
            , prefix);
    }
    native static String lookupNamespaceURIImpl(long peer
        , String prefix);


}

