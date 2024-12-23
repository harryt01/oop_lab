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
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSStyleSheet;

public class CSSRuleImpl implements CSSRule {
    private static class SelfDisposer implements DisposerRecord {
        private final long peer;
        SelfDisposer(final long peer) {
            this.peer = peer;
        }

        @Override
        public void dispose() {
            CSSRuleImpl.dispose(peer);
        }
    }

    CSSRuleImpl(long peer) {
        this.peer = peer;
        Disposer.addRecord(this, new SelfDisposer(peer));
    }

    static CSSRule create(long peer) {
        if (peer == 0L) return null;
        switch (CSSRuleImpl.getTypeImpl(peer)) {
        case STYLE_RULE: return new CSSStyleRuleImpl(peer);
        case CHARSET_RULE: return new CSSCharsetRuleImpl(peer);
        case IMPORT_RULE: return new CSSImportRuleImpl(peer);
        case MEDIA_RULE: return new CSSMediaRuleImpl(peer);
        case FONT_FACE_RULE: return new CSSFontFaceRuleImpl(peer);
        case PAGE_RULE: return new CSSPageRuleImpl(peer);
        }
        return new CSSRuleImpl(peer);
    }

    private final long peer;

    long getPeer() {
        return peer;
    }

    @Override public boolean equals(Object that) {
        return (that instanceof CSSRuleImpl) && (peer == ((CSSRuleImpl)that).peer);
    }

    @Override public int hashCode() {
        long p = peer;
        return (int) (p ^ (p >> 17));
    }

    static long getPeer(CSSRule arg) {
        return (arg == null) ? 0L : ((CSSRuleImpl)arg).getPeer();
    }

    native private static void dispose(long peer);

    static CSSRule getImpl(long peer) {
        return (CSSRule)create(peer);
    }


// Constants
    public static final int UNKNOWN_RULE = 0;
    public static final int STYLE_RULE = 1;
    public static final int CHARSET_RULE = 2;
    public static final int IMPORT_RULE = 3;
    public static final int MEDIA_RULE = 4;
    public static final int FONT_FACE_RULE = 5;
    public static final int PAGE_RULE = 6;
    public static final int KEYFRAMES_RULE = 7;
    public static final int KEYFRAME_RULE = 8;
    public static final int SUPPORTS_RULE = 12;
    public static final int WEBKIT_REGION_RULE = 16;
    public static final int WEBKIT_KEYFRAMES_RULE = 7;
    public static final int WEBKIT_KEYFRAME_RULE = 8;

// Attributes
    @Override
    public short getType() {
        return getTypeImpl(getPeer());
    }
    native static short getTypeImpl(long peer);

    @Override
    public String getCssText() {
        return getCssTextImpl(getPeer());
    }
    native static String getCssTextImpl(long peer);

    @Override
    public void setCssText(String value) throws DOMException {
        setCssTextImpl(getPeer(), value);
    }
    native static void setCssTextImpl(long peer, String value);

    @Override
    public CSSStyleSheet getParentStyleSheet() {
        return CSSStyleSheetImpl.getImpl(getParentStyleSheetImpl(getPeer()));
    }
    native static long getParentStyleSheetImpl(long peer);

    @Override
    public CSSRule getParentRule() {
        return CSSRuleImpl.getImpl(getParentRuleImpl(getPeer()));
    }
    native static long getParentRuleImpl(long peer);

}

