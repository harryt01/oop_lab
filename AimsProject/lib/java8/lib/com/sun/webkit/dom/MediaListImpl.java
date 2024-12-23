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
import org.w3c.dom.stylesheets.MediaList;

public class MediaListImpl implements MediaList {
    private static class SelfDisposer implements DisposerRecord {
        private final long peer;
        SelfDisposer(final long peer) {
            this.peer = peer;
        }

        @Override
        public void dispose() {
            MediaListImpl.dispose(peer);
        }
    }

    MediaListImpl(long peer) {
        this.peer = peer;
        Disposer.addRecord(this, new SelfDisposer(peer));
    }

    static MediaList create(long peer) {
        if (peer == 0L) return null;
        return new MediaListImpl(peer);
    }

    private final long peer;

    long getPeer() {
        return peer;
    }

    @Override public boolean equals(Object that) {
        return (that instanceof MediaListImpl) && (peer == ((MediaListImpl)that).peer);
    }

    @Override public int hashCode() {
        long p = peer;
        return (int) (p ^ (p >> 17));
    }

    static long getPeer(MediaList arg) {
        return (arg == null) ? 0L : ((MediaListImpl)arg).getPeer();
    }

    native private static void dispose(long peer);

    static MediaList getImpl(long peer) {
        return (MediaList)create(peer);
    }


// Attributes
    @Override
    public String getMediaText() {
        return getMediaTextImpl(getPeer());
    }
    native static String getMediaTextImpl(long peer);

    @Override
    public void setMediaText(String value) throws DOMException {
        setMediaTextImpl(getPeer(), value);
    }
    native static void setMediaTextImpl(long peer, String value);

    @Override
    public int getLength() {
        return getLengthImpl(getPeer());
    }
    native static int getLengthImpl(long peer);


// Functions
    @Override
    public String item(int index)
    {
        return itemImpl(getPeer()
            , index);
    }
    native static String itemImpl(long peer
        , int index);


    @Override
    public void deleteMedium(String oldMedium) throws DOMException
    {
        deleteMediumImpl(getPeer()
            , oldMedium);
    }
    native static void deleteMediumImpl(long peer
        , String oldMedium);


    @Override
    public void appendMedium(String newMedium) throws DOMException
    {
        appendMediumImpl(getPeer()
            , newMedium);
    }
    native static void appendMediumImpl(long peer
        , String newMedium);


}

