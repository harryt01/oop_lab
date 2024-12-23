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
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

public class TreeWalkerImpl implements TreeWalker {
    private static class SelfDisposer implements DisposerRecord {
        private final long peer;
        SelfDisposer(final long peer) {
            this.peer = peer;
        }

        @Override
        public void dispose() {
            TreeWalkerImpl.dispose(peer);
        }
    }

    TreeWalkerImpl(long peer) {
        this.peer = peer;
        Disposer.addRecord(this, new SelfDisposer(peer));
    }

    static TreeWalker create(long peer) {
        if (peer == 0L) return null;
        return new TreeWalkerImpl(peer);
    }

    private final long peer;

    long getPeer() {
        return peer;
    }

    @Override public boolean equals(Object that) {
        return (that instanceof TreeWalkerImpl) && (peer == ((TreeWalkerImpl)that).peer);
    }

    @Override public int hashCode() {
        long p = peer;
        return (int) (p ^ (p >> 17));
    }

    static long getPeer(TreeWalker arg) {
        return (arg == null) ? 0L : ((TreeWalkerImpl)arg).getPeer();
    }

    native private static void dispose(long peer);

    static TreeWalker getImpl(long peer) {
        return (TreeWalker)create(peer);
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

    @Override
    public Node getCurrentNode() {
        return NodeImpl.getImpl(getCurrentNodeImpl(getPeer()));
    }
    native static long getCurrentNodeImpl(long peer);

    @Override
    public void setCurrentNode(Node value) throws DOMException {
        setCurrentNodeImpl(getPeer(), NodeImpl.getPeer(value));
    }
    native static void setCurrentNodeImpl(long peer, long value);


// Functions
    @Override
    public Node parentNode()
    {
        return NodeImpl.getImpl(parentNodeImpl(getPeer()));
    }
    native static long parentNodeImpl(long peer);


    @Override
    public Node firstChild()
    {
        return NodeImpl.getImpl(firstChildImpl(getPeer()));
    }
    native static long firstChildImpl(long peer);


    @Override
    public Node lastChild()
    {
        return NodeImpl.getImpl(lastChildImpl(getPeer()));
    }
    native static long lastChildImpl(long peer);


    @Override
    public Node previousSibling()
    {
        return NodeImpl.getImpl(previousSiblingImpl(getPeer()));
    }
    native static long previousSiblingImpl(long peer);


    @Override
    public Node nextSibling()
    {
        return NodeImpl.getImpl(nextSiblingImpl(getPeer()));
    }
    native static long nextSiblingImpl(long peer);


    @Override
    public Node previousNode()
    {
        return NodeImpl.getImpl(previousNodeImpl(getPeer()));
    }
    native static long previousNodeImpl(long peer);


    @Override
    public Node nextNode()
    {
        return NodeImpl.getImpl(nextNodeImpl(getPeer()));
    }
    native static long nextNodeImpl(long peer);


}

