/*
 * Copyright (c) 2014, Oracle and/or its affiliates. All rights reserved.
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
package javafx.scene.control;

import com.sun.javafx.scene.control.skin.ComboBoxListViewSkin;
import com.sun.javafx.scene.control.skin.SpinnerSkin;
import javafx.beans.NamedArg;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.AccessibleAction;
import javafx.scene.AccessibleAttribute;
import javafx.scene.AccessibleRole;
import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;

/**
 * A single line text field that lets the user select a number or an object
 * value from an ordered sequence. Spinners typically provide a pair of tiny
 * arrow buttons for stepping through the elements of the sequence. The keyboard
 * up/down arrow keys also cycle through the elements. The user may also be
 * allowed to type a (legal) value directly into the spinner. Although combo
 * boxes provide similar functionality, spinners are sometimes preferred because
 * they don't require a drop down list that can obscure important data, and also
 * because they allow for features such as
 * {@link SpinnerValueFactory#wrapAroundProperty() wrapping}
 * and simpler specification of 'infinite' data models (the
 * {@link SpinnerValueFactory SpinnerValueFactory}, rather than using a
 * {@link javafx.collections.ObservableList ObservableList} data model like many
 * other JavaFX UI controls.
 *
 * <p>A Spinner's sequence value is defined by its
 * {@link SpinnerValueFactory SpinnerValueFactory}. The value factory
 * can be specified as a constructor argument and changed with the
 * {@link #valueFactoryProperty() value factory property}. SpinnerValueFactory
 * classes for some common types are provided with JavaFX, including:
 *
 * <br/>
 *
 * <ul>
 *     <li>{@link SpinnerValueFactory.IntegerSpinnerValueFactory}</li>
 *     <li>{@link SpinnerValueFactory.DoubleSpinnerValueFactory}</li>
 *     <li>{@link SpinnerValueFactory.ListSpinnerValueFactory}</li>
 * </ul>
 *
 * <br/>
 *
 * <p>A Spinner has a TextField child component that is responsible for displaying
 * and potentially changing the current {@link #valueProperty() value} of the
 * Spinner, which is called the {@link #editorProperty() editor}. By default the
 * Spinner is non-editable, but input can be accepted if the
 * {@link #editableProperty() editable property} is set to true. The Spinner
 * editor stays in sync with the value factory by listening for changes to the
 * {@link SpinnerValueFactory#valueProperty() value property} of the value factory.
 * If the user has changed the value displayed in the editor it is possible for
 * the Spinner {@link #valueProperty() value} to differ from that of the editor.
 * To make sure the model has the same value as the editor, the user must commit
 * the edit using the Enter key.
 *
 * @see SpinnerValueFactory
 * @param <T> The type of all values that can be iterated through in the Spinner.
 *            Common types include Integer and String.
 * @since JavaFX 8u40
 */
public class Spinner<T> extends Control {

    // default style class, puts arrows on right, stacked vertically
    private static final String DEFAULT_STYLE_CLASS = "spinner";

    /** The arrows are placed on the right of the Spinner, pointing horizontally (i.e. left and right). */
    public static final String STYLE_CLASS_ARROWS_ON_RIGHT_HORIZONTAL = "arrows-on-right-horizontal";

    /** The arrows are placed on the left of the Spinner, pointing vertically (i.e. up and down). */
    public static final String STYLE_CLASS_ARROWS_ON_LEFT_VERTICAL = "arrows-on-left-vertical";

    /** The arrows are placed on the left of the Spinner, pointing horizontally (i.e. left and right). */
    public static final String STYLE_CLASS_ARROWS_ON_LEFT_HORIZONTAL = "arrows-on-left-horizontal";

    /** The arrows are placed above and beneath the spinner, stretching to take the entire width. */
    public static final String STYLE_CLASS_SPLIT_ARROWS_VERTICAL = "split-arrows-vertical";

    /** The decrement arrow is placed on the left of the Spinner, and the increment on the right. */
    public static final String STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL = "split-arrows-horizontal";



    /***************************************************************************
     *                                                                         *
     * Constructors                                                            *
     *                                                                         *
     **************************************************************************/

    /**
     * Constructs a default Spinner instance, with the default 'spinner' style
     * class and a non-editable editor.
     */
    public Spinner() {
        getStyleClass().add(DEFAULT_STYLE_CLASS);
        setAccessibleRole(AccessibleRole.SPINNER);

        getEditor().setOnAction(action -> {
            String text = getEditor().getText();
            SpinnerValueFactory<T> valueFactory = getValueFactory();
            if (valueFactory != null) {
                StringConverter<T> converter = valueFactory.getConverter();
                if (converter != null) {
                    T value = converter.fromString(text);
                    valueFactory.setValue(value);
                }
            }
        });

        getEditor().editableProperty().bind(editableProperty());

        value.addListener((o, oldValue, newValue) -> setText(newValue));

        // Fix for RT-29885
        getProperties().addListener((MapChangeListener<Object, Object>) change -> {
            if (change.wasAdded()) {
                if (change.getKey() == "FOCUSED") {
                    setFocused((Boolean)change.getValueAdded());
                    getProperties().remove("FOCUSED");
                }
            }
        });
        // End of fix for RT-29885
    }

    /**
     * Creates a Spinner instance with the
     * {@link #valueFactoryProperty() value factory} set to be an instance
     * of {@link SpinnerValueFactory.IntegerSpinnerValueFactory}. Note that
     * if this constructor is called, the only valid generic type for the
     * Spinner instance is Integer, i.e. Spinner&lt;Integer&gt;.
     *
     * @param min The minimum allowed integer value for the Spinner.
     * @param max The maximum allowed integer value for the Spinner.
     * @param initialValue The value of the Spinner when first instantiated, must
     *                     be within the bounds of the min and max arguments, or
     *                     else the min value will be used.
     */
    public Spinner(@NamedArg("min") int min,
                   @NamedArg("max") int max,
                   @NamedArg("initialValue") int initialValue) {
        // This only works if the Spinner is of type Integer
        this((SpinnerValueFactory<T>)new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, initialValue));
    }

    /**
     * Creates a Spinner instance with the
     * {@link #valueFactoryProperty() value factory} set to be an instance
     * of {@link SpinnerValueFactory.IntegerSpinnerValueFactory}. Note that
     * if this constructor is called, the only valid generic type for the
     * Spinner instance is Integer, i.e. Spinner&lt;Integer&gt;.
     *
     * @param min The minimum allowed integer value for the Spinner.
     * @param max The maximum allowed integer value for the Spinner.
     * @param initialValue The value of the Spinner when first instantiated, must
     *                     be within the bounds of the min and max arguments, or
     *                     else the min value will be used.
     * @param amountToStepBy The amount to increment or decrement by, per step.
     */
    public Spinner(@NamedArg("min") int min,
                   @NamedArg("max") int max,
                   @NamedArg("initialValue") int initialValue,
                   @NamedArg("amountToStepBy") int amountToStepBy) {
        // This only works if the Spinner is of type Integer
        this((SpinnerValueFactory<T>)new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, initialValue, amountToStepBy));
    }

    /**
     * Creates a Spinner instance with the
     * {@link #valueFactoryProperty() value factory} set to be an instance
     * of {@link SpinnerValueFactory.DoubleSpinnerValueFactory}. Note that
     * if this constructor is called, the only valid generic type for the
     * Spinner instance is Double, i.e. Spinner&lt;Double&gt;.
     *
     * @param min The minimum allowed double value for the Spinner.
     * @param max The maximum allowed double value for the Spinner.
     * @param initialValue The value of the Spinner when first instantiated, must
     *                     be within the bounds of the min and max arguments, or
     *                     else the min value will be used.
     */
    public Spinner(@NamedArg("min") double min,
                   @NamedArg("max") double max,
                   @NamedArg("initialValue") double initialValue) {
        // This only works if the Spinner is of type Double
        this((SpinnerValueFactory<T>)new SpinnerValueFactory.DoubleSpinnerValueFactory(min, max, initialValue));
    }

    /**
     * Creates a Spinner instance with the
     * {@link #valueFactoryProperty() value factory} set to be an instance
     * of {@link SpinnerValueFactory.DoubleSpinnerValueFactory}. Note that
     * if this constructor is called, the only valid generic type for the
     * Spinner instance is Double, i.e. Spinner&lt;Double&gt;.
     *
     * @param min The minimum allowed double value for the Spinner.
     * @param max The maximum allowed double value for the Spinner.
     * @param initialValue The value of the Spinner when first instantiated, must
     *                     be within the bounds of the min and max arguments, or
     *                     else the min value will be used.
     * @param amountToStepBy The amount to increment or decrement by, per step.
     */
    public Spinner(@NamedArg("min") double min,
                   @NamedArg("max") double max,
                   @NamedArg("initialValue") double initialValue,
                   @NamedArg("amountToStepBy") double amountToStepBy) {
        // This only works if the Spinner is of type Double
        this((SpinnerValueFactory<T>)new SpinnerValueFactory.DoubleSpinnerValueFactory(min, max, initialValue, amountToStepBy));
    }

    /**
     * Creates a Spinner instance with the
     * {@link #valueFactoryProperty() value factory} set to be an instance
     * of {@link SpinnerValueFactory.LocalDateSpinnerValueFactory}. Note that
     * if this constructor is called, the only valid generic type for the
     * Spinner instance is LocalDate, i.e. Spinner&lt;LocalDate&gt;.
     *
     * @param min The minimum allowed LocalDate value for the Spinner.
     * @param max The maximum allowed LocalDate value for the Spinner.
     * @param initialValue The value of the Spinner when first instantiated, must
     *                     be within the bounds of the min and max arguments, or
     *                     else the min value will be used.
     */
    Spinner(@NamedArg("min") LocalDate min,
                   @NamedArg("max") LocalDate max,
                   @NamedArg("initialValue") LocalDate initialValue) {
        // This only works if the Spinner is of type LocalDate
        this((SpinnerValueFactory<T>)new SpinnerValueFactory.LocalDateSpinnerValueFactory(min, max, initialValue));
    }

    /**
     * Creates a Spinner instance with the
     * {@link #valueFactoryProperty() value factory} set to be an instance
     * of {@link SpinnerValueFactory.LocalDateSpinnerValueFactory}. Note that
     * if this constructor is called, the only valid generic type for the
     * Spinner instance is LocalDate, i.e. Spinner&lt;LocalDate&gt;.
     *
     * @param min The minimum allowed LocalDate value for the Spinner.
     * @param max The maximum allowed LocalDate value for the Spinner.
     * @param initialValue The value of the Spinner when first instantiated, must
     *                     be within the bounds of the min and max arguments, or
     *                     else the min value will be used.
     * @param amountToStepBy The amount to increment or decrement by, per step.
     * @param temporalUnit The size of each step (e.g. day, week, month, year, etc).
     */
    Spinner(@NamedArg("min") LocalDate min,
                   @NamedArg("max") LocalDate max,
                   @NamedArg("initialValue") LocalDate initialValue,
                   @NamedArg("amountToStepBy") long amountToStepBy,
                   @NamedArg("temporalUnit") TemporalUnit temporalUnit) {
        // This only works if the Spinner is of type LocalDate
        this((SpinnerValueFactory<T>)new SpinnerValueFactory.LocalDateSpinnerValueFactory(min, max, initialValue, amountToStepBy, temporalUnit));
    }

    /**
     * Creates a Spinner instance with the
     * {@link #valueFactoryProperty() value factory} set to be an instance
     * of {@link SpinnerValueFactory.LocalTimeSpinnerValueFactory}. Note that
     * if this constructor is called, the only valid generic type for the
     * Spinner instance is LocalTime, i.e. Spinner&lt;LocalTime&gt;.
     *
     * @param min The minimum allowed LocalTime value for the Spinner.
     * @param max The maximum allowed LocalTime value for the Spinner.
     * @param initialValue The value of the Spinner when first instantiated, must
     *                     be within the bounds of the min and max arguments, or
     *                     else the min value will be used.
     */
    Spinner(@NamedArg("min") LocalTime min,
                   @NamedArg("max") LocalTime max,
                   @NamedArg("initialValue") LocalTime initialValue) {
        // This only works if the Spinner is of type LocalTime
        this((SpinnerValueFactory<T>)new SpinnerValueFactory.LocalTimeSpinnerValueFactory(min, max, initialValue));
    }

    /**
     * Creates a Spinner instance with the
     * {@link #valueFactoryProperty() value factory} set to be an instance
     * of {@link SpinnerValueFactory.LocalTimeSpinnerValueFactory}. Note that
     * if this constructor is called, the only valid generic type for the
     * Spinner instance is LocalTime, i.e. Spinner&lt;LocalTime&gt;.
     *
     * @param min The minimum allowed LocalTime value for the Spinner.
     * @param max The maximum allowed LocalTime value for the Spinner.
     * @param initialValue The value of the Spinner when first instantiated, must
     *                     be within the bounds of the min and max arguments, or
     *                     else the min value will be used.
     * @param amountToStepBy The amount to increment or decrement by, per step.
     * @param temporalUnit The size of each step (e.g. hour, minute, second, etc).
     */
    Spinner(@NamedArg("min") LocalTime min,
                   @NamedArg("max") LocalTime max,
                   @NamedArg("initialValue") LocalTime initialValue,
                   @NamedArg("amountToStepBy") long amountToStepBy,
                   @NamedArg("temporalUnit") TemporalUnit temporalUnit) {
        // This only works if the Spinner is of type LocalTime
        this((SpinnerValueFactory<T>)new SpinnerValueFactory.LocalTimeSpinnerValueFactory(min, max, initialValue, amountToStepBy, temporalUnit));
    }

    /**
     * Creates a Spinner instance with the
     * {@link #valueFactoryProperty() value factory} set to be an instance
     * of {@link SpinnerValueFactory.ListSpinnerValueFactory}. The
     * Spinner {@link #valueProperty() value property} will be set to the first
     * element of the list, if an element exists, or null otherwise.
     *
     * @param items A list of items that will be stepped through in the Spinner.
     */
    public Spinner(@NamedArg("items") ObservableList<T> items) {
        this(new SpinnerValueFactory.ListSpinnerValueFactory<T>(items));
    }

    /**
     * Creates a Spinner instance with the given value factory set.
     *
     * @param valueFactory The {@link #valueFactoryProperty() value factory} to use.
     */
    public Spinner(@NamedArg("valueFactory") SpinnerValueFactory<T> valueFactory) {
        this();

        setValueFactory(valueFactory);
    }



    /***************************************************************************
     *                                                                         *
     * Public API                                                              *
     *                                                                         *
     **************************************************************************/

    /**
     * Attempts to increment the {@link #valueFactoryProperty() value factory}
     * by one step, by calling the {@link SpinnerValueFactory#increment(int)}
     * method with an argument of one. If the value factory is null, an
     * IllegalStateException is thrown.
     *
     * @throws IllegalStateException if the value factory returned by
     *      calling {@link #getValueFactory()} is null.
     */
    public void increment() {
        increment(1);
    }

    /**
     * Attempts to increment the {@link #valueFactoryProperty() value factory}
     * by the given number of steps, by calling the
     * {@link SpinnerValueFactory#increment(int)}
     * method and forwarding the steps argument to it. If the value factory is
     * null, an IllegalStateException is thrown.
     *
     * @param steps The number of increments that should be performed on the value.
     * @throws IllegalStateException if the value factory returned by
     *      calling {@link #getValueFactory()} is null.
     */
    public void increment(int steps) {
        SpinnerValueFactory<T> valueFactory = getValueFactory();
        if (valueFactory == null) {
            throw new IllegalStateException("Can't increment Spinner with a null SpinnerValueFactory");
        }
        commitEditorText();
        valueFactory.increment(steps);
    }

    /**
     * Attempts to decrement the {@link #valueFactoryProperty() value factory}
     * by one step, by calling the {@link SpinnerValueFactory#decrement(int)}
     * method with an argument of one. If the value factory is null, an
     * IllegalStateException is thrown.
     *
     * @throws IllegalStateException if the value factory returned by
     *      calling {@link #getValueFactory()} is null.
     */
    public void decrement() {
        decrement(1);
    }

    /**
     * Attempts to decrement the {@link #valueFactoryProperty() value factory}
     * by the given number of steps, by calling the
     * {@link SpinnerValueFactory#decrement(int)}
     * method and forwarding the steps argument to it. If the value factory is
     * null, an IllegalStateException is thrown.
     *
     * @param steps The number of decrements that should be performed on the value.
     * @throws IllegalStateException if the value factory returned by
     *      calling {@link #getValueFactory()} is null.
     */
    public void decrement(int steps) {
        SpinnerValueFactory<T> valueFactory = getValueFactory();
        if (valueFactory == null) {
            throw new IllegalStateException("Can't decrement Spinner with a null SpinnerValueFactory");
        }
        commitEditorText();
        valueFactory.decrement(steps);
    }

    /** {@inheritDoc} */
    @Override protected Skin<?> createDefaultSkin() {
        return new SpinnerSkin<>(this);
    }



    /***************************************************************************
     *                                                                         *
     * Properties                                                              *
     *                                                                         *
     **************************************************************************/

    // --- value (a read only, bound property to the value factory value property)
    /**
     * The value property on Spinner is a read-only property, as it is bound to
     * the SpinnerValueFactory
     * {@link SpinnerValueFactory#valueProperty() value property}. Should the
     * {@link #valueFactoryProperty() value factory} change, this value property
     * will be unbound from the old value factory and bound to the new one.
     *
     * <p>If developers wish to modify the value property, they may do so with
     * code in the following form:
     *
     * <pre>
     * {@code
     * Object newValue = ...;
     * spinner.getValueFactory().setValue(newValue);
     * }</pre>
     */
    private ReadOnlyObjectWrapper<T> value = new ReadOnlyObjectWrapper<T>(this, "value");
    public final T getValue() {
        return value.get();
    }
    public final ReadOnlyObjectProperty<T> valueProperty() {
        return value;
    }


    // --- valueFactory
    /**
     * The value factory is the model behind the JavaFX Spinner control - without
     * a value factory installed a Spinner is unusable. It is the role of the
     * value factory to handle almost all aspects of the Spinner, including:
     *
     * <ul>
     *     <li>Representing the current state of the {@link SpinnerValueFactory#valueProperty() value},</li>
     *     <li>{@link SpinnerValueFactory#increment(int) Incrementing}
     *         and {@link SpinnerValueFactory#decrement(int) decrementing} the
     *         value, with one or more steps per call,</li>
     *     <li>{@link SpinnerValueFactory#converterProperty() Converting} text input
     *         from the user (via the Spinner {@link #editorProperty() editor},</li>
     *     <li>Converting {@link SpinnerValueFactory#converterProperty() objects to user-readable strings}
     *         for display on screen</li>
     * </ul>
     */
    private ObjectProperty<SpinnerValueFactory<T>> valueFactory =
            new SimpleObjectProperty<SpinnerValueFactory<T>>(this, "valueFactory") {
                @Override protected void invalidated() {
                    value.unbind();

                    SpinnerValueFactory<T> newFactory = get();
                    if (newFactory != null) {
                        // this binding is what ensures the Spinner.valueProperty()
                        // properly represents the value in the value factory
                        value.bind(newFactory.valueProperty());
                    }
                }
            };
    public final void setValueFactory(SpinnerValueFactory<T> value) {
        valueFactory.setValue(value);
    }
    public final SpinnerValueFactory<T> getValueFactory() {
        return valueFactory.get();
    }
    public final ObjectProperty<SpinnerValueFactory<T>> valueFactoryProperty() {
        return valueFactory;
    }


    // --- editable
    /**
     * The editable property is used to specify whether user input is able to
     * be typed into the Spinner {@link #editorProperty() editor}. If editable
     * is true, user input will be received once the user types and presses
     * the Enter key. At this point the input is passed to the
     * SpinnerValueFactory {@link SpinnerValueFactory#converterProperty() converter}
     * {@link javafx.util.StringConverter#fromString(String)} method.
     * The returned value from this call (of type T) is then sent to the
     * {@link SpinnerValueFactory#setValue(Object)} method. If the value
     * is valid, it will remain as the value. If it is invalid, the value factory
     * will need to react accordingly and back out this change.
     */
    private BooleanProperty editable;
    public final void setEditable(boolean value) {
        editableProperty().set(value);
    }
    public final boolean isEditable() {
        return editable == null ? true : editable.get();
    }
    public final BooleanProperty editableProperty() {
        if (editable == null) {
            editable = new SimpleBooleanProperty(this, "editable", false);
        }
        return editable;
    }


    // --- editor
    /**
     * The editor used by the Spinner control.
     */
    public final ReadOnlyObjectProperty<TextField> editorProperty() {
        if (editor == null) {
            editor = new ReadOnlyObjectWrapper<TextField>(this, "editor");
            textField = new ComboBoxListViewSkin.FakeFocusTextField();
            editor.set(textField);
        }
        return editor.getReadOnlyProperty();
    }
    private TextField textField;
    private ReadOnlyObjectWrapper<TextField> editor;
    public final TextField getEditor() {
        return editorProperty().get();
    }



    /***************************************************************************
     *                                                                         *
     * Implementation                                                          *
     *                                                                         *
     **************************************************************************/

    /*
     * Update the TextField based on the current value
     */
    private void setText(T value) {
        String text = null;

        SpinnerValueFactory<T> valueFactory = getValueFactory();
        if (valueFactory != null) {
            StringConverter<T> converter = valueFactory.getConverter();
            if (converter != null) {
                text = converter.toString(value);
            }
        }

        notifyAccessibleAttributeChanged(AccessibleAttribute.VALUE_STRING);
        if (text == null) {
            if (value == null) {
                getEditor().clear();
                return;
            } else {
                text = value.toString();
            }
        }

        getEditor().setText(text);
    }

    /*
     * Convenience method to support wrapping values around their min / max
     * constraints. Used by the SpinnerValueFactory implementations when
     * the Spinner wrapAround property is true.
     */
    static int wrapValue(int value, int min, int max) {
        if (max == 0) {
            throw new RuntimeException();
        }

        int r = value % max;
        if (r > min && max < min) {
            r = r + max - min;
        } else if (r < min && max > min) {
            r = r + max - min;
        }
        return r;
    }

    /*
     * Convenience method to support wrapping values around their min / max
     * constraints. Used by the SpinnerValueFactory implementations when
     * the Spinner wrapAround property is true.
     */
    static BigDecimal wrapValue(BigDecimal value, BigDecimal min, BigDecimal max) {
        if (max.doubleValue() == 0) {
            throw new RuntimeException();
        }

        // note that this wrap method differs from the others where we take the
        // difference - in this approach we wrap to the min or max - it feels better
        // to go from 1 to 0, rather than 1 to 0.05 (where max is 1 and step is 0.05).
        if (value.compareTo(min) < 0) {
            return max;
        } else if (value.compareTo(max) > 0) {
            return min;
        }
        return value;
    }

    private void commitEditorText() {
        if (!isEditable()) return;
        String text = getEditor().getText();
        SpinnerValueFactory<T> valueFactory = getValueFactory();
        if (valueFactory != null) {
            StringConverter<T> converter = valueFactory.getConverter();
            if (converter != null) {
                T value = converter.fromString(text);
                valueFactory.setValue(value);
            }
        }
    }


    /***************************************************************************
     *                                                                         *
     * Accessibility handling                                                  *
     *                                                                         *
     **************************************************************************/

    @Override
    public Object queryAccessibleAttribute(AccessibleAttribute attribute, Object... parameters) {
        switch (attribute) {
            case VALUE_STRING: {
                T value = getValue();
                SpinnerValueFactory<T> factory = getValueFactory();
                if (factory != null) {
                    StringConverter<T> converter = factory.getConverter();
                    if (converter != null) {
                        return converter.toString(value);
                    }
                }
                return value != null ? value.toString() : "";
            }

            case TEXT: {
                String accText = getAccessibleText();
                return (accText != null) ? accText : "";
            }

            case EDITABLE:
                return isEditable();

            default: return super.queryAccessibleAttribute(attribute, parameters);
        }
    }

    @Override
    public void executeAccessibleAction(AccessibleAction action, Object... parameters) {
        switch (action) {
            case INCREMENT:
                increment();
                break;
            case DECREMENT:
                decrement();
                break;
            default: super.executeAccessibleAction(action);
        }
    }

}
