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
import org.w3c.dom.css.CSSCharsetRule;

public class CSSCharsetRuleImpl extends CSSRuleImpl implements CSSCharsetRule {
    CSSCharsetRuleImpl(long peer) {
        super(peer);
    }

    static CSSCharsetRule getImpl(long peer) {
        return (CSSCharsetRule)create(peer);
    }


// Attributes
    @Override
    public String getEncoding() {
        return getEncodingImpl(getPeer());
    }
    native static String getEncodingImpl(long peer);

    @Override
    public void setEncoding(String value) throws DOMException {
        setEncodingImpl(getPeer(), value);
    }
    native static void setEncodingImpl(long peer, String value);

}

