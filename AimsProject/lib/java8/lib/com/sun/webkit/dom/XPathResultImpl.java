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
import org.w3c.dom.Node;
import org.w3c.dom.xpath.XPathResult;

public class XPathResultImpl implements XPathResult {
    private static class SelfDisposer implements DisposerRecord {
        private final long peer;
        SelfDisposer(final long peer) {
            this.peer = peer;
        }

        @Override
        public void dispose() {
            XPathResultImpl.dispose(peer);
        }
    }

    XPathResultImpl(long peer) {
        this.peer = peer;
        Disposer.addRecord(this, new SelfDisposer(peer));
    }

    static XPathResult create(long peer) {
        if (peer == 0L) return null;
        return new XPathResultImpl(peer);
    }

    private final long peer;

    long getPeer() {
        return peer;
    }

    @Override public boolean equals(Object that) {
        return (that instanceof XPathResultImpl) && (peer == ((XPathResultImpl)that).peer);
    }

    @Override public int hashCode() {
        long p = peer;
        return (int) (p ^ (p >> 17));
    }

    static long getPeer(XPathResult arg) {
        return (arg == null) ? 0L : ((XPathResultImpl)arg).getPeer();
    }

    native private static void dispose(long peer);

    static XPathResult getImpl(long peer) {
        return (XPathResult)create(peer);
    }


// Constants
    public static final int ANY_TYPE = 0;
    public static final int NUMBER_TYPE = 1;
    public static final int STRING_TYPE = 2;
    public static final int BOOLEAN_TYPE = 3;
    public static final int UNORDERED_NODE_ITERATOR_TYPE = 4;
    public static final int ORDERED_NODE_ITERATOR_TYPE = 5;
    public static final int UNORDERED_NODE_SNAPSHOT_TYPE = 6;
    public static final int ORDERED_NODE_SNAPSHOT_TYPE = 7;
    public static final int ANY_UNORDERED_NODE_TYPE = 8;
    public static final int FIRST_ORDERED_NODE_TYPE = 9;

// Attributes
    @Override
    public short getResultType() {
        return getResultTypeImpl(getPeer());
    }
    native static short getResultTypeImpl(long peer);

    @Override
    public double getNumberValue() throws DOMException {
        return getNumberValueImpl(getPeer());
    }
    native static double getNumberValueImpl(long peer);

    @Override
    public String getStringValue() throws DOMException {
        return getStringValueImpl(getPeer());
    }
    native static String getStringValueImpl(long peer);

    @Override
    public boolean getBooleanValue() throws DOMException {
        return getBooleanValueImpl(getPeer());
    }
    native static boolean getBooleanValueImpl(long peer);

    @Override
    public Node getSingleNodeValue() throws DOMException {
        return NodeImpl.getImpl(getSingleNodeValueImpl(getPeer()));
    }
    native static long getSingleNodeValueImpl(long peer);

    @Override
    public boolean getInvalidIteratorState() {
        return getInvalidIteratorStateImpl(getPeer());
    }
    native static boolean getInvalidIteratorStateImpl(long peer);

    @Override
    public int getSnapshotLength() throws DOMException {
        return getSnapshotLengthImpl(getPeer());
    }
    native static int getSnapshotLengthImpl(long peer);


// Functions
    @Override
    public Node iterateNext() throws DOMException
    {
        return NodeImpl.getImpl(iterateNextImpl(getPeer()));
    }
    native static long iterateNextImpl(long peer);


    @Override
    public Node snapshotItem(int index) throws DOMException
    {
        return NodeImpl.getImpl(snapshotItemImpl(getPeer()
            , index));
    }
    native static long snapshotItemImpl(long peer
        , int index);


}

