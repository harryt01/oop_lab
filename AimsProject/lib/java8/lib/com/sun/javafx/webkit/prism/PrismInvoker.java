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

package com.sun.javafx.webkit.prism;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;
import com.sun.javafx.application.PlatformImpl;
import com.sun.javafx.tk.RenderJob;
import com.sun.javafx.tk.Toolkit;
import com.sun.webkit.Invoker;

public final class PrismInvoker extends Invoker {

    private static final Logger log =
            Logger.getLogger(PrismInvoker.class.getName());

    public PrismInvoker() {
    }

    /*
     * No synchronization b/w Event (User) & Render threads is required
     * because FX synchronizes pulse and render operations itself.
     */
    @Override protected boolean lock(ReentrantLock lock) {
        return false;
    }

    @Override protected boolean unlock(ReentrantLock lock) {
        return false;
    }

    @Override protected boolean isEventThread() {
        return isEventThreadPrivate();
    }

    private static boolean isEventThreadPrivate() {
        return Toolkit.getToolkit().isFxUserThread();
    }

    @Override public void checkEventThread() {
        Toolkit.getToolkit().checkFxUserThread();
    }

    @Override public void invokeOnEventThread(final Runnable r) {
        if (isEventThread()) {
            r.run();
        } else {
            PlatformImpl.runLater(r);
        }
    }

    @Override public void postOnEventThread(final Runnable r) {
        PlatformImpl.runLater(r);
    }

    static void invokeOnRenderThread(final Runnable r) {
        Toolkit.getToolkit().addRenderJob(new RenderJob(r));
    }

    static void runOnRenderThread(final Runnable r) {
        if (Thread.currentThread().getName().startsWith("QuantumRenderer")) {
            r.run();
        } else {
            FutureTask<Void> f = new FutureTask<Void>(r, null);
            Toolkit.getToolkit().addRenderJob(new RenderJob(f));
            try {
                // block until job is complete
                f.get();
            } catch (ExecutionException | InterruptedException ex) {
                StringWriter logMessage = new StringWriter();
                PrintWriter writer = new PrintWriter(logMessage);
                ex.printStackTrace(writer);
                log.severe("RenderJob error" + String.format("%n") +
                        logMessage.toString());
            }
        }
    }
}
