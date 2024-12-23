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

import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.CSSStyleRule;

public class CSSStyleRuleImpl extends CSSRuleImpl implements CSSStyleRule {
    CSSStyleRuleImpl(long peer) {
        super(peer);
    }

    static CSSStyleRule getImpl(long peer) {
        return (CSSStyleRule)create(peer);
    }


// Attributes
    @Override
    public String getSelectorText() {
        return getSelectorTextImpl(getPeer());
    }
    native static String getSelectorTextImpl(long peer);

    @Override
    public void setSelectorText(String value) {
        setSelectorTextImpl(getPeer(), value);
    }
    native static void setSelectorTextImpl(long peer, String value);

    @Override
    public CSSStyleDeclaration getStyle() {
        return CSSStyleDeclarationImpl.getImpl(getStyleImpl(getPeer()));
    }
    native static long getStyleImpl(long peer);

}

