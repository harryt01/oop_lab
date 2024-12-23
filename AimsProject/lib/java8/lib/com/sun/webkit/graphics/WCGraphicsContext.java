/*
 * Copyright (c) 2011, 2021, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.webkit.graphics;

import java.nio.ByteBuffer;

import com.sun.prism.paint.Color;

public abstract class WCGraphicsContext {
    // The constants are taken from WebCore/platform/graphics/GraphicsTypes.h
    public static final int COMPOSITE_CLEAR               = 0;
    public static final int COMPOSITE_COPY                = 1;
    public static final int COMPOSITE_SOURCE_OVER         = 2;
    public static final int COMPOSITE_SOURCE_IN           = 3;
    public static final int COMPOSITE_SOURCE_OUT          = 4;
    public static final int COMPOSITE_SOURCE_ATOP         = 5;
    public static final int COMPOSITE_DESTINATION_OVER    = 6;
    public static final int COMPOSITE_DESTINATION_IN      = 7;
    public static final int COMPOSITE_DESTINATION_OUT     = 8;
    public static final int COMPOSITE_DESTINATION_ATOP    = 9;
    public static final int COMPOSITE_XOR                 = 10;
    public static final int COMPOSITE_PLUS_DARKER         = 11;
    public static final int COMPOSITE_HIGHLIGHT           = 12;
    public static final int COMPOSITE_PLUS_LIGHTER        = 13;

    public abstract void fillRect(float x, float y, float w, float h, Color color);
    public abstract void clearRect(float x, float y, float w, float h);
    public abstract void setFillColor(Color color);
    public abstract void setFillGradient(WCGradient gradient);

    public abstract void fillRoundedRect(float x, float y, float w, float h,
            float topLeftW, float topLeftH, float topRightW, float topRightH,
            float bottomLeftW, float bottomLeftH, float bottomRightW, float bottomRightH,
            Color color);

    public abstract void setTextMode(boolean fill, boolean stroke, boolean clip);
    public abstract void setFontSmoothingType(int fontSmoothingType);
    public abstract int getFontSmoothingType();

    public abstract void setStrokeStyle(int style);
    public abstract void setStrokeColor(Color color);
    public abstract void setStrokeWidth(float width);
    public abstract void setStrokeGradient(WCGradient gradient);

    public abstract void setLineDash(float offset, float... sizes);
    public abstract void setLineCap(int lineCap);
    public abstract void setLineJoin(int lineJoin);
    public abstract void setMiterLimit(float miterLimit);

    public abstract void drawPolygon(WCPath path, boolean shouldAntialias);
    public abstract void drawLine(int x0, int y0, int x1, int y1);

    public abstract void drawImage(WCImage img,
                          float dstx, float dsty, float dstw, float dsth,
                          float srcx, float srcy, float srcw, float srch);

    public abstract void drawIcon(WCIcon icon, int x, int y);

    public abstract void drawPattern(WCImage texture, WCRectangle srcRect,
                            WCTransform patternTransform, WCPoint phase,
                            WCRectangle destRect);

    public abstract void drawBitmapImage(ByteBuffer image, int x, int y, int w, int h);

    public abstract void translate(float x, float y);
    public abstract void scale(float sx, float sy);
    public abstract void rotate(float radians);

    public abstract void setPerspectiveTransform(WCTransform t);
    public abstract void setTransform(WCTransform t);
    public abstract WCTransform getTransform();
    public abstract void concatTransform(WCTransform t);

    public abstract void saveState();
    public abstract void restoreState();

    public abstract void setClip(WCPath path, boolean isOut);
    public abstract void setClip(int cx, int cy, int cw, int ch);
    public abstract void setClip(WCRectangle clip);
    public abstract WCRectangle getClip();

    public abstract void drawRect(int x, int y, int w, int h);
    public abstract void setComposite(int composite);
    public abstract void strokeArc(int x, int y, int w, int h, int startAngle,
                          int angleSpan);
    public abstract void drawEllipse(int x, int y, int w, int h);
    public abstract void drawFocusRing(int x, int y, int w, int h, Color color);
    public abstract void setAlpha(float alpha);
    public abstract float getAlpha();
    public abstract void beginTransparencyLayer(float opacity);
    public abstract void endTransparencyLayer();
    public abstract void strokePath(WCPath path);
    public abstract void strokeRect(float x, float y, float w, float h,
                                    float lineWidth);
    public abstract void fillPath(WCPath path);

    public abstract void setShadow(float dx, float dy, float blur, Color color);

    public abstract void drawString(WCFont f, String str,
                                    boolean rtl,
                                    int from, int to,
                                    float x, float y);
    public abstract void drawString(WCFont f, int[] glyphs,
                                    float[] advances,
                                    float x, float y);
    public abstract void drawWidget(RenderTheme theme, Ref widget, int x, int y);
    public abstract void drawScrollbar(ScrollBarTheme theme, Ref widget,
                                       int x, int y, int pressedPart, int hoveredPart);
    public abstract WCImage getImage();

    public abstract Object getPlatformGraphics();

    public abstract WCGradient createLinearGradient(WCPoint p1, WCPoint p2);
    public abstract WCGradient createRadialGradient(WCPoint p1, float r1, WCPoint p2, float r2);

    public abstract void flush();

    public abstract boolean isValid();

    public abstract void dispose();
}
