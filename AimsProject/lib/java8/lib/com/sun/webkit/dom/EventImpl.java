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
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventTarget;

public class EventImpl implements Event {
    private static class SelfDisposer implements DisposerRecord {
        private final long peer;
        SelfDisposer(final long peer) {
            this.peer = peer;
        }

        @Override
        public void dispose() {
            EventImpl.dispose(peer);
        }
    }

    EventImpl(long peer) {
        this.peer = peer;
        Disposer.addRecord(this, new SelfDisposer(peer));
    }

    static Event create(long peer) {
        if (peer == 0L) return null;
        switch (EventImpl.getCPPTypeImpl(peer)) {
        case TYPE_MouseEvent: return new MouseEventImpl(peer);
        case TYPE_KeyboardEvent: return new KeyboardEventImpl(peer);
        case TYPE_WheelEvent: return new WheelEventImpl(peer);
        case TYPE_UIEvent: return new UIEventImpl(peer);
        case TYPE_MutationEvent: return new MutationEventImpl(peer);
        }
        return new EventImpl(peer);
    }

    private final long peer;

    long getPeer() {
        return peer;
    }

    @Override public boolean equals(Object that) {
        return (that instanceof EventImpl) && (peer == ((EventImpl)that).peer);
    }

    @Override public int hashCode() {
        long p = peer;
        return (int) (p ^ (p >> 17));
    }

    static long getPeer(Event arg) {
        return (arg == null) ? 0L : ((EventImpl)arg).getPeer();
    }

    native private static void dispose(long peer);

    private static final int TYPE_WheelEvent = 1;
    private static final int TYPE_MouseEvent = 2;
    private static final int TYPE_KeyboardEvent = 3;
    private static final int TYPE_UIEvent = 4;
    private static final int TYPE_MutationEvent = 5;
    native private static int getCPPTypeImpl(long peer);

    static Event getImpl(long peer) {
        return (Event)create(peer);
    }


// Constants
    public static final int NONE = 0;
    public static final int CAPTURING_PHASE = 1;
    public static final int AT_TARGET = 2;
    public static final int BUBBLING_PHASE = 3;
    public static final int MOUSEDOWN = 1;
    public static final int MOUSEUP = 2;
    public static final int MOUSEOVER = 4;
    public static final int MOUSEOUT = 8;
    public static final int MOUSEMOVE = 16;
    public static final int MOUSEDRAG = 32;
    public static final int CLICK = 64;
    public static final int DBLCLICK = 128;
    public static final int KEYDOWN = 256;
    public static final int KEYUP = 512;
    public static final int KEYPRESS = 1024;
    public static final int DRAGDROP = 2048;
    public static final int FOCUS = 4096;
    public static final int BLUR = 8192;
    public static final int SELECT = 16384;
    public static final int CHANGE = 32768;

// Attributes
    @Override
    public String getType() {
        return getTypeImpl(getPeer());
    }
    native static String getTypeImpl(long peer);

    @Override
    public EventTarget getTarget() {
        return (EventTarget)NodeImpl.getImpl(getTargetImpl(getPeer()));
    }
    native static long getTargetImpl(long peer);

    @Override
    public EventTarget getCurrentTarget() {
        return (EventTarget)NodeImpl.getImpl(getCurrentTargetImpl(getPeer()));
    }
    native static long getCurrentTargetImpl(long peer);

    @Override
    public short getEventPhase() {
        return getEventPhaseImpl(getPeer());
    }
    native static short getEventPhaseImpl(long peer);

    @Override
    public boolean getBubbles() {
        return getBubblesImpl(getPeer());
    }
    native static boolean getBubblesImpl(long peer);

    @Override
    public boolean getCancelable() {
        return getCancelableImpl(getPeer());
    }
    native static boolean getCancelableImpl(long peer);

    @Override
    public long getTimeStamp() {
        return getTimeStampImpl(getPeer());
    }
    native static long getTimeStampImpl(long peer);

    public boolean getDefaultPrevented() {
        return getDefaultPreventedImpl(getPeer());
    }
    native static boolean getDefaultPreventedImpl(long peer);

    public boolean getIsTrusted() {
        return getIsTrustedImpl(getPeer());
    }
    native static boolean getIsTrustedImpl(long peer);

    public EventTarget getSrcElement() {
        return (EventTarget)NodeImpl.getImpl(getSrcElementImpl(getPeer()));
    }
    native static long getSrcElementImpl(long peer);

    public boolean getReturnValue() {
        return getReturnValueImpl(getPeer());
    }
    native static boolean getReturnValueImpl(long peer);

    public void setReturnValue(boolean value) {
        setReturnValueImpl(getPeer(), value);
    }
    native static void setReturnValueImpl(long peer, boolean value);

    public boolean getCancelBubble() {
        return getCancelBubbleImpl(getPeer());
    }
    native static boolean getCancelBubbleImpl(long peer);

    public void setCancelBubble(boolean value) {
        setCancelBubbleImpl(getPeer(), value);
    }
    native static void setCancelBubbleImpl(long peer, boolean value);


// Functions
    @Override
    public void stopPropagation()
    {
        stopPropagationImpl(getPeer());
    }
    native static void stopPropagationImpl(long peer);


    @Override
    public void preventDefault()
    {
        preventDefaultImpl(getPeer());
    }
    native static void preventDefaultImpl(long peer);


    @Override
    public void initEvent(String eventTypeArg
        , boolean canBubbleArg
        , boolean cancelableArg)
    {
        initEventImpl(getPeer()
            , eventTypeArg
            , canBubbleArg
            , cancelableArg);
    }
    native static void initEventImpl(long peer
        , String eventTypeArg
        , boolean canBubbleArg
        , boolean cancelableArg);


    public void stopImmediatePropagation()
    {
        stopImmediatePropagationImpl(getPeer());
    }
    native static void stopImmediatePropagationImpl(long peer);


}

