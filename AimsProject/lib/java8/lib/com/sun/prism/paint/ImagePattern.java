/*
 * Copyright (c) 2009, 2020, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.prism.paint;

import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.prism.Image;

public final class ImagePattern extends Paint {

    private final Image image;
    private final float x;
    private final float y;
    private final float width;
    private final float height;
    private final BaseTransform patternTransform;

    public ImagePattern(Image image,
                        float x, float y, float width, float height,
                        BaseTransform patternTransform,
                        boolean proportional, boolean isMutable)
    {
        super(Type.IMAGE_PATTERN, proportional, isMutable);
        if (image == null) {
            throw new IllegalArgumentException("Image must be non-null");
        }
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        if (patternTransform != null) {
            this.patternTransform = patternTransform.copy();
        } else {
            this.patternTransform = BaseTransform.IDENTITY_TRANSFORM;
        }
    }

    public ImagePattern(Image image,
                        float x, float y, float width, float height,
                        boolean proportional, boolean isMutable)
    {
        this(image, x, y, width, height, null, proportional, isMutable);
    }

    public Image getImage() {
        return image;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public BaseTransform getPatternTransformNoClone() {
        return patternTransform;
    }

    public boolean isOpaque() {
        return image.isOpaque();
    }
}
