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
import org.w3c.dom.css.CSSMediaRule;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.stylesheets.MediaList;

public class CSSMediaRuleImpl extends CSSRuleImpl implements CSSMediaRule {
    CSSMediaRuleImpl(long peer) {
        super(peer);
    }

    static CSSMediaRule getImpl(long peer) {
        return (CSSMediaRule)create(peer);
    }


// Attributes
    @Override
    public MediaList getMedia() {
        return MediaListImpl.getImpl(getMediaImpl(getPeer()));
    }
    native static long getMediaImpl(long peer);

    @Override
    public CSSRuleList getCssRules() {
        return CSSRuleListImpl.getImpl(getCssRulesImpl(getPeer()));
    }
    native static long getCssRulesImpl(long peer);


// Functions
    @Override
    public int insertRule(String rule
        , int index) throws DOMException
    {
        return insertRuleImpl(getPeer()
            , rule
            , index);
    }
    native static int insertRuleImpl(long peer
        , String rule
        , int index);


    @Override
    public void deleteRule(int index) throws DOMException
    {
        deleteRuleImpl(getPeer()
            , index);
    }
    native static void deleteRuleImpl(long peer
        , int index);


}

