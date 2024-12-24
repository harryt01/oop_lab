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

import org.w3c.dom.html.HTMLFormElement;
import org.w3c.dom.html.HTMLOptionElement;

public class HTMLOptionElementImpl extends HTMLElementImpl implements HTMLOptionElement {
    HTMLOptionElementImpl(long peer) {
        super(peer);
    }

    static HTMLOptionElement getImpl(long peer) {
        return (HTMLOptionElement)create(peer);
    }


// Attributes
    @Override
    public boolean getDisabled() {
        return getDisabledImpl(getPeer());
    }
    native static boolean getDisabledImpl(long peer);

    @Override
    public void setDisabled(boolean value) {
        setDisabledImpl(getPeer(), value);
    }
    native static void setDisabledImpl(long peer, boolean value);

    @Override
    public HTMLFormElement getForm() {
        return HTMLFormElementImpl.getImpl(getFormImpl(getPeer()));
    }
    native static long getFormImpl(long peer);

    @Override
    public String getLabel() {
        return getLabelImpl(getPeer());
    }
    native static String getLabelImpl(long peer);

    @Override
    public void setLabel(String value) {
        setLabelImpl(getPeer(), value);
    }
    native static void setLabelImpl(long peer, String value);

    @Override
    public boolean getDefaultSelected() {
        return getDefaultSelectedImpl(getPeer());
    }
    native static boolean getDefaultSelectedImpl(long peer);

    @Override
    public void setDefaultSelected(boolean value) {
        setDefaultSelectedImpl(getPeer(), value);
    }
    native static void setDefaultSelectedImpl(long peer, boolean value);

    @Override
    public boolean getSelected() {
        return getSelectedImpl(getPeer());
    }
    native static boolean getSelectedImpl(long peer);

    @Override
    public void setSelected(boolean value) {
        setSelectedImpl(getPeer(), value);
    }
    native static void setSelectedImpl(long peer, boolean value);

    @Override
    public String getValue() {
        return getValueImpl(getPeer());
    }
    native static String getValueImpl(long peer);

    @Override
    public void setValue(String value) {
        setValueImpl(getPeer(), value);
    }
    native static void setValueImpl(long peer, String value);

    @Override
    public String getText() {
        return getTextImpl(getPeer());
    }
    native static String getTextImpl(long peer);

    @Override
    public int getIndex() {
        return getIndexImpl(getPeer());
    }
    native static int getIndexImpl(long peer);

}

