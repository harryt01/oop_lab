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
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

public class NodeIteratorImpl implements NodeIterator {
    private static class SelfDisposer implements DisposerRecord {
        private final long peer;
        SelfDisposer(final long peer) {
            this.peer = peer;
        }

        @Override
        public void dispose() {
            NodeIteratorImpl.dispose(peer);
        }
    }

    NodeIteratorImpl(long peer) {
        this.peer = peer;
        Disposer.addRecord(this, new SelfDisposer(peer));
    }

    static NodeIterator create(long peer) {
        if (peer == 0L) return null;
        return new NodeIteratorImpl(peer);
    }

    private final long peer;

    long getPeer() {
        return peer;
    }

    @Override public boolean equals(Object that) {
        return (that instanceof NodeIteratorImpl) && (peer == ((NodeIteratorImpl)that).peer);
    }

    @Override public int hashCode() {
        long p = peer;
        return (int) (p ^ (p >> 17));
    }

    static long getPeer(NodeIterator arg) {
        return (arg == null) ? 0L : ((NodeIteratorImpl)arg).getPeer();
    }

    native private static void dispose(long peer);

    static NodeIterator getImpl(long peer) {
        return (NodeIterator)create(peer);
    }


// Attributes
    @Override
    public Node getRoot() {
        return NodeImpl.getImpl(getRootImpl(getPeer()));
    }
    native static long getRootImpl(long peer);

    @Override
    public int getWhatToShow() {
        return getWhatToShowImpl(getPeer());
    }
    native static int getWhatToShowImpl(long peer);

    @Override
    public NodeFilter getFilter() {
        return NodeFilterImpl.getImpl(getFilterImpl(getPeer()));
    }
    native static long getFilterImpl(long peer);

    @Override
    public boolean getExpandEntityReferences() {
        return getExpandEntityReferencesImpl(getPeer());
    }
    native static boolean getExpandEntityReferencesImpl(long peer);

    public Node getReferenceNode() {
        return NodeImpl.getImpl(getReferenceNodeImpl(getPeer()));
    }
    native static long getReferenceNodeImpl(long peer);

    public boolean getPointerBeforeReferenceNode() {
        return getPointerBeforeReferenceNodeImpl(getPeer());
    }
    native static boolean getPointerBeforeReferenceNodeImpl(long peer);


// Functions
    @Override
    public Node nextNode()
    {
        return NodeImpl.getImpl(nextNodeImpl(getPeer()));
    }
    native static long nextNodeImpl(long peer);


    @Override
    public Node previousNode()
    {
        return NodeImpl.getImpl(previousNodeImpl(getPeer()));
    }
    native static long previousNodeImpl(long peer);


    @Override
    public void detach()
    {
        detachImpl(getPeer());
    }
    native static void detachImpl(long peer);


}

