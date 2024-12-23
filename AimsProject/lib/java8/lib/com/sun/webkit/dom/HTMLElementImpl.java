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
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLElement;

public class HTMLElementImpl extends ElementImpl implements HTMLElement {
    HTMLElementImpl(long peer) {
        super(peer);
    }

    static HTMLElement getImpl(long peer) {
        return (HTMLElement)create(peer);
    }


// Attributes
    @Override
    public String getId() {
        return getIdImpl(getPeer());
    }
    native static String getIdImpl(long peer);

    @Override
    public void setId(String value) {
        setIdImpl(getPeer(), value);
    }
    native static void setIdImpl(long peer, String value);

    @Override
    public String getTitle() {
        return getTitleImpl(getPeer());
    }
    native static String getTitleImpl(long peer);

    @Override
    public void setTitle(String value) {
        setTitleImpl(getPeer(), value);
    }
    native static void setTitleImpl(long peer, String value);

    @Override
    public String getLang() {
        return getLangImpl(getPeer());
    }
    native static String getLangImpl(long peer);

    @Override
    public void setLang(String value) {
        setLangImpl(getPeer(), value);
    }
    native static void setLangImpl(long peer, String value);

    public boolean getTranslate() {
        return getTranslateImpl(getPeer());
    }
    native static boolean getTranslateImpl(long peer);

    public void setTranslate(boolean value) {
        setTranslateImpl(getPeer(), value);
    }
    native static void setTranslateImpl(long peer, boolean value);

    @Override
    public String getDir() {
        return getDirImpl(getPeer());
    }
    native static String getDirImpl(long peer);

    @Override
    public void setDir(String value) {
        setDirImpl(getPeer(), value);
    }
    native static void setDirImpl(long peer, String value);

    public int getTabIndex() {
        return getTabIndexImpl(getPeer());
    }
    native static int getTabIndexImpl(long peer);

    public void setTabIndex(int value) {
        setTabIndexImpl(getPeer(), value);
    }
    native static void setTabIndexImpl(long peer, int value);

    public boolean getDraggable() {
        return getDraggableImpl(getPeer());
    }
    native static boolean getDraggableImpl(long peer);

    public void setDraggable(boolean value) {
        setDraggableImpl(getPeer(), value);
    }
    native static void setDraggableImpl(long peer, boolean value);

    public String getWebkitdropzone() {
        return getWebkitdropzoneImpl(getPeer());
    }
    native static String getWebkitdropzoneImpl(long peer);

    public void setWebkitdropzone(String value) {
        setWebkitdropzoneImpl(getPeer(), value);
    }
    native static void setWebkitdropzoneImpl(long peer, String value);

    public boolean getHidden() {
        return getHiddenImpl(getPeer());
    }
    native static boolean getHiddenImpl(long peer);

    public void setHidden(boolean value) {
        setHiddenImpl(getPeer(), value);
    }
    native static void setHiddenImpl(long peer, boolean value);

    public String getAccessKey() {
        return getAccessKeyImpl(getPeer());
    }
    native static String getAccessKeyImpl(long peer);

    public void setAccessKey(String value) {
        setAccessKeyImpl(getPeer(), value);
    }
    native static void setAccessKeyImpl(long peer, String value);

    public String getInnerText() {
        return getInnerTextImpl(getPeer());
    }
    native static String getInnerTextImpl(long peer);

    public void setInnerText(String value) throws DOMException {
        setInnerTextImpl(getPeer(), value);
    }
    native static void setInnerTextImpl(long peer, String value);

    public String getOuterText() {
        return getOuterTextImpl(getPeer());
    }
    native static String getOuterTextImpl(long peer);

    public void setOuterText(String value) throws DOMException {
        setOuterTextImpl(getPeer(), value);
    }
    native static void setOuterTextImpl(long peer, String value);

    @Override
    public HTMLCollection getChildren() {
        return HTMLCollectionImpl.getImpl(getChildrenImpl(getPeer()));
    }
    native static long getChildrenImpl(long peer);

    public String getContentEditable() {
        return getContentEditableImpl(getPeer());
    }
    native static String getContentEditableImpl(long peer);

    public void setContentEditable(String value) throws DOMException {
        setContentEditableImpl(getPeer(), value);
    }
    native static void setContentEditableImpl(long peer, String value);

    public boolean getIsContentEditable() {
        return getIsContentEditableImpl(getPeer());
    }
    native static boolean getIsContentEditableImpl(long peer);

    public boolean getSpellcheck() {
        return getSpellcheckImpl(getPeer());
    }
    native static boolean getSpellcheckImpl(long peer);

    public void setSpellcheck(boolean value) {
        setSpellcheckImpl(getPeer(), value);
    }
    native static void setSpellcheckImpl(long peer, boolean value);

    public String getTitleDisplayString() {
        return getTitleDisplayStringImpl(getPeer());
    }
    native static String getTitleDisplayStringImpl(long peer);


// Functions
    public Element insertAdjacentElement(String where
        , Element element) throws DOMException
    {
        return ElementImpl.getImpl(insertAdjacentElementImpl(getPeer()
            , where
            , ElementImpl.getPeer(element)));
    }
    native static long insertAdjacentElementImpl(long peer
        , String where
        , long element);


    public void insertAdjacentHTML(String where
        , String html) throws DOMException
    {
        insertAdjacentHTMLImpl(getPeer()
            , where
            , html);
    }
    native static void insertAdjacentHTMLImpl(long peer
        , String where
        , String html);


    public void insertAdjacentText(String where
        , String text) throws DOMException
    {
        insertAdjacentTextImpl(getPeer()
            , where
            , text);
    }
    native static void insertAdjacentTextImpl(long peer
        , String where
        , String text);


    public void click()
    {
        clickImpl(getPeer());
    }
    native static void clickImpl(long peer);


}

