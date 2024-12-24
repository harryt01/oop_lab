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
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLTableCaptionElement;
import org.w3c.dom.html.HTMLTableElement;
import org.w3c.dom.html.HTMLTableSectionElement;

public class HTMLTableElementImpl extends HTMLElementImpl implements HTMLTableElement {
    HTMLTableElementImpl(long peer) {
        super(peer);
    }

    static HTMLTableElement getImpl(long peer) {
        return (HTMLTableElement)create(peer);
    }


// Attributes
    @Override
    public HTMLTableCaptionElement getCaption() {
        return HTMLTableCaptionElementImpl.getImpl(getCaptionImpl(getPeer()));
    }
    native static long getCaptionImpl(long peer);

    @Override
    public void setCaption(HTMLTableCaptionElement value) throws DOMException {
        setCaptionImpl(getPeer(), HTMLTableCaptionElementImpl.getPeer(value));
    }
    native static void setCaptionImpl(long peer, long value);

    @Override
    public HTMLTableSectionElement getTHead() {
        return HTMLTableSectionElementImpl.getImpl(getTHeadImpl(getPeer()));
    }
    native static long getTHeadImpl(long peer);

    @Override
    public void setTHead(HTMLTableSectionElement value) throws DOMException {
        setTHeadImpl(getPeer(), HTMLTableSectionElementImpl.getPeer(value));
    }
    native static void setTHeadImpl(long peer, long value);

    @Override
    public HTMLTableSectionElement getTFoot() {
        return HTMLTableSectionElementImpl.getImpl(getTFootImpl(getPeer()));
    }
    native static long getTFootImpl(long peer);

    @Override
    public void setTFoot(HTMLTableSectionElement value) throws DOMException {
        setTFootImpl(getPeer(), HTMLTableSectionElementImpl.getPeer(value));
    }
    native static void setTFootImpl(long peer, long value);

    @Override
    public HTMLCollection getRows() {
        return HTMLCollectionImpl.getImpl(getRowsImpl(getPeer()));
    }
    native static long getRowsImpl(long peer);

    @Override
    public HTMLCollection getTBodies() {
        return HTMLCollectionImpl.getImpl(getTBodiesImpl(getPeer()));
    }
    native static long getTBodiesImpl(long peer);

    @Override
    public String getAlign() {
        return getAlignImpl(getPeer());
    }
    native static String getAlignImpl(long peer);

    @Override
    public void setAlign(String value) {
        setAlignImpl(getPeer(), value);
    }
    native static void setAlignImpl(long peer, String value);

    @Override
    public String getBgColor() {
        return getBgColorImpl(getPeer());
    }
    native static String getBgColorImpl(long peer);

    @Override
    public void setBgColor(String value) {
        setBgColorImpl(getPeer(), value);
    }
    native static void setBgColorImpl(long peer, String value);

    @Override
    public String getBorder() {
        return getBorderImpl(getPeer());
    }
    native static String getBorderImpl(long peer);

    @Override
    public void setBorder(String value) {
        setBorderImpl(getPeer(), value);
    }
    native static void setBorderImpl(long peer, String value);

    @Override
    public String getCellPadding() {
        return getCellPaddingImpl(getPeer());
    }
    native static String getCellPaddingImpl(long peer);

    @Override
    public void setCellPadding(String value) {
        setCellPaddingImpl(getPeer(), value);
    }
    native static void setCellPaddingImpl(long peer, String value);

    @Override
    public String getCellSpacing() {
        return getCellSpacingImpl(getPeer());
    }
    native static String getCellSpacingImpl(long peer);

    @Override
    public void setCellSpacing(String value) {
        setCellSpacingImpl(getPeer(), value);
    }
    native static void setCellSpacingImpl(long peer, String value);

    @Override
    public String getFrame() {
        return getFrameImpl(getPeer());
    }
    native static String getFrameImpl(long peer);

    @Override
    public void setFrame(String value) {
        setFrameImpl(getPeer(), value);
    }
    native static void setFrameImpl(long peer, String value);

    @Override
    public String getRules() {
        return getRulesImpl(getPeer());
    }
    native static String getRulesImpl(long peer);

    @Override
    public void setRules(String value) {
        setRulesImpl(getPeer(), value);
    }
    native static void setRulesImpl(long peer, String value);

    @Override
    public String getSummary() {
        return getSummaryImpl(getPeer());
    }
    native static String getSummaryImpl(long peer);

    @Override
    public void setSummary(String value) {
        setSummaryImpl(getPeer(), value);
    }
    native static void setSummaryImpl(long peer, String value);

    @Override
    public String getWidth() {
        return getWidthImpl(getPeer());
    }
    native static String getWidthImpl(long peer);

    @Override
    public void setWidth(String value) {
        setWidthImpl(getPeer(), value);
    }
    native static void setWidthImpl(long peer, String value);


// Functions
    @Override
    public HTMLElement createTHead()
    {
        return HTMLElementImpl.getImpl(createTHeadImpl(getPeer()));
    }
    native static long createTHeadImpl(long peer);


    @Override
    public void deleteTHead()
    {
        deleteTHeadImpl(getPeer());
    }
    native static void deleteTHeadImpl(long peer);


    @Override
    public HTMLElement createTFoot()
    {
        return HTMLElementImpl.getImpl(createTFootImpl(getPeer()));
    }
    native static long createTFootImpl(long peer);


    @Override
    public void deleteTFoot()
    {
        deleteTFootImpl(getPeer());
    }
    native static void deleteTFootImpl(long peer);


    public HTMLElement createTBody()
    {
        return HTMLElementImpl.getImpl(createTBodyImpl(getPeer()));
    }
    native static long createTBodyImpl(long peer);


    @Override
    public HTMLElement createCaption()
    {
        return HTMLElementImpl.getImpl(createCaptionImpl(getPeer()));
    }
    native static long createCaptionImpl(long peer);


    @Override
    public void deleteCaption()
    {
        deleteCaptionImpl(getPeer());
    }
    native static void deleteCaptionImpl(long peer);


    @Override
    public HTMLElement insertRow(int index) throws DOMException
    {
        return HTMLElementImpl.getImpl(insertRowImpl(getPeer()
            , index));
    }
    native static long insertRowImpl(long peer
        , int index);


    @Override
    public void deleteRow(int index) throws DOMException
    {
        deleteRowImpl(getPeer()
            , index);
    }
    native static void deleteRowImpl(long peer
        , int index);


}

