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

import org.w3c.dom.css.CSSImportRule;
import org.w3c.dom.css.CSSStyleSheet;
import org.w3c.dom.stylesheets.MediaList;

public class CSSImportRuleImpl extends CSSRuleImpl implements CSSImportRule {
    CSSImportRuleImpl(long peer) {
        super(peer);
    }

    static CSSImportRule getImpl(long peer) {
        return (CSSImportRule)create(peer);
    }


// Attributes
    @Override
    public String getHref() {
        return getHrefImpl(getPeer());
    }
    native static String getHrefImpl(long peer);

    @Override
    public MediaList getMedia() {
        return MediaListImpl.getImpl(getMediaImpl(getPeer()));
    }
    native static long getMediaImpl(long peer);

    @Override
    public CSSStyleSheet getStyleSheet() {
        return CSSStyleSheetImpl.getImpl(getStyleSheetImpl(getPeer()));
    }
    native static long getStyleSheetImpl(long peer);

}

