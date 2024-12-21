/*
 * Copyright (c) 2010, 2023, Oracle and/or its affiliates. All rights reserved.
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
package com.sun.glass.ui.gtk;

import com.sun.glass.ui.Pixels;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

final class GtkPixels extends Pixels {

    public GtkPixels(int width, int height, ByteBuffer data) {
        super(width, height, data);
    }

    public GtkPixels(int width, int height, IntBuffer data) {
        super(width, height, data);
    }

    public GtkPixels(int width, int height, IntBuffer data, float scalex, float scaley) {
        super(width, height, data, scalex, scaley);
    }

    @Override
    protected void _fillDirectByteBuffer(ByteBuffer bb) {
        // Taken from MacPixels
        if (this.bytes != null) {
            this.bytes.rewind();
            bb.put(this.bytes);
            this.bytes.rewind();
        } else {
            this.ints.rewind();
            for (int i=0; i<this.ints.capacity(); i++) {
                int data = this.ints.get();
                bb.put((byte)((data)&0xff));
                bb.put((byte)((data>>8)&0xff));
                bb.put((byte)((data>>16)&0xff));
                bb.put((byte)((data>>24)&0xff));
            }
            this.ints.rewind();
        }
    }

    @Override
    protected native void _attachInt(long ptr, int w, int h, IntBuffer ints, int[] array, int offset);

    @Override
    protected native void _attachByte(long ptr, int w, int h, ByteBuffer bytes, byte[] array, int offset);

}
