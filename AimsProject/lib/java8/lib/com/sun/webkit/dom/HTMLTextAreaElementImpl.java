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
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLFormElement;
import org.w3c.dom.html.HTMLTextAreaElement;

public class HTMLTextAreaElementImpl extends HTMLElementImpl implements HTMLTextAreaElement {
    HTMLTextAreaElementImpl(long peer) {
        super(peer);
    }

    static HTMLTextAreaElement getImpl(long peer) {
        return (HTMLTextAreaElement)create(peer);
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

    public String getDirName() {
        return getDirNameImpl(getPeer());
    }
    native static String getDirNameImpl(long peer);

    public void setDirName(String value) {
        setDirNameImpl(getPeer(), value);
    }
    native static void setDirNameImpl(long peer, String value);

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

    public int getMaxLength() {
        return getMaxLengthImpl(getPeer());
    }
    native static int getMaxLengthImpl(long peer);

    public void setMaxLength(int value) throws DOMException {
        setMaxLengthImpl(getPeer(), value);
    }
    native static void setMaxLengthImpl(long peer, int value);

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

    public String getPlaceholder() {
        return getPlaceholderImpl(getPeer());
    }
    native static String getPlaceholderImpl(long peer);

    public void setPlaceholder(String value) {
        setPlaceholderImpl(getPeer(), value);
    }
    native static void setPlaceholderImpl(long peer, String value);

    @Override
    public boolean getReadOnly() {
        return getReadOnlyImpl(getPeer());
    }
    native static boolean getReadOnlyImpl(long peer);

    @Override
    public void setReadOnly(boolean value) {
        setReadOnlyImpl(getPeer(), value);
    }
    native static void setReadOnlyImpl(long peer, boolean value);

    public boolean getRequired() {
        return getRequiredImpl(getPeer());
    }
    native static boolean getRequiredImpl(long peer);

    public void setRequired(boolean value) {
        setRequiredImpl(getPeer(), value);
    }
    native static void setRequiredImpl(long peer, boolean value);

    @Override
    public int getRows() {
        return getRowsImpl(getPeer());
    }
    native static int getRowsImpl(long peer);

    @Override
    public void setRows(int value) {
        setRowsImpl(getPeer(), value);
    }
    native static void setRowsImpl(long peer, int value);

    @Override
    public int getCols() {
        return getColsImpl(getPeer());
    }
    native static int getColsImpl(long peer);

    @Override
    public void setCols(int value) {
        setColsImpl(getPeer(), value);
    }
    native static void setColsImpl(long peer, int value);

    public String getWrap() {
        return getWrapImpl(getPeer());
    }
    native static String getWrapImpl(long peer);

    public void setWrap(String value) {
        setWrapImpl(getPeer(), value);
    }
    native static void setWrapImpl(long peer, String value);

    @Override
    public String getType() {
        return getTypeImpl(getPeer());
    }
    native static String getTypeImpl(long peer);

    @Override
    public String getDefaultValue() {
        return getDefaultValueImpl(getPeer());
    }
    native static String getDefaultValueImpl(long peer);

    @Override
    public void setDefaultValue(String value) {
        setDefaultValueImpl(getPeer(), value);
    }
    native static void setDefaultValueImpl(long peer, String value);

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

    public int getTextLength() {
        return getTextLengthImpl(getPeer());
    }
    native static int getTextLengthImpl(long peer);

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

    public int getSelectionStart() {
        return getSelectionStartImpl(getPeer());
    }
    native static int getSelectionStartImpl(long peer);

    public void setSelectionStart(int value) {
        setSelectionStartImpl(getPeer(), value);
    }
    native static void setSelectionStartImpl(long peer, int value);

    public int getSelectionEnd() {
        return getSelectionEndImpl(getPeer());
    }
    native static int getSelectionEndImpl(long peer);

    public void setSelectionEnd(int value) {
        setSelectionEndImpl(getPeer(), value);
    }
    native static void setSelectionEndImpl(long peer, int value);

    public String getSelectionDirection() {
        return getSelectionDirectionImpl(getPeer());
    }
    native static String getSelectionDirectionImpl(long peer);

    public void setSelectionDirection(String value) {
        setSelectionDirectionImpl(getPeer(), value);
    }
    native static void setSelectionDirectionImpl(long peer, String value);

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

    public String getAutocomplete() {
        return getAutocompleteImpl(getPeer());
    }
    native static String getAutocompleteImpl(long peer);

    public void setAutocomplete(String value) {
        setAutocompleteImpl(getPeer(), value);
    }
    native static void setAutocompleteImpl(long peer, String value);


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
    public void select()
    {
        selectImpl(getPeer());
    }
    native static void selectImpl(long peer);


    public void setRangeText(String replacement) throws DOMException
    {
        setRangeTextImpl(getPeer()
            , replacement);
    }
    native static void setRangeTextImpl(long peer
        , String replacement);


    public void setRangeTextEx(String replacement
        , int start
        , int end
        , String selectionMode) throws DOMException
    {
        setRangeTextExImpl(getPeer()
            , replacement
            , start
            , end
            , selectionMode);
    }
    native static void setRangeTextExImpl(long peer
        , String replacement
        , int start
        , int end
        , String selectionMode);


    public void setSelectionRange(int start
        , int end
        , String direction)
    {
        setSelectionRangeImpl(getPeer()
            , start
            , end
            , direction);
    }
    native static void setSelectionRangeImpl(long peer
        , int start
        , int end
        , String direction);


}

