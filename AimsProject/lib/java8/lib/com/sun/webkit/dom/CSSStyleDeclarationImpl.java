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
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.CSSValue;

public class CSSStyleDeclarationImpl implements CSSStyleDeclaration {
    private static class SelfDisposer implements DisposerRecord {
        private final long peer;
        SelfDisposer(final long peer) {
            this.peer = peer;
        }

        @Override
        public void dispose() {
            CSSStyleDeclarationImpl.dispose(peer);
        }
    }

    CSSStyleDeclarationImpl(long peer) {
        this.peer = peer;
        Disposer.addRecord(this, new SelfDisposer(peer));
    }

    static CSSStyleDeclaration create(long peer) {
        if (peer == 0L) return null;
        return new CSSStyleDeclarationImpl(peer);
    }

    private final long peer;

    long getPeer() {
        return peer;
    }

    @Override public boolean equals(Object that) {
        return (that instanceof CSSStyleDeclarationImpl) && (peer == ((CSSStyleDeclarationImpl)that).peer);
    }

    @Override public int hashCode() {
        long p = peer;
        return (int) (p ^ (p >> 17));
    }

    static long getPeer(CSSStyleDeclaration arg) {
        return (arg == null) ? 0L : ((CSSStyleDeclarationImpl)arg).getPeer();
    }

    native private static void dispose(long peer);

    static CSSStyleDeclaration getImpl(long peer) {
        return (CSSStyleDeclaration)create(peer);
    }


// Attributes
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
    public int getLength() {
        return getLengthImpl(getPeer());
    }
    native static int getLengthImpl(long peer);

    @Override
    public CSSRule getParentRule() {
        return CSSRuleImpl.getImpl(getParentRuleImpl(getPeer()));
    }
    native static long getParentRuleImpl(long peer);


// Functions
    @Override
    public String getPropertyValue(String propertyName)
    {
        return getPropertyValueImpl(getPeer()
            , propertyName);
    }
    native static String getPropertyValueImpl(long peer
        , String propertyName);


    @Override
    public CSSValue getPropertyCSSValue(String propertyName)
    {
        return CSSValueImpl.getImpl(getPropertyCSSValueImpl(getPeer()
            , propertyName));
    }
    native static long getPropertyCSSValueImpl(long peer
        , String propertyName);


    @Override
    public String removeProperty(String propertyName) throws DOMException
    {
        return removePropertyImpl(getPeer()
            , propertyName);
    }
    native static String removePropertyImpl(long peer
        , String propertyName);


    @Override
    public String getPropertyPriority(String propertyName)
    {
        return getPropertyPriorityImpl(getPeer()
            , propertyName);
    }
    native static String getPropertyPriorityImpl(long peer
        , String propertyName);


    @Override
    public void setProperty(String propertyName
        , String value
        , String priority) throws DOMException
    {
        setPropertyImpl(getPeer()
            , propertyName
            , value
            , priority);
    }
    native static void setPropertyImpl(long peer
        , String propertyName
        , String value
        , String priority);


    @Override
    public String item(int index)
    {
        return itemImpl(getPeer()
            , index);
    }
    native static String itemImpl(long peer
        , int index);


    public String getPropertyShorthand(String propertyName)
    {
        return getPropertyShorthandImpl(getPeer()
            , propertyName);
    }
    native static String getPropertyShorthandImpl(long peer
        , String propertyName);


    public boolean isPropertyImplicit(String propertyName)
    {
        return isPropertyImplicitImpl(getPeer()
            , propertyName);
    }
    native static boolean isPropertyImplicitImpl(long peer
        , String propertyName);


}

