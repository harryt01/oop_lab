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

import org.w3c.dom.Node;
import org.w3c.dom.events.MutationEvent;

public class MutationEventImpl extends EventImpl implements MutationEvent {
    MutationEventImpl(long peer) {
        super(peer);
    }

    static MutationEvent getImpl(long peer) {
        return (MutationEvent)create(peer);
    }


// Constants
    public static final int MODIFICATION = 1;
    public static final int ADDITION = 2;
    public static final int REMOVAL = 3;

// Attributes
    @Override
    public Node getRelatedNode() {
        return NodeImpl.getImpl(getRelatedNodeImpl(getPeer()));
    }
    native static long getRelatedNodeImpl(long peer);

    @Override
    public String getPrevValue() {
        return getPrevValueImpl(getPeer());
    }
    native static String getPrevValueImpl(long peer);

    @Override
    public String getNewValue() {
        return getNewValueImpl(getPeer());
    }
    native static String getNewValueImpl(long peer);

    @Override
    public String getAttrName() {
        return getAttrNameImpl(getPeer());
    }
    native static String getAttrNameImpl(long peer);

    @Override
    public short getAttrChange() {
        return getAttrChangeImpl(getPeer());
    }
    native static short getAttrChangeImpl(long peer);


// Functions
    @Override
    public void initMutationEvent(String type
        , boolean canBubble
        , boolean cancelable
        , Node relatedNode
        , String prevValue
        , String newValue
        , String attrName
        , short attrChange)
    {
        initMutationEventImpl(getPeer()
            , type
            , canBubble
            , cancelable
            , NodeImpl.getPeer(relatedNode)
            , prevValue
            , newValue
            , attrName
            , attrChange);
    }
    native static void initMutationEventImpl(long peer
        , String type
        , boolean canBubble
        , boolean cancelable
        , long relatedNode
        , String prevValue
        , String newValue
        , String attrName
        , short attrChange);


}

