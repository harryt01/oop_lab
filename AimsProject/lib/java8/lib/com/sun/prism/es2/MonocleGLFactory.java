/*
 * Copyright (c) 2014, 2021, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.prism.es2;

import com.sun.glass.ui.monocle.GLException;
import com.sun.glass.ui.monocle.NativePlatformFactory;
import com.sun.prism.es2.GLPixelFormat.Attributes;
import java.util.HashMap;
import com.sun.glass.ui.monocle.AcceleratedScreen;
import com.sun.prism.impl.PrismSettings;

class MonocleGLFactory extends GLFactory {

    private static native long nInitialize(int[] attrArr);
    private static native long nPopulateNativeCtxInfo(long libraryHandle);
    private static native int nGetAdapterOrdinal(long nativeScreen);
    private static native int nGetAdapterCount();
    private static native int nGetDefaultScreen(long nativeCtxInfo);
    private static native long nGetDisplay(long nativeCtxInfo);
    private static native long nGetVisualID(long nativeCtxInfo);
    private static native boolean nGetIsGL2(long nativeCtxInfo);

    // Entries must be in lowercase and null string is a wild card
    // For Linux Beta release we will limit es2 pipe qualification check to NVidia GPUs only
    private GLGPUInfo preQualificationFilter[] = null;
    private GLGPUInfo rejectList[] = null;

    private AcceleratedScreen accScreen = null;

    @Override
    GLGPUInfo[] getPreQualificationFilter() {
        return preQualificationFilter;
    }

    @Override
    GLGPUInfo[] getRejectList() {
        return rejectList;
    }

    @Override
    GLContext createGLContext(long nativeCtxInfo) {
        return new MonocleGLContext(nativeCtxInfo);
    }

    @Override
    GLContext createGLContext(GLDrawable drawable, GLPixelFormat pixelFormat,
                                     GLContext shareCtx, boolean vSyncRequest) {

        // No need to pass down shareCtx as we don't use shared ctx on Monocle
        return new MonocleGLContext(drawable, pixelFormat, vSyncRequest,
                                    accScreen, nativeCtxInfo);
    }

    @Override
    GLDrawable createDummyGLDrawable(GLPixelFormat pixelFormat) {
        return new MonocleGLDrawable(pixelFormat, accScreen);
    }

    @Override
    GLDrawable createGLDrawable(long nativeWindow, GLPixelFormat pixelFormat) {
        return new MonocleGLDrawable(nativeWindow, pixelFormat, accScreen);
    }

    @Override
    GLPixelFormat createGLPixelFormat(long nativeScreen, Attributes attributes) {
        return new MonocleGLPixelFormat(nativeScreen, attributes);
    }

    @Override
    boolean initialize(Class psClass, Attributes attrs) {

        // holds the list of attributes to be translated for native call
        int attrArr[] = new int[GLPixelFormat.Attributes.NUM_ITEMS];

        attrArr[GLPixelFormat.Attributes.RED_SIZE] = attrs.getRedSize();
        attrArr[GLPixelFormat.Attributes.GREEN_SIZE] = attrs.getGreenSize();
        attrArr[GLPixelFormat.Attributes.BLUE_SIZE] = attrs.getBlueSize();
        attrArr[GLPixelFormat.Attributes.ALPHA_SIZE] = attrs.getAlphaSize();
        attrArr[GLPixelFormat.Attributes.DEPTH_SIZE] = attrs.getDepthSize();
        attrArr[GLPixelFormat.Attributes.DOUBLEBUFFER] = attrs.isDoubleBuffer() ? 1 : 0;
        attrArr[GLPixelFormat.Attributes.ONSCREEN] = attrs.isOnScreen() ? 1 : 0;

        try {
            accScreen = NativePlatformFactory.getNativePlatform().getAcceleratedScreen(

                    attrArr);

            // If the native platform can't provide hardware accelerated rendering,
            // accScreen can be null
            if (accScreen == null) {
                return false;
            }

            accScreen.enableRendering(true);

            nativeCtxInfo = nPopulateNativeCtxInfo(accScreen.getGLHandle());

            accScreen.enableRendering(false);

            if (nativeCtxInfo == 0) {
                // current pipe doesn't support this pixelFormat request
                return false;
            } else {
                gl2 = nGetIsGL2(nativeCtxInfo);
                return true;
            }
        } catch (GLException e) {
            if (PrismSettings.verbose) {
                e.printStackTrace();
            }
            return false;
        } catch (UnsatisfiedLinkError e) {
            if (PrismSettings.verbose) {
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    int getAdapterCount() {
        return nGetAdapterCount();
    }

    @Override
    int getAdapterOrdinal(long nativeScreen) {
        return nGetAdapterOrdinal(nativeScreen);
    }

    @Override
    void updateDeviceDetails(HashMap deviceDetails) {
    }
}
