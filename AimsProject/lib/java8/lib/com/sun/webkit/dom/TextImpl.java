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

import org.w3c.dom.DOMException;
import org.w3c.dom.Text;

public class TextImpl extends CharacterDataImpl implements Text {
    TextImpl(long peer) {
        super(peer);
    }

    static Text getImpl(long peer) {
        return (Text)create(peer);
    }


// Attributes
    @Override
    public String getWholeText() {
        return getWholeTextImpl(getPeer());
    }
    native static String getWholeTextImpl(long peer);


// Functions
    @Override
    public Text splitText(int offset) throws DOMException
    {
        return TextImpl.getImpl(splitTextImpl(getPeer()
            , offset));
    }
    native static long splitTextImpl(long peer
        , int offset);


    @Override
    public Text replaceWholeText(String content) throws DOMException
    {
        return TextImpl.getImpl(replaceWholeTextImpl(getPeer()
            , content));
    }
    native static long replaceWholeTextImpl(long peer
        , String content);



//stubs
    @Override
    public boolean isElementContentWhitespace() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

