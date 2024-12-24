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
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSStyleSheet;

public class CSSStyleSheetImpl extends StyleSheetImpl implements CSSStyleSheet {
    CSSStyleSheetImpl(long peer) {
        super(peer);
    }

    static CSSStyleSheet getImpl(long peer) {
        return (CSSStyleSheet)create(peer);
    }


// Attributes
    @Override
    public CSSRule getOwnerRule() {
        return CSSRuleImpl.getImpl(getOwnerRuleImpl(getPeer()));
    }
    native static long getOwnerRuleImpl(long peer);

    @Override
    public CSSRuleList getCssRules() {
        return CSSRuleListImpl.getImpl(getCssRulesImpl(getPeer()));
    }
    native static long getCssRulesImpl(long peer);

    public CSSRuleList getRules() {
        return CSSRuleListImpl.getImpl(getRulesImpl(getPeer()));
    }
    native static long getRulesImpl(long peer);


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


    public int addRule(String selector
        , String style
        , int index) throws DOMException
    {
        return addRuleImpl(getPeer()
            , selector
            , style
            , index);
    }
    native static int addRuleImpl(long peer
        , String selector
        , String style
        , int index);


    public void removeRule(int index) throws DOMException
    {
        removeRuleImpl(getPeer()
            , index);
    }
    native static void removeRuleImpl(long peer
        , int index);


}

