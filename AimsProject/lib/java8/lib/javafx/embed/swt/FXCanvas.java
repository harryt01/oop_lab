/*
 * Copyright (c) 2012, 2020, Oracle and/or its affiliates. All rights reserved.
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

package javafx.embed.swt;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sun.glass.ui.Application;
import com.sun.glass.ui.Pixels;
import com.sun.javafx.cursor.CursorFrame;
import com.sun.javafx.cursor.CursorType;

import com.sun.javafx.embed.EmbeddedSceneDSInterface;
import com.sun.javafx.embed.HostDragStartListener;
import javafx.application.Platform;
import javafx.beans.NamedArg;
import javafx.scene.Scene;
import javafx.scene.input.TransferMode;

import com.sun.javafx.application.PlatformImpl;
import com.sun.javafx.embed.AbstractEvents;
import com.sun.javafx.embed.EmbeddedSceneDTInterface;
import com.sun.javafx.embed.EmbeddedSceneInterface;
import com.sun.javafx.embed.EmbeddedStageInterface;
import com.sun.javafx.embed.HostInterface;
import com.sun.javafx.stage.EmbeddedWindow;
import java.lang.reflect.Method;

import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.HTMLTransfer;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.dnd.URLTransfer;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.MouseWheelListener;

/**
 * {@code FXCanvas} is a component to embed JavaFX content into
 * SWT applications. The content to be displayed is specified
 * with the {@link #setScene} method that accepts an instance of
 * JavaFX {@code Scene}. After the scene is assigned, it gets
 * repainted automatically. All the input and focus events are
 * forwarded to the scene transparently to the developer.
 * <p>
 * Here is a typical pattern how {@code FXCanvas} can used:
 * <pre>
 *    public class Test {
 *        private static Scene createScene() {
 *            Group group = new Group();
 *            Scene scene = new Scene(group);
 *            Button button = new Button("JFX Button");
 *            group.getChildren().add(button);
 *            return scene;
 *        }
 *
 *        public static void main(String[] args) {
 *            Display display = new Display();
 *            Shell shell = new Shell(display);
 *            shell.setLayout(new FillLayout());
 *            FXCanvas canvas = new FXCanvas(shell, SWT.NONE);
 *            Scene scene = createScene();
 *            canvas.setScene(scene);
 *            shell.open();
 *            while (!shell.isDisposed()) {
 *                if (!display.readAndDispatch()) display.sleep();
 *            }
 *            display.dispose();
 *        }
 *    }
 * </pre>
 *
 *
 * @since JavaFX 2.0
 */
public class FXCanvas extends Canvas {

    private HostContainer hostContainer;
    private volatile EmbeddedWindow stage;
    private volatile Scene scene;
    private EmbeddedStageInterface stagePeer;
    private EmbeddedSceneInterface scenePeer;

    private int pWidth = 0;
    private int pHeight = 0;

    private volatile int pPreferredWidth = -1;
    private volatile int pPreferredHeight = -1;

    private IntBuffer pixelsBuf = null;

    // This filter runs when any widget is moved
    Listener moveFilter = event -> {
        // If a parent has moved, send a move event to FX
        Control control = FXCanvas.this;
        while (control != null) {
            if (control == event.widget) {
                sendMoveEventToFX();
                break;
            }
            control = control.getParent();
        };
    };

    private double getScaleFactor() {
        if (SWT.getPlatform().equals("cocoa")) {
            if (windowField == null || screenMethod == null || backingScaleFactorMethod == null) {
                return 1.0;
            }
            try {
                Object nsWindow = windowField.get(this.getShell());
                Object nsScreen = screenMethod.invoke(nsWindow);
                Object bsFactor = backingScaleFactorMethod.invoke(nsScreen);
                return ((Double) bsFactor).doubleValue();
            } catch (Exception e) {
                // FAIL silently should the reflection fail
            }
        }
        return 1.0;
    }

    private DropTarget dropTarget;

    static Transfer [] StandardTransfers = new Transfer [] {
        TextTransfer.getInstance(),
        RTFTransfer.getInstance(),
        HTMLTransfer.getInstance(),
        URLTransfer.getInstance(),
        ImageTransfer.getInstance(),
        FileTransfer.getInstance(),
    };
    static Transfer [] CustomTransfers = new Transfer [0];

    static Transfer [] getAllTransfers () {
        Transfer [] transfers = new Transfer[StandardTransfers.length + CustomTransfers.length];
        System.arraycopy(StandardTransfers, 0, transfers, 0, StandardTransfers.length);
        System.arraycopy(CustomTransfers, 0, transfers, StandardTransfers.length, CustomTransfers.length);
        return transfers;
    }

    static Transfer getCustomTransfer(String mime) {
        for (int i=0; i<CustomTransfers.length; i++) {
            if (((CustomTransfer)CustomTransfers[i]).getMime().equals(mime)) {
                return CustomTransfers[i];
            }
        }
        Transfer transfer = new CustomTransfer (mime, mime);
        Transfer [] newCustom = new Transfer [CustomTransfers.length + 1];
        System.arraycopy(CustomTransfers, 0, newCustom, 0, CustomTransfers.length);
        newCustom[CustomTransfers.length] = transfer;
        CustomTransfers = newCustom;
        return transfer;
    }

    private static Field windowField;
    private static Method windowMethod;
    private static Method screenMethod;
    private static Method backingScaleFactorMethod;

    static {
        if (SWT.getPlatform().equals("cocoa")) {
            try {
                windowField = Shell.class.getDeclaredField("window");
                windowField.setAccessible(true);

                Class nsViewClass = Class.forName("org.eclipse.swt.internal.cocoa.NSView");
                windowMethod = nsViewClass.getDeclaredMethod("window");
                windowMethod.setAccessible(true);

                Class nsWindowClass = Class.forName("org.eclipse.swt.internal.cocoa.NSWindow");
                screenMethod = nsWindowClass.getDeclaredMethod("screen");
                screenMethod.setAccessible(true);

                Class nsScreenClass = Class.forName("org.eclipse.swt.internal.cocoa.NSScreen");
                backingScaleFactorMethod = nsScreenClass.getDeclaredMethod("backingScaleFactor");
                backingScaleFactorMethod.setAccessible(true);
            } catch (Exception e) {
                //Fail silently.  If we can't get the methods, then the current version of SWT has no retina support
            }
        }
    }

    /**
     * @inheritDoc
     */
    public FXCanvas(@NamedArg("parent") Composite parent, @NamedArg("style") int style) {
        super(parent, style | SWT.NO_BACKGROUND);
        initFx();
        hostContainer = new HostContainer();
        registerEventListeners();
        Display display = parent.getDisplay();
        display.addFilter(SWT.Move, moveFilter);
    }

    private static void initFx() {
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            System.setProperty("javafx.embed.isEventThread", "true");
            System.setProperty("glass.win.uiScale", "100%");
            System.setProperty("glass.win.minHiDPI", "9999");
            //System.setProperty("glass.win.renderScale", "100%");
            return null;
        });
        Map map = Application.getDeviceDetails();
        if (map == null) {
            Application.setDeviceDetails(map = new HashMap());
        }
        if (map.get("javafx.embed.eventProc") == null) {
            long eventProc = 0;
            try {
                Field field = Display.class.getDeclaredField("eventProc");
                field.setAccessible(true);
                if (field.getType() == int.class) {
                    eventProc = field.getLong(Display.getDefault());
                } else {
                    if (field.getType() == long.class) {
                        eventProc = field.getLong(Display.getDefault());
                    }
                }
            } catch (Throwable th) {
                //Fail silently
            }
            map.put("javafx.embed.eventProc", eventProc);
        }
        // Note that calling PlatformImpl.startup more than once is OK
        PlatformImpl.startup(() -> {
            Application.GetApplication().setName(Display.getAppName());
        });
    }

    static ArrayList<DropTarget> targets = new ArrayList<>();

    DropTarget getDropTarget() {
        return dropTarget;
    }

    void setDropTarget(DropTarget newTarget) {
        if (dropTarget != null) {
            targets.remove(dropTarget);
            dropTarget.dispose();
        }
        dropTarget = newTarget;
        if (dropTarget != null) {
            targets.add(dropTarget);
        }
    }

    static void updateDropTarget() {
        // Update all drop targets rather than just this target
        //
        // In order for a drop target to recognise a custom format,
        // the format must be registered and the transfer type added
        // to the list of transfers that the target accepts.  This
        // must happen before the drag and drop operations starts
        // or the drop target will not accept the format.  Therefore,
        // set all transfers for all targets before any drag and drop
        // operation starts
        //
        for (DropTarget target : targets) {
            target.setTransfer(getAllTransfers());
        }
    }

    /**
     * {@inheritDoc}
     */
    public Point computeSize (int wHint, int hHint, boolean changed) {
        checkWidget();
        if (wHint == -1 && hHint == -1) {
            if (pPreferredWidth != -1 && pPreferredHeight != -1) {
                return new Point (pPreferredWidth, pPreferredHeight);
            }
        }
        return super.computeSize(wHint, hHint, changed);
    }

    /**
     * Returns the JavaFX scene attached to this {@code FXCanvas}.
     *
     * @return the {@code Scene} attached to this {@code FXCanvas}
     */
    public Scene getScene() {
        checkWidget();
        return scene;
    }

    /**
     * Attaches a {@code Scene} object to display in this {@code
     * FXCanvas}. This method must called either on the JavaFX
     * JavaFX application thread (which is the same as the SWT
     * event dispatch thread).
     *
     * @param newScene a scene to display in this {@code FXCanvas}
     *
     * @see javafx.application.Platform#isFxApplicationThread()
     */
    public void setScene(final Scene newScene) {
        checkWidget();

        if ((stage == null) && (newScene != null)) {
            stage = new EmbeddedWindow(hostContainer);
            stage.show();
        }
        scene = newScene;
        if (stage != null) {
            stage.setScene(newScene);
        }
        if ((stage != null) && (newScene == null)) {
            stage.hide();
            stage = null;
        }
    }

    // Note that removing the listeners is unnecessary
    private void registerEventListeners() {
        addDisposeListener(new DisposeListener() {
            @Override
            public void widgetDisposed(DisposeEvent de) {
                Display display = getDisplay();
                display.removeFilter(SWT.Move, moveFilter);
                FXCanvas.this.widgetDisposed(de);
            }
        });

        addPaintListener(pe -> {
            FXCanvas.this.paintControl(pe);
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseDoubleClick(MouseEvent me) {
                // Clicks and double-clicks are handled in FX
            }
            @Override
            public void mouseDown(MouseEvent me) {
                // FX only supports 3 buttons so don't send the event for other buttons
                if (me.button > 3) return;
                FXCanvas.this.sendMouseEventToFX(me, AbstractEvents.MOUSEEVENT_PRESSED);
            }
            @Override
            public void mouseUp(MouseEvent me) {
                // FX only supports 3 buttons so don't send the event for other buttons
                if (me.button > 3) return;
                FXCanvas.this.sendMouseEventToFX(me, AbstractEvents.MOUSEEVENT_RELEASED);
            }
        });

        addMouseMoveListener(me -> {
            if ((me.stateMask & SWT.BUTTON_MASK) != 0) {
                // FX only supports 3 buttons so don't send the event for other buttons
                if ((me.stateMask & (SWT.BUTTON1 | SWT.BUTTON2 | SWT.BUTTON3)) != 0) {
                    FXCanvas.this.sendMouseEventToFX(me, AbstractEvents.MOUSEEVENT_DRAGGED);
                } else {
                    FXCanvas.this.sendMouseEventToFX(me, AbstractEvents.MOUSEEVENT_MOVED);
                }
            } else {
                FXCanvas.this.sendMouseEventToFX(me, AbstractEvents.MOUSEEVENT_MOVED);
            }
        });

        addMouseWheelListener(me -> {
            FXCanvas.this.sendMouseEventToFX(me, AbstractEvents.MOUSEEVENT_WHEEL);
        });

        addMouseTrackListener(new MouseTrackListener() {
            @Override
            public void mouseEnter(MouseEvent me) {
                FXCanvas.this.sendMouseEventToFX(me, AbstractEvents.MOUSEEVENT_ENTERED);
            }
            @Override
            public void mouseExit(MouseEvent me) {
                FXCanvas.this.sendMouseEventToFX(me, AbstractEvents.MOUSEEVENT_EXITED);
            }
            @Override
            public void mouseHover(MouseEvent me) {
                // No mouse hovering in FX
            }
        });

        addControlListener(new ControlListener() {
            @Override
            public void controlMoved(ControlEvent ce) {
                FXCanvas.this.sendMoveEventToFX();
            }
            @Override
            public void controlResized(ControlEvent ce) {
                FXCanvas.this.sendResizeEventToFX();
            }
        });

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                FXCanvas.this.sendFocusEventToFX(fe, true);
            }
            @Override
            public void focusLost(FocusEvent fe) {
                FXCanvas.this.sendFocusEventToFX(fe, false);
            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                FXCanvas.this.sendKeyEventToFX(e, SWT.KeyDown);

            }
            @Override
            public void keyReleased(KeyEvent e) {
                FXCanvas.this.sendKeyEventToFX(e, SWT.KeyUp);
            }
        });

        addMenuDetectListener(e -> {
            Runnable r = () -> {
                if (isDisposed()) return;
                FXCanvas.this.sendMenuEventToFX(e);
            };
            // In SWT, MenuDetect comes before the equivalent mouse event
            // On Mac, the order is MenuDetect, MouseDown, MouseUp.  FX
            // does not expect this order and when it gets the MouseDown,
            // it closes the menu.  The fix is to defer the MenuDetect
            // notification until after the MouseDown is sent to FX.
            if ("cocoa".equals(SWT.getPlatform())) {
                getDisplay().asyncExec(r);
            } else {
                r.run();
            }
        });
    }

    private void widgetDisposed(DisposeEvent de) {
        setDropTarget(null);
        if (stage != null) {
            stage.hide();
        }
    }

    double lastScaleFactor = 1.0;
    int lastWidth, lastHeight;
    IntBuffer lastPixelsBuf =  null;
    private void paintControl(PaintEvent pe) {
        if ((scenePeer == null) || (pixelsBuf == null)) {
            return;
        }

        double scaleFactor = getScaleFactor();
        if (lastScaleFactor != scaleFactor) {
            resizePixelBuffer(scaleFactor);
            lastScaleFactor = scaleFactor;
            scenePeer.setPixelScaleFactors((float)scaleFactor, (float)scaleFactor);
        }

        // if we can't get the pixels, draw the bits that were there before
        IntBuffer buffer = pixelsBuf;
        int width = pWidth, height = pHeight;
        if (scenePeer.getPixels(pixelsBuf, pWidth, pHeight)) {
            width = lastWidth = pWidth;
            height = lastHeight = pHeight;
            buffer = lastPixelsBuf = pixelsBuf;
        } else {
            if (lastPixelsBuf == null) return;
            width = lastWidth;
            height = lastHeight;
            buffer = lastPixelsBuf;
        }
        width = (int)Math.round(width * scaleFactor);
        height = (int)Math.round(height * scaleFactor);

        // Consider optimizing this
        ImageData imageData = null;
        if ("win32".equals(SWT.getPlatform())) {
            PaletteData palette = new PaletteData(0xFF00, 0xFF0000, 0xFF000000);
            int scanline = width * 4;
            byte[] dstData = new byte[scanline * height];
            int[] srcData = buffer.array();
            int dp = 0, sp = 0;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int p = srcData[sp++];
                    dstData[dp++] = (byte) (p & 0xFF); //dst:blue
                    dstData[dp++] = (byte)((p >> 8) & 0xFF); //dst:green
                    dstData[dp++] = (byte)((p >> 16) & 0xFF); //dst:green
                    dstData[dp++] = (byte)0x00; //alpha
                }
            }
            /*ImageData*/ imageData = new ImageData(width, height, 32, palette, 4, dstData);
        } else {
            if (width * height > buffer.array().length) {
                // We shouldn't be here...
                System.err.println("FXCanvas.paintControl: scale mismatch!");
                return;
            }
            PaletteData palette = new PaletteData(0x00ff0000, 0x0000ff00, 0x000000ff);
            /*ImageData*/  imageData = new ImageData(width, height, 32, palette);
            imageData.setPixels(0, 0,width * height, buffer.array(), 0);
        }

        Image image = new Image(Display.getDefault(), imageData);
        pe.gc.drawImage(image, 0, 0, width, height, 0, 0, pWidth, pHeight);
        image.dispose();
    }

    private void sendMoveEventToFX() {
        if ((stagePeer == null) /*|| !isShowing()*/) {
            return;
        }
        Rectangle rect = getClientArea();
        Point los = toDisplay(rect.x, rect.y);
        stagePeer.setLocation(los.x, los.y);
    }

    private void sendMouseEventToFX(MouseEvent me, int embedMouseType) {
        if (scenePeer == null) {
            return;
        }

        Point los = toDisplay(me.x, me.y);
        boolean primaryBtnDown = (me.stateMask & SWT.BUTTON1) != 0;
        boolean middleBtnDown = (me.stateMask & SWT.BUTTON2) != 0;
        boolean secondaryBtnDown = (me.stateMask & SWT.BUTTON3) != 0;
        boolean shift = (me.stateMask & SWT.SHIFT) != 0;
        boolean control = (me.stateMask & SWT.CONTROL) != 0;
        boolean alt = (me.stateMask & SWT.ALT) != 0;
        boolean meta = (me.stateMask & SWT.COMMAND) != 0;
        int button = me.button;
        switch (embedMouseType) {
            case AbstractEvents.MOUSEEVENT_PRESSED:
                primaryBtnDown |= me.button == 1;
                middleBtnDown |= me.button == 2;
                secondaryBtnDown |= me.button == 3;
                break;
            case AbstractEvents.MOUSEEVENT_RELEASED:
                primaryBtnDown &= me.button != 1;
                middleBtnDown &= me.button != 2;
                secondaryBtnDown &= me.button != 3;
                break;
            case AbstractEvents.MOUSEEVENT_CLICKED:
                // Don't send click events to FX, as they are generated in Scene
                return;

            case AbstractEvents.MOUSEEVENT_MOVED:
            case AbstractEvents.MOUSEEVENT_DRAGGED:
            case AbstractEvents.MOUSEEVENT_ENTERED:
            case AbstractEvents.MOUSEEVENT_EXITED:
                // If this event was the result of mouse movement and has no
                // button associated with it, then we look at the state to
                // determine which button to report
                if (button == 0) {
                    if ((me.stateMask & SWT.BUTTON1) != 0) {
                        button = 1;
                    } else if ((me.stateMask & SWT.BUTTON2) != 0) {
                        button = 2;
                    } else if ((me.stateMask & SWT.BUTTON3) != 0) {
                        button = 3;
                    }
                }
                break;

            default:
                break;
        }

        scenePeer.mouseEvent(
                embedMouseType,
                SWTEvents.mouseButtonToEmbedMouseButton(button, me.stateMask),
                primaryBtnDown, middleBtnDown, secondaryBtnDown,
                me.x, me.y,
                los.x, los.y,
                shift, control, alt, meta,
                SWTEvents.getWheelRotation(me, embedMouseType),
                false);  // RT-32990: popup trigger not implemented
    }

    private void sendKeyEventToFX(final KeyEvent e, int type) {
        if (scenePeer == null /*|| !isFxEnabled()*/) {
            return;
        }
        int stateMask = e.stateMask;
        if (type == SWT.KeyDown) {
            if (e.keyCode == SWT.SHIFT) stateMask |= SWT.SHIFT;
            if (e.keyCode == SWT.CONTROL) stateMask |= SWT.CONTROL;
            if (e.keyCode == SWT.ALT) stateMask |= SWT.ALT;
            if (e.keyCode == SWT.COMMAND) stateMask |= SWT.COMMAND;
        } else {
            if (e.keyCode == SWT.SHIFT) stateMask &= ~SWT.SHIFT;
            if (e.keyCode == SWT.CONTROL) stateMask &= ~SWT.CONTROL;
            if (e.keyCode == SWT.ALT) stateMask &= ~SWT.ALT;
            if (e.keyCode == SWT.COMMAND) stateMask &= ~SWT.COMMAND;
        }
        int keyCode = SWTEvents.keyCodeToEmbedKeyCode(e.keyCode);
        scenePeer.keyEvent(
                SWTEvents.keyIDToEmbedKeyType(type),
                keyCode, new char[0],
                SWTEvents.keyModifiersToEmbedKeyModifiers(stateMask));
        if (e.character != '\0' && type == SWT.KeyDown) {
            char[] chars = new char[] { e.character };
            scenePeer.keyEvent(
                    AbstractEvents.KEYEVENT_TYPED,
                    e.keyCode, chars,
                    SWTEvents.keyModifiersToEmbedKeyModifiers(stateMask));
        }
    }

    private void sendMenuEventToFX(MenuDetectEvent me) {
        if (scenePeer == null /*|| !isFxEnabled()*/) {
            return;
        }
        Point pt = toControl(me.x, me.y);
        scenePeer.menuEvent(pt.x, pt.y, me.x, me.y, false);
    }

    private void sendResizeEventToFX() {

        // force the panel to draw right away (avoid black rectangle)
        redraw();
        update();

        pWidth = getClientArea().width;
        pHeight = getClientArea().height;

        resizePixelBuffer(lastScaleFactor);

        if (scenePeer == null) {
            return;
        }

        stagePeer.setSize(pWidth, pHeight);
        scenePeer.setSize(pWidth, pHeight);
    }

    private void resizePixelBuffer(double newScaleFactor) {
        lastPixelsBuf = null;
        if ((pWidth <= 0) || (pHeight <= 0)) {
            pixelsBuf = null;
        } else {
            pixelsBuf = IntBuffer.allocate((int)Math.round(pWidth * newScaleFactor) *
                                           (int)Math.round(pHeight * newScaleFactor));
            // The bg color may show through on resize. See RT-34380.
            RGB rgb = getBackground().getRGB();
            Arrays.fill(pixelsBuf.array(), rgb.red << 16 | rgb.green << 8 | rgb.blue);
        }
    }

    private void sendFocusEventToFX(FocusEvent fe, boolean focused) {
        if ((stage == null) || (stagePeer == null)) {
            return;
        }
        int focusCause = (focused ?
                          AbstractEvents.FOCUSEVENT_ACTIVATED :
                          AbstractEvents.FOCUSEVENT_DEACTIVATED);
        stagePeer.setFocused(focused, focusCause);
    }

    private class HostContainer implements HostInterface {

        @Override
        public void setEmbeddedStage(EmbeddedStageInterface embeddedStage) {
            stagePeer = embeddedStage;
            if (stagePeer == null) {
                return;
            }
            if (pWidth > 0 && pHeight > 0) {
                stagePeer.setSize(pWidth, pHeight);
            }
            if (FXCanvas.this.isFocusControl()) {
                stagePeer.setFocused(true, AbstractEvents.FOCUSEVENT_ACTIVATED);
            }
            sendMoveEventToFX();
            sendResizeEventToFX();
        }

        TransferMode getTransferMode(int bits) {
            switch (bits) {
                case DND.DROP_COPY:
                    return TransferMode.COPY;
                case DND.DROP_MOVE:
                case DND.DROP_TARGET_MOVE:
                    return TransferMode.MOVE;
                case DND.DROP_LINK:
                    return TransferMode.LINK;
                default:
                   return null;
            }
        }

        Set<TransferMode> getTransferModes(int bits) {
            Set<TransferMode> set = new HashSet<TransferMode>();
            if ((bits & DND.DROP_COPY) != 0) set.add(TransferMode.COPY);
            if ((bits & DND.DROP_MOVE) != 0) set.add(TransferMode.MOVE);
            if ((bits & DND.DROP_TARGET_MOVE) != 0) set.add(TransferMode.MOVE);
            if ((bits & DND.DROP_LINK) != 0) set.add(TransferMode.LINK);
            return set;
        }

        ImageData createImageData(Pixels pixels) {
            if (pixels == null) return null;
            int width = pixels.getWidth();
            int height = pixels.getHeight();
            int bpr = width * 4;
            int dataSize = bpr * height;
            byte[] buffer = new byte[dataSize];
            byte[] alphaData = new byte[width * height];
            if (pixels.getBytesPerComponent() == 1) {
                // ByteBgraPre
                ByteBuffer pixbuf = (ByteBuffer) pixels.getPixels();
                for (int y = 0, offset = 0, alphaOffset = 0; y < height; y++) {
                    for (int x = 0; x < width; x++, offset += 4) {
                        byte b = pixbuf.get();
                        byte g = pixbuf.get();
                        byte r = pixbuf.get();
                        byte a = pixbuf.get();
                        // non premultiplied ?
                        alphaData[alphaOffset++] = a;
                        buffer[offset] = b;
                        buffer[offset + 1] = g;
                        buffer[offset + 2] = r;
                        buffer[offset + 3] = 0;// alpha
                    }
                }
            } else if (pixels.getBytesPerComponent() == 4) {
                // IntArgbPre
                IntBuffer pixbuf = (IntBuffer) pixels.getPixels();
                for (int y = 0, offset = 0, alphaOffset = 0; y < height; y++) {
                    for (int x = 0; x < width; x++, offset += 4) {
                        int pixel = pixbuf.get();
                        byte b = (byte) (pixel & 0xFF);
                        byte g = (byte) ((pixel >> 8) & 0xFF);
                        byte r = (byte) ((pixel >> 16) & 0xFF);
                        byte a = (byte) ((pixel >> 24) & 0xFF);
                        // non premultiplied ?
                        alphaData[alphaOffset++] = a;
                        buffer[offset] = b;
                        buffer[offset + 1] = g;
                        buffer[offset + 2] = r;
                        buffer[offset + 3] = 0;// alpha
                    }
                }
            } else {
                return null;
            }
            PaletteData palette = new PaletteData(0xFF00, 0xFF0000, 0xFF000000);
            ImageData imageData = new ImageData(width, height, 32, palette, 4, buffer);
            imageData.alphaData = alphaData;
            return imageData;
        }

        // Consider using dragAction
        private DragSource createDragSource(final EmbeddedSceneDSInterface fxDragSource, TransferMode dragAction) {
            Transfer [] transfers = getTransferTypes(fxDragSource.getMimeTypes());
            if (transfers.length == 0) return null;
            int dragOperation = getDragActions(fxDragSource.getSupportedActions());
            final DragSource dragSource = new DragSource(FXCanvas.this, dragOperation);
            dragSource.setTransfer(transfers);
            dragSource.addDragListener(new DragSourceListener() {
                public void dragFinished(org.eclipse.swt.dnd.DragSourceEvent event) {
                    dragSource.dispose();
                    fxDragSource.dragDropEnd(getTransferMode(event.detail));
                }
                public void dragSetData(org.eclipse.swt.dnd.DragSourceEvent event) {
                    Transfer [] transfers = dragSource.getTransfer();
                    for (int i=0; i<transfers.length; i++) {
                        if (transfers[i].isSupportedType(event.dataType)) {
                            String mime = getMime(transfers[i]);
                            if (mime != null) {
                                event.doit = true;
                                event.data = fxDragSource.getData(mime);
                                if (event.data instanceof Pixels) {
                                    event.data = createImageData((Pixels)event.data);
                                }
                                return;
                            }
                        }
                        event.doit = false;
                    }
                }
                public void dragStart(org.eclipse.swt.dnd.DragSourceEvent event) {
                }
            });
            return dragSource;
        }

        int getDragAction(TransferMode tm) {
            if (tm == null) return DND.DROP_NONE;
            switch (tm) {
                case COPY: return DND.DROP_COPY;
                case MOVE: return DND.DROP_MOVE;
                case LINK: return DND.DROP_LINK;
                default:
                    throw new IllegalArgumentException("Invalid transfer mode");
            }
        }

        int getDragActions(Set<TransferMode> set) {
            int result = 0;
            for (TransferMode mode : set) {
                result |= getDragAction(mode);
            }
            return result;
        }

        Transfer getTransferType(String mime) {
            if (mime.equals("text/plain")) return TextTransfer.getInstance();
            if (mime.equals("text/rtf")) return RTFTransfer.getInstance();
            if (mime.equals("text/html")) return HTMLTransfer.getInstance();
            if (mime.equals("text/uri-list")) return URLTransfer.getInstance();
            if (mime.equals("application/x-java-rawimage")) return ImageTransfer.getInstance();
            if (mime.equals("application/x-java-file-list") || mime.equals("java.file-list")) {
                return FileTransfer.getInstance();
            }
            return getCustomTransfer(mime);
        }

        Transfer [] getTransferTypes(String [] mimeTypes) {
            int count= 0;
            Transfer [] transfers = new Transfer [mimeTypes.length];
            for (int i=0; i<mimeTypes.length; i++) {
                Transfer transfer = getTransferType(mimeTypes[i]);
                if (transfer != null) transfers [count++] = transfer;
            }
            if (count != mimeTypes.length) {
                Transfer [] newTransfers = new Transfer[count];
                System.arraycopy(transfers, 0, newTransfers, 0, count);
                transfers = newTransfers;
            }
            return transfers;
        }

        String getMime(Transfer transfer) {
            if (transfer.equals(TextTransfer.getInstance())) return "text/plain";
            if (transfer.equals(RTFTransfer.getInstance())) return "text/rtf"; ;
            if (transfer.equals( HTMLTransfer.getInstance())) return "text/html";
            if (transfer.equals(URLTransfer.getInstance())) return "text/uri-list";
            if (transfer.equals( ImageTransfer.getInstance())) return "application/x-java-rawimage";
            if (transfer.equals(FileTransfer.getInstance())) return "application/x-java-file-list";
            if (transfer instanceof CustomTransfer) return ((CustomTransfer)transfer).getMime();
            return null;
        }

        String [] getMimes(Transfer [] transfers, TransferData data) {
            int count= 0;
            String [] result = new String [transfers.length];
            for (int i=0; i<transfers.length; i++) {
                if (transfers[i].isSupportedType(data)) {
                    result [count++] = getMime (transfers [i]);
                }
            }
            if (count != result.length) {
                String [] newResult = new String[count];
                System.arraycopy(result, 0, newResult, 0, count);
                result = newResult;
            }
            return result;
        }

        DropTarget createDropTarget(EmbeddedSceneInterface embeddedScene) {
            final DropTarget dropTarget = new DropTarget(FXCanvas.this, DND.DROP_COPY | DND.DROP_LINK | DND.DROP_MOVE);
            final EmbeddedSceneDTInterface fxDropTarget = embeddedScene.createDropTarget();
            dropTarget.setTransfer(getAllTransfers());
            dropTarget.addDropListener(new DropTargetListener() {
                Object data;
                // In SWT, the list of available types that the source can provide
                // is part of the event.  FX queries this directly from the operating
                // system and bypasses SWT.  This variable is commented out to remind
                // us of this potential inconsistency.
                //
                //TransferData [] transferData;
                TransferData currentTransferData;
                boolean ignoreLeave;
                int detail = DND.DROP_NONE, operations = DND.DROP_NONE;
                EmbeddedSceneDSInterface fxDragSource = new EmbeddedSceneDSInterface() {
                    public Set<TransferMode> getSupportedActions() {
                        return getTransferModes(operations);
                    }
                    public Object getData(String mimeType) {
                        // NOTE: get the data for the requested mime type, not the default data
                        return data;
                    }
                    public String[] getMimeTypes() {
                        if (currentTransferData == null) return new String [0];
                        return getMimes(getAllTransfers(), currentTransferData);
                    }
                    public boolean isMimeTypeAvailable(String mimeType) {
                        String [] mimes = getMimeTypes();
                        for (int i=0; i<mimes.length; i++) {
                            if (mimes[i].equals(mimeType)) return true;
                        }
                        return false;
                    }
                    public void dragDropEnd(TransferMode performedAction) {
                        data = null;
                        //transferData = null;
                        currentTransferData = null;
                    }
                };
                public void dragEnter(DropTargetEvent event) {
                    ignoreLeave = false;
                    dropTarget.setTransfer(getAllTransfers());
                    detail = event.detail;
                    operations = event.operations;
                    dragOver (event, true, detail);
                }
                public void dragLeave(DropTargetEvent event) {
                    detail = operations = DND.DROP_NONE;
                    data = null;
                    //transferData = null;
                    currentTransferData = null;
                    getDisplay().asyncExec(() -> {
                        if (ignoreLeave) return;
                        fxDropTarget.handleDragLeave();
                    });
                }
                public void dragOperationChanged(DropTargetEvent event) {
                    detail = event.detail;
                    operations = event.operations;
                    dragOver(event, false, detail);
                }
                public void dragOver(DropTargetEvent event) {
                    operations = event.operations;
                    dragOver (event, false, detail);
                }
                public void dragOver(DropTargetEvent event, boolean enter, int detail) {
                    //transferData = event.dataTypes;
                    currentTransferData = event.currentDataType;
                    Point pt = toControl(event.x, event.y);
                    if (detail == DND.DROP_NONE) detail = DND.DROP_COPY;
                    TransferMode acceptedMode, recommendedMode = getTransferMode(detail);
                    if (enter) {
                        acceptedMode = fxDropTarget.handleDragEnter(pt.x, pt.y, event.x, event.y, recommendedMode, fxDragSource);
                    } else {
                        acceptedMode = fxDropTarget.handleDragOver(pt.x, pt.y, event.x, event.y, recommendedMode);
                    }
                    event.detail = getDragAction(acceptedMode);
                }
                public void drop(DropTargetEvent event) {
                    detail = event.detail;
                    operations = event.operations;
                    data = event.data;
                    //transferData = event.dataTypes;
                    currentTransferData = event.currentDataType;
                    Point pt = toControl(event.x, event.y);
                    TransferMode recommendedDropAction = getTransferMode(event.detail);
                    TransferMode acceptedMode = fxDropTarget.handleDragDrop(pt.x, pt.y, event.x, event.y, recommendedDropAction);
                    event.detail = getDragAction(acceptedMode);
                    data = null;
                    //transferData = null;
                    currentTransferData = null;
                }
                public void dropAccept(DropTargetEvent event) {
                    ignoreLeave = true;
                }
            });
            return dropTarget;
        }

        @Override
        public void setEmbeddedScene(EmbeddedSceneInterface embeddedScene) {
            scenePeer = embeddedScene;
            if (scenePeer == null) {
                return;
            }
            if (pWidth > 0 && pHeight > 0) {
                scenePeer.setSize(pWidth, pHeight);
            }
            double scaleFactor = getScaleFactor();
            resizePixelBuffer(scaleFactor);
            lastScaleFactor = scaleFactor;
            scenePeer.setPixelScaleFactors((float)scaleFactor, (float)scaleFactor);
            scenePeer.setDragStartListener((fxDragSource, dragAction) -> {
                Platform.runLater(() -> {
                    DragSource dragSource = createDragSource(fxDragSource, dragAction);
                    if (dragSource == null) {
                        fxDragSource.dragDropEnd(null);
                    } else {
                        updateDropTarget();
                        FXCanvas.this.notifyListeners(SWT.DragDetect, null);
                    }
                });
            });
            //Force the old drop target to be disposed before creating a new one
            setDropTarget(null);
            setDropTarget(createDropTarget(embeddedScene));
        }

        @Override
        public boolean requestFocus() {
            Display.getDefault().asyncExec(() -> {
                if (isDisposed()) return;
                FXCanvas.this.forceFocus();
            });
            return true;
        }

        @Override
        public boolean traverseFocusOut(boolean bln) {
            // RT-18085: not implemented
            return true;
        }

        Object lock = new Object();
        boolean queued = false;
        public void repaint() {
            synchronized (lock) {
                if (queued) return;
                queued = true;
                Display.getDefault().asyncExec(() -> {
                    try {
                        if (isDisposed()) return;
                        FXCanvas.this.redraw();
                    } finally {
                        synchronized (lock) {
                            queued = false;
                        }
                    }
                });
            }
        }

        @Override
        public void setPreferredSize(int width, int height) {
            FXCanvas.this.pPreferredWidth = width;
            FXCanvas.this.pPreferredHeight = height;
            //FXCanvas.this.getShell().layout(new Control []{FXCanvas.this}, SWT.DEFER);
        }

        @Override
        public void setEnabled(boolean bln) {
            FXCanvas.this.setEnabled(bln);
        }

        @Override
        public void setCursor(CursorFrame cursorFrame) {
            FXCanvas.this.setCursor(getPlatformCursor(cursorFrame));
        }

        private org.eclipse.swt.graphics.Cursor getPlatformCursor(final CursorFrame cursorFrame) {
            /*
             * On the Mac, setting the cursor during drag and drop clears the move
             * and link indicators.  The fix is to set the default cursor for the
             * control (which is null) when the FX explicitly requests the default
             * cursor.  This will preserve the drag and drop indicators.
             */
            if (cursorFrame.getCursorType() == CursorType.DEFAULT) {
                return null;
            }
            final org.eclipse.swt.graphics.Cursor cachedPlatformCursor =
                    cursorFrame.getPlatformCursor(org.eclipse.swt.graphics.Cursor.class);
            if (cachedPlatformCursor != null) {
                // platform cursor already cached
                return cachedPlatformCursor;
            }

            // platform cursor not cached yet
            final org.eclipse.swt.graphics.Cursor platformCursor =
                    SWTCursors.embedCursorToCursor(cursorFrame);
            cursorFrame.setPlatforCursor(org.eclipse.swt.graphics.Cursor.class, platformCursor);

            return platformCursor;
        }

        @Override
        public boolean grabFocus() {
            // RT-27949: not implemented
            return true;
        }

        @Override
        public void ungrabFocus() {
            // RT-27949: not implemented
        }
    }
}
