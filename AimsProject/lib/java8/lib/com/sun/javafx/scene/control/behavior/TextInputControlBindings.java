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

package com.sun.javafx.scene.control.behavior;

import java.util.ArrayList;
import java.util.List;

import javafx.application.ConditionalFeature;
import com.sun.javafx.PlatformUtil;
import com.sun.javafx.application.PlatformImpl;

import static javafx.scene.input.KeyCode.*;
import static javafx.scene.input.KeyEvent.*;

/**
 */
public class TextInputControlBindings {
    protected static final List<KeyBinding> BINDINGS = new ArrayList<KeyBinding>();
    static {
        // Function keys
        BINDINGS.add(new KeyBinding(F1, KEY_PRESSED,    "Function"));
        BINDINGS.add(new KeyBinding(F2, KEY_PRESSED,    "Function"));
        BINDINGS.add(new KeyBinding(F3, KEY_PRESSED,    "Function"));
        BINDINGS.add(new KeyBinding(F4, KEY_PRESSED,    "Function"));
        BINDINGS.add(new KeyBinding(F5, KEY_PRESSED,    "Function"));
        BINDINGS.add(new KeyBinding(F6, KEY_PRESSED,    "Function"));
        BINDINGS.add(new KeyBinding(F7, KEY_PRESSED,    "Function"));
        BINDINGS.add(new KeyBinding(F8, KEY_PRESSED,    "Function"));
        BINDINGS.add(new KeyBinding(F9, KEY_PRESSED,    "Function"));
        BINDINGS.add(new KeyBinding(F10, KEY_PRESSED,   "Function"));
        BINDINGS.add(new KeyBinding(F11, KEY_PRESSED,   "Function"));
        BINDINGS.add(new KeyBinding(F12, KEY_PRESSED,   "Function"));

        // caret movement
        BINDINGS.add(new KeyBinding(RIGHT, KEY_PRESSED,       "Right"));
        BINDINGS.add(new KeyBinding(KP_RIGHT, KEY_PRESSED,    "Right"));
        BINDINGS.add(new KeyBinding(LEFT, KEY_PRESSED,        "Left"));
        BINDINGS.add(new KeyBinding(KP_LEFT, KEY_PRESSED,     "Left"));
        BINDINGS.add(new KeyBinding(UP, KEY_PRESSED,          "Home"));
        BINDINGS.add(new KeyBinding(KP_UP, KEY_PRESSED,       "Home"));
        BINDINGS.add(new KeyBinding(HOME, KEY_PRESSED,        "Home"));
        BINDINGS.add(new KeyBinding(DOWN, KEY_PRESSED,        "End"));
        BINDINGS.add(new KeyBinding(KP_DOWN, KEY_PRESSED,     "End"));
        BINDINGS.add(new KeyBinding(END, KEY_PRESSED,         "End"));
        BINDINGS.add(new KeyBinding(ENTER, KEY_PRESSED,       "Fire"));
        // deletion
        BINDINGS.add(new KeyBinding(BACK_SPACE, KEY_PRESSED,  "DeletePreviousChar"));
        BINDINGS.add(new KeyBinding(DELETE, KEY_PRESSED,      "DeleteNextChar"));
        // cut/copy/paste
        BINDINGS.add(new KeyBinding(CUT, KEY_PRESSED,         "Cut"));
        BINDINGS.add(new KeyBinding(DELETE, KEY_PRESSED,      "Cut").shift());
        BINDINGS.add(new KeyBinding(COPY, KEY_PRESSED,        "Copy"));
        BINDINGS.add(new KeyBinding(PASTE, KEY_PRESSED,       "Paste"));
        BINDINGS.add(new KeyBinding(INSERT, KEY_PRESSED,      "Paste").shift());// does this belong on mac?
        // selection
        BINDINGS.add(new KeyBinding(RIGHT, KEY_PRESSED,       "SelectRight").shift());
        BINDINGS.add(new KeyBinding(KP_RIGHT, KEY_PRESSED,    "SelectRight").shift());
        BINDINGS.add(new KeyBinding(LEFT, KEY_PRESSED,        "SelectLeft").shift());
        BINDINGS.add(new KeyBinding(KP_LEFT, KEY_PRESSED,     "SelectLeft").shift());
        BINDINGS.add(new KeyBinding(UP, KEY_PRESSED,          "SelectHome").shift());
        BINDINGS.add(new KeyBinding(KP_UP, KEY_PRESSED,       "SelectHome").shift());
        BINDINGS.add(new KeyBinding(DOWN, KEY_PRESSED,        "SelectEnd").shift());
        BINDINGS.add(new KeyBinding(KP_DOWN, KEY_PRESSED,     "SelectEnd").shift());

        BINDINGS.add(new KeyBinding(BACK_SPACE, KEY_PRESSED,  "DeletePreviousChar").shift());
        BINDINGS.add(new KeyBinding(DELETE, KEY_PRESSED,      "DeleteNextChar").shift());

        // platform specific settings
        if (PlatformUtil.isMac()) {
            BINDINGS.add(new KeyBinding(HOME, KEY_PRESSED,       "SelectHomeExtend").shift());
            BINDINGS.add(new KeyBinding(END, KEY_PRESSED,        "SelectEndExtend").shift());

            BINDINGS.add(new KeyBinding(HOME, KEY_PRESSED,       "Home").shortcut());
            BINDINGS.add(new KeyBinding(END, KEY_PRESSED,        "End").shortcut());
            BINDINGS.add(new KeyBinding(LEFT, KEY_PRESSED,       "Home").shortcut());
            BINDINGS.add(new KeyBinding(KP_LEFT, KEY_PRESSED,    "Home").shortcut());
            BINDINGS.add(new KeyBinding(RIGHT, KEY_PRESSED,      "End").shortcut());
            BINDINGS.add(new KeyBinding(KP_RIGHT, KEY_PRESSED,   "End").shortcut());
            BINDINGS.add(new KeyBinding(LEFT, KEY_PRESSED,       "LeftWord").alt());
            BINDINGS.add(new KeyBinding(KP_LEFT, KEY_PRESSED,    "LeftWord").alt());
            BINDINGS.add(new KeyBinding(RIGHT, KEY_PRESSED,      "RightWord").alt());
            BINDINGS.add(new KeyBinding(KP_RIGHT, KEY_PRESSED,   "RightWord").alt());
            BINDINGS.add(new KeyBinding(DELETE, KEY_PRESSED,     "DeleteNextWord").alt());
            BINDINGS.add(new KeyBinding(BACK_SPACE, KEY_PRESSED, "DeletePreviousWord").alt());
            BINDINGS.add(new KeyBinding(BACK_SPACE, KEY_PRESSED, "DeleteFromLineStart").shortcut());
            BINDINGS.add(new KeyBinding(X, KEY_PRESSED,          "Cut").shortcut());
            BINDINGS.add(new KeyBinding(C, KEY_PRESSED,          "Copy").shortcut());
            BINDINGS.add(new KeyBinding(INSERT, KEY_PRESSED,     "Copy").shortcut());
            BINDINGS.add(new KeyBinding(V, KEY_PRESSED,          "Paste").shortcut());
            BINDINGS.add(new KeyBinding(HOME, KEY_PRESSED,       "SelectHome").shift().shortcut());
            BINDINGS.add(new KeyBinding(END, KEY_PRESSED,        "SelectEnd").shift().shortcut());
            BINDINGS.add(new KeyBinding(LEFT, KEY_PRESSED,       "SelectHomeExtend").shift().shortcut());
            BINDINGS.add(new KeyBinding(KP_LEFT, KEY_PRESSED,    "SelectHomeExtend").shift().shortcut());
            BINDINGS.add(new KeyBinding(RIGHT, KEY_PRESSED,      "SelectEndExtend").shift().shortcut());
            BINDINGS.add(new KeyBinding(KP_RIGHT, KEY_PRESSED,   "SelectEndExtend").shift().shortcut());
            BINDINGS.add(new KeyBinding(A, KEY_PRESSED,          "SelectAll").shortcut());
            BINDINGS.add(new KeyBinding(LEFT, KEY_PRESSED,       "SelectLeftWord").shift().alt());
            BINDINGS.add(new KeyBinding(KP_LEFT, KEY_PRESSED,    "SelectLeftWord").shift().alt());
            BINDINGS.add(new KeyBinding(RIGHT, KEY_PRESSED,      "SelectRightWord").shift().alt());
            BINDINGS.add(new KeyBinding(KP_RIGHT, KEY_PRESSED,   "SelectRightWord").shift().alt());
            BINDINGS.add(new KeyBinding(Z, KEY_PRESSED,          "Undo").shortcut());
            BINDINGS.add(new KeyBinding(Z, KEY_PRESSED,          "Redo").shift().shortcut());
        } else {
            BINDINGS.add(new KeyBinding(HOME, KEY_PRESSED,       "SelectHome").shift());
            BINDINGS.add(new KeyBinding(END, KEY_PRESSED,        "SelectEnd").shift());

            BINDINGS.add(new KeyBinding(HOME, KEY_PRESSED,       "Home").ctrl());
            BINDINGS.add(new KeyBinding(END, KEY_PRESSED,        "End").ctrl());
            BINDINGS.add(new KeyBinding(LEFT, KEY_PRESSED,       "LeftWord").ctrl());
            BINDINGS.add(new KeyBinding(KP_LEFT, KEY_PRESSED,    "LeftWord").ctrl());
            BINDINGS.add(new KeyBinding(RIGHT, KEY_PRESSED,      "RightWord").ctrl());
            BINDINGS.add(new KeyBinding(KP_RIGHT, KEY_PRESSED,   "RightWord").ctrl());
            BINDINGS.add(new KeyBinding(H, KEY_PRESSED,          "DeletePreviousChar").ctrl());
            BINDINGS.add(new KeyBinding(DELETE, KEY_PRESSED,     "DeleteNextWord").ctrl());
            BINDINGS.add(new KeyBinding(BACK_SPACE, KEY_PRESSED, "DeletePreviousWord").ctrl());
            BINDINGS.add(new KeyBinding(X, KEY_PRESSED,          "Cut").ctrl());
            BINDINGS.add(new KeyBinding(C, KEY_PRESSED,          "Copy").ctrl());
            BINDINGS.add(new KeyBinding(INSERT, KEY_PRESSED,     "Copy").ctrl());
            BINDINGS.add(new KeyBinding(V, KEY_PRESSED,          "Paste").ctrl());
            BINDINGS.add(new KeyBinding(HOME, KEY_PRESSED,       "SelectHome").ctrl().shift());
            BINDINGS.add(new KeyBinding(END, KEY_PRESSED,        "SelectEnd").ctrl().shift());
            BINDINGS.add(new KeyBinding(LEFT, KEY_PRESSED,       "SelectLeftWord").ctrl().shift());
            BINDINGS.add(new KeyBinding(KP_LEFT, KEY_PRESSED,    "SelectLeftWord").ctrl().shift());
            BINDINGS.add(new KeyBinding(RIGHT, KEY_PRESSED,      "SelectRightWord").ctrl().shift());
            BINDINGS.add(new KeyBinding(KP_RIGHT, KEY_PRESSED,   "SelectRightWord").ctrl().shift());
            BINDINGS.add(new KeyBinding(A, KEY_PRESSED,          "SelectAll").ctrl());
            BINDINGS.add(new KeyBinding(BACK_SLASH, KEY_PRESSED, "Unselect").ctrl());
            if (PlatformUtil.isLinux()) {
                BINDINGS.add(new KeyBinding(Z, KEY_PRESSED,          "Undo").ctrl());
                BINDINGS.add(new KeyBinding(Z, KEY_PRESSED,          "Redo").ctrl().shift());
            } else {  // Windows
                BINDINGS.add(new KeyBinding(Z, KEY_PRESSED,          "Undo").ctrl());
                BINDINGS.add(new KeyBinding(Y, KEY_PRESSED,          "Redo").ctrl());
            }
        }
        // Any other key press first goes to normal text input
        // Note this is KEY_TYPED because otherwise the character is not available in the event.
        BINDINGS.add(new KeyBinding(null, KEY_TYPED, "InputCharacter")
                .alt(OptionalBoolean.ANY)
                .shift(OptionalBoolean.ANY)
                .ctrl(OptionalBoolean.ANY)
                .meta(OptionalBoolean.ANY));
        // Traversal Bindings
        BINDINGS.add(new KeyBinding(TAB, "TraverseNext"));
        BINDINGS.add(new KeyBinding(TAB, "TraversePrevious").shift());
        BINDINGS.add(new KeyBinding(TAB, "TraverseNext").ctrl());
        BINDINGS.add(new KeyBinding(TAB, "TraversePrevious").shift().ctrl());

        // The following keys are forwarded to the parent container
        BINDINGS.add(new KeyBinding(ESCAPE, "Cancel"));
        BINDINGS.add(new KeyBinding(F10, "ToParent"));
        // TODO XXX DEBUGGING ONLY
//        BINDINGS.add(new KeyBinding(F4, "TraverseDebug").alt().ctrl().shift());
        /*DEBUG*/if (PlatformImpl.isSupported(ConditionalFeature.VIRTUAL_KEYBOARD)) {
            BINDINGS.add(new KeyBinding(DIGIT9, "UseVK").ctrl().shift());
        }
    }
}
