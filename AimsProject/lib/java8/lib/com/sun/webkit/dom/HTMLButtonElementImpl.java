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

import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLButtonElement;
import org.w3c.dom.html.HTMLFormElement;

public class HTMLButtonElementImpl extends HTMLElementImpl implements HTMLButtonElement {
    HTMLButtonElementImpl(long peer) {
        super(peer);
    }

    static HTMLButtonElement getImpl(long peer) {
        return (HTMLButtonElement)create(peer);
    }


// Attributes
    public boolean getAutofocus() {
        return getAutofocusImpl(getPeer());
    }
    native static boolean getAutofocusImpl(long peer);

    public void setAutofocus(boolean value) {
        setAutofocusImpl(getPeer(), value);
    }
    native static void setAutofocusImpl(long peer, boolean value);

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

    public String getFormAction() {
        return getFormActionImpl(getPeer());
    }
    native static String getFormActionImpl(long peer);

    public void setFormAction(String value) {
        setFormActionImpl(getPeer(), value);
    }
    native static void setFormActionImpl(long peer, String value);

    public String getFormEnctype() {
        return getFormEnctypeImpl(getPeer());
    }
    native static String getFormEnctypeImpl(long peer);

    public void setFormEnctype(String value) {
        setFormEnctypeImpl(getPeer(), value);
    }
    native static void setFormEnctypeImpl(long peer, String value);

    public String getFormMethod() {
        return getFormMethodImpl(getPeer());
    }
    native static String getFormMethodImpl(long peer);

    public void setFormMethod(String value) {
        setFormMethodImpl(getPeer(), value);
    }
    native static void setFormMethodImpl(long peer, String value);

    @Override
    public String getType() {
        return getTypeImpl(getPeer());
    }
    native static String getTypeImpl(long peer);

    public void setType(String value) {
        setTypeImpl(getPeer(), value);
    }
    native static void setTypeImpl(long peer, String value);

    public boolean getFormNoValidate() {
        return getFormNoValidateImpl(getPeer());
    }
    native static boolean getFormNoValidateImpl(long peer);

    public void setFormNoValidate(boolean value) {
        setFormNoValidateImpl(getPeer(), value);
    }
    native static void setFormNoValidateImpl(long peer, boolean value);

    public String getFormTarget() {
        return getFormTargetImpl(getPeer());
    }
    native static String getFormTargetImpl(long peer);

    public void setFormTarget(String value) {
        setFormTargetImpl(getPeer(), value);
    }
    native static void setFormTargetImpl(long peer, String value);

    @Override
    public String getName() {
        return getNameImpl(getPeer());
    }
    native static String getNameImpl(long peer);

    @Override
    public void setName(String value) {
        setNameImpl(getPeer(), value);
    }
    native static void setNameImpl(long peer, String value);

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

    public boolean getWillValidate() {
        return getWillValidateImpl(getPeer());
    }
    native static boolean getWillValidateImpl(long peer);

    public String getValidationMessage() {
        return getValidationMessageImpl(getPeer());
    }
    native static String getValidationMessageImpl(long peer);

    public NodeList getLabels() {
        return NodeListImpl.getImpl(getLabelsImpl(getPeer()));
    }
    native static long getLabelsImpl(long peer);

    @Override
    public String getAccessKey() {
        return getAccessKeyImpl(getPeer());
    }
    native static String getAccessKeyImpl(long peer);

    @Override
    public void setAccessKey(String value) {
        setAccessKeyImpl(getPeer(), value);
    }
    native static void setAccessKeyImpl(long peer, String value);


// Functions
    public boolean checkValidity()
    {
        return checkValidityImpl(getPeer());
    }
    native static boolean checkValidityImpl(long peer);


    public void setCustomValidity(String error)
    {
        setCustomValidityImpl(getPeer()
            , error);
    }
    native static void setCustomValidityImpl(long peer
        , String error);


    @Override
    public void click()
    {
        clickImpl(getPeer());
    }
    native static void clickImpl(long peer);


}

