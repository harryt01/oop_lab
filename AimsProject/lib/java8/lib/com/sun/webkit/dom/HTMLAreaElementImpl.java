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

import org.w3c.dom.html.HTMLAreaElement;

public class HTMLAreaElementImpl extends HTMLElementImpl implements HTMLAreaElement {
    HTMLAreaElementImpl(long peer) {
        super(peer);
    }

    static HTMLAreaElement getImpl(long peer) {
        return (HTMLAreaElement)create(peer);
    }


// Attributes
    @Override
    public String getAlt() {
        return getAltImpl(getPeer());
    }
    native static String getAltImpl(long peer);

    @Override
    public void setAlt(String value) {
        setAltImpl(getPeer(), value);
    }
    native static void setAltImpl(long peer, String value);

    @Override
    public String getCoords() {
        return getCoordsImpl(getPeer());
    }
    native static String getCoordsImpl(long peer);

    @Override
    public void setCoords(String value) {
        setCoordsImpl(getPeer(), value);
    }
    native static void setCoordsImpl(long peer, String value);

    @Override
    public boolean getNoHref() {
        return getNoHrefImpl(getPeer());
    }
    native static boolean getNoHrefImpl(long peer);

    @Override
    public void setNoHref(boolean value) {
        setNoHrefImpl(getPeer(), value);
    }
    native static void setNoHrefImpl(long peer, boolean value);

    public String getPing() {
        return getPingImpl(getPeer());
    }
    native static String getPingImpl(long peer);

    public void setPing(String value) {
        setPingImpl(getPeer(), value);
    }
    native static void setPingImpl(long peer, String value);

    public String getRel() {
        return getRelImpl(getPeer());
    }
    native static String getRelImpl(long peer);

    public void setRel(String value) {
        setRelImpl(getPeer(), value);
    }
    native static void setRelImpl(long peer, String value);

    @Override
    public String getShape() {
        return getShapeImpl(getPeer());
    }
    native static String getShapeImpl(long peer);

    @Override
    public void setShape(String value) {
        setShapeImpl(getPeer(), value);
    }
    native static void setShapeImpl(long peer, String value);

    @Override
    public String getTarget() {
        return getTargetImpl(getPeer());
    }
    native static String getTargetImpl(long peer);

    @Override
    public void setTarget(String value) {
        setTargetImpl(getPeer(), value);
    }
    native static void setTargetImpl(long peer, String value);

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

    @Override
    public String getHref() {
        return getHrefImpl(getPeer());
    }
    native static String getHrefImpl(long peer);

    @Override
    public void setHref(String value) {
        setHrefImpl(getPeer(), value);
    }
    native static void setHrefImpl(long peer, String value);

    public String getOrigin() {
        return getOriginImpl(getPeer());
    }
    native static String getOriginImpl(long peer);

    public String getProtocol() {
        return getProtocolImpl(getPeer());
    }
    native static String getProtocolImpl(long peer);

    public void setProtocol(String value) {
        setProtocolImpl(getPeer(), value);
    }
    native static void setProtocolImpl(long peer, String value);

    public String getUsername() {
        return getUsernameImpl(getPeer());
    }
    native static String getUsernameImpl(long peer);

    public void setUsername(String value) {
        setUsernameImpl(getPeer(), value);
    }
    native static void setUsernameImpl(long peer, String value);

    public String getPassword() {
        return getPasswordImpl(getPeer());
    }
    native static String getPasswordImpl(long peer);

    public void setPassword(String value) {
        setPasswordImpl(getPeer(), value);
    }
    native static void setPasswordImpl(long peer, String value);

    public String getHost() {
        return getHostImpl(getPeer());
    }
    native static String getHostImpl(long peer);

    public void setHost(String value) {
        setHostImpl(getPeer(), value);
    }
    native static void setHostImpl(long peer, String value);

    public String getHostname() {
        return getHostnameImpl(getPeer());
    }
    native static String getHostnameImpl(long peer);

    public void setHostname(String value) {
        setHostnameImpl(getPeer(), value);
    }
    native static void setHostnameImpl(long peer, String value);

    public String getPort() {
        return getPortImpl(getPeer());
    }
    native static String getPortImpl(long peer);

    public void setPort(String value) {
        setPortImpl(getPeer(), value);
    }
    native static void setPortImpl(long peer, String value);

    public String getPathname() {
        return getPathnameImpl(getPeer());
    }
    native static String getPathnameImpl(long peer);

    public void setPathname(String value) {
        setPathnameImpl(getPeer(), value);
    }
    native static void setPathnameImpl(long peer, String value);

    public String getSearch() {
        return getSearchImpl(getPeer());
    }
    native static String getSearchImpl(long peer);

    public void setSearch(String value) {
        setSearchImpl(getPeer(), value);
    }
    native static void setSearchImpl(long peer, String value);

    public String getHash() {
        return getHashImpl(getPeer());
    }
    native static String getHashImpl(long peer);

    public void setHash(String value) {
        setHashImpl(getPeer(), value);
    }
    native static void setHashImpl(long peer, String value);

}

