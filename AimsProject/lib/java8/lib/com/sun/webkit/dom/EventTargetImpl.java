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
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

public class EventTargetImpl implements EventTarget {
    private static class SelfDisposer implements DisposerRecord {
        private final long peer;
        SelfDisposer(final long peer) {
            this.peer = peer;
        }

        @Override
        public void dispose() {
            EventTargetImpl.dispose(peer);
        }
    }

    EventTargetImpl(long peer) {
        this.peer = peer;
        Disposer.addRecord(this, new SelfDisposer(peer));
    }

    static EventTarget create(long peer) {
        if (peer == 0L) return null;
        return new EventTargetImpl(peer);
    }

    private final long peer;

    long getPeer() {
        return peer;
    }

    @Override public boolean equals(Object that) {
        return (that instanceof EventTargetImpl) && (peer == ((EventTargetImpl)that).peer);
    }

    @Override public int hashCode() {
        long p = peer;
        return (int) (p ^ (p >> 17));
    }

    static long getPeer(EventTarget arg) {
        return (arg == null) ? 0L : ((EventTargetImpl)arg).getPeer();
    }

    native private static void dispose(long peer);

    static EventTarget getImpl(long peer) {
        return (EventTarget)create(peer);
    }


// Functions
    @Override
    public void addEventListener(String type
        , EventListener listener
        , boolean useCapture)
    {
        addEventListenerImpl(getPeer()
            , type
            , EventListenerImpl.getPeer(listener)
            , useCapture);
    }
    native static void addEventListenerImpl(long peer
        , String type
        , long listener
        , boolean useCapture);


    @Override
    public void removeEventListener(String type
        , EventListener listener
        , boolean useCapture)
    {
        removeEventListenerImpl(getPeer()
            , type
            , EventListenerImpl.getPeer(listener)
            , useCapture);
    }
    native static void removeEventListenerImpl(long peer
        , String type
        , long listener
        , boolean useCapture);


    @Override
    public boolean dispatchEvent(Event event) throws DOMException
    {
        return dispatchEventImpl(getPeer()
            , EventImpl.getPeer(event));
    }
    native static boolean dispatchEventImpl(long peer
        , long event);


}

