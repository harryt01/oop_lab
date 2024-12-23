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

package com.sun.javafx.scene.control.skin;

import com.sun.javafx.scene.control.behavior.ComboBoxListViewBehavior;

import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.WeakInvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.WeakListChangeListener;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.scene.AccessibleAttribute;
import javafx.scene.AccessibleRole;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class ComboBoxListViewSkin<T> extends ComboBoxPopupControl<T> {

    // By default we measure the width of all cells in the ListView. If this
    // is too burdensome, the developer may set a property in the ComboBox
    // properties map with this key to specify the number of rows to measure.
    // This may one day become a property on the ComboBox itself.
    private static final String COMBO_BOX_ROWS_TO_MEASURE_WIDTH_KEY = "comboBoxRowsToMeasureWidth";



    /***************************************************************************
     *                                                                         *
     * Private fields                                                          *
     *                                                                         *
     **************************************************************************/

    private final ComboBox<T> comboBox;
    private ObservableList<T> comboBoxItems;

    private ListCell<T> buttonCell;
    private Callback<ListView<T>, ListCell<T>> cellFactory;

    private final ListView<T> listView;
    private ObservableList<T> listViewItems;

    private boolean listSelectionLock = false;
    private boolean listViewSelectionDirty = false;


    /***************************************************************************
     *                                                                         *
     * Listeners                                                               *
     *                                                                         *
     **************************************************************************/

    private boolean itemCountDirty;
    private final ListChangeListener<T> listViewItemsListener = new ListChangeListener<T>() {
        @Override public void onChanged(ListChangeListener.Change<? extends T> c) {
            itemCountDirty = true;
            getSkinnable().requestLayout();
        }
    };

    private final InvalidationListener itemsObserver;

    private final WeakListChangeListener<T> weakListViewItemsListener =
            new WeakListChangeListener<T>(listViewItemsListener);


    /***************************************************************************
     *                                                                         *
     * Constructors                                                            *
     *                                                                         *
     **************************************************************************/

    public ComboBoxListViewSkin(final ComboBox<T> comboBox) {
        super(comboBox, new ComboBoxListViewBehavior<T>(comboBox));
        this.comboBox = comboBox;
        updateComboBoxItems();

        itemsObserver = observable -> {
            updateComboBoxItems();
            updateListViewItems();
        };
        this.comboBox.itemsProperty().addListener(new WeakInvalidationListener(itemsObserver));

        // listview for popup
        this.listView = createListView();

        // Fix for RT-21207. Additional code related to this bug is further below.
        this.listView.setManaged(false);
        getChildren().add(listView);
        // -- end of fix

        updateListViewItems();
        updateCellFactory();

        updateButtonCell();

        // Fix for RT-19431 (also tested via ComboBoxListViewSkinTest)
        updateValue();

        registerChangeListener(comboBox.itemsProperty(), "ITEMS");
        registerChangeListener(comboBox.promptTextProperty(), "PROMPT_TEXT");
        registerChangeListener(comboBox.cellFactoryProperty(), "CELL_FACTORY");
        registerChangeListener(comboBox.visibleRowCountProperty(), "VISIBLE_ROW_COUNT");
        registerChangeListener(comboBox.converterProperty(), "CONVERTER");
        registerChangeListener(comboBox.buttonCellProperty(), "BUTTON_CELL");
        registerChangeListener(comboBox.valueProperty(), "VALUE");
        registerChangeListener(comboBox.editableProperty(), "EDITABLE");

        // Refer to JDK-8095306
        if (comboBox.isShowing()) {
            show();
        }
    }



    /***************************************************************************
     *                                                                         *
     * Public API                                                              *
     *                                                                         *
     **************************************************************************/

    /** {@inheritDoc} */
    @Override protected void handleControlPropertyChanged(String p) {
        super.handleControlPropertyChanged(p);

        if ("ITEMS".equals(p)) {
            updateComboBoxItems();
            updateListViewItems();
        } else if ("PROMPT_TEXT".equals(p)) {
            updateDisplayNode();
        } else if ("CELL_FACTORY".equals(p)) {
            updateCellFactory();
        } else if ("VISIBLE_ROW_COUNT".equals(p)) {
            if (listView == null) return;
            listView.requestLayout();
        } else if ("CONVERTER".equals(p)) {
            updateListViewItems();
        } else if ("EDITOR".equals(p)) {
            getEditableInputNode();
        } else if ("BUTTON_CELL".equals(p)) {
            updateButtonCell();
            updateDisplayArea();
        } else if ("VALUE".equals(p)) {
            updateValue();
            comboBox.fireEvent(new ActionEvent());
        } else if ("EDITABLE".equals(p)) {
            updateEditable();
        }
    }

    @Override protected TextField getEditor() {
        // Return null if editable is false, even if the ComboBox has an editor set.
        // Use getSkinnable() here because this method is called from the super
        // constructor before comboBox is initialized.
        return getSkinnable().isEditable() ? ((ComboBox)getSkinnable()).getEditor() : null;
    }

    @Override protected StringConverter<T> getConverter() {
        return ((ComboBox)getSkinnable()).getConverter();
    }


    /** {@inheritDoc} */
    @Override public Node getDisplayNode() {
        Node displayNode;
        if (comboBox.isEditable()) {
            displayNode = getEditableInputNode();
        } else {
            displayNode = buttonCell;
        }

        updateDisplayNode();

        return displayNode;
    }

    public void updateComboBoxItems() {
        comboBoxItems = comboBox.getItems();
        comboBoxItems = comboBoxItems == null ? FXCollections.<T>emptyObservableList() : comboBoxItems;
    }

    public void updateListViewItems() {
        if (listViewItems != null) {
            listViewItems.removeListener(weakListViewItemsListener);
        }

        this.listViewItems = comboBoxItems;
        listView.setItems(listViewItems);

        if (listViewItems != null) {
            listViewItems.addListener(weakListViewItemsListener);
        }

        itemCountDirty = true;
        getSkinnable().requestLayout();
    }

    @Override public Node getPopupContent() {
        return listView;
    }

    @Override protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        reconfigurePopup();
        return 50;
    }

    @Override protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        double superPrefWidth = super.computePrefWidth(height, topInset, rightInset, bottomInset, leftInset);
        double listViewWidth = listView.prefWidth(height);
        double pw = Math.max(superPrefWidth, listViewWidth);

        reconfigurePopup();

        return pw;
    }

    @Override protected double computeMaxWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        reconfigurePopup();
        return super.computeMaxWidth(height, topInset, rightInset, bottomInset, leftInset);
    }

    @Override protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        reconfigurePopup();
        return super.computeMinHeight(width, topInset, rightInset, bottomInset, leftInset);
    }

    @Override protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        reconfigurePopup();
        return super.computePrefHeight(width, topInset, rightInset, bottomInset, leftInset);
    }

    @Override protected double computeMaxHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        reconfigurePopup();
        return super.computeMaxHeight(width, topInset, rightInset, bottomInset, leftInset);
    }

    @Override protected void layoutChildren(final double x, final double y,
            final double w, final double h) {
        if (listViewSelectionDirty) {
            try {
                listSelectionLock = true;
                T item = comboBox.getSelectionModel().getSelectedItem();
                listView.getSelectionModel().clearSelection();
                listView.getSelectionModel().select(item);
            } finally {
                listSelectionLock = false;
                listViewSelectionDirty = false;
            }
        }

        super.layoutChildren(x,y,w,h);
    }

    // Added to allow subclasses to prevent the popup from hiding when the
    // ListView is clicked on (e.g when the list cells have checkboxes).
    protected boolean isHideOnClickEnabled() {
        return true;
    }



    /***************************************************************************
     *                                                                         *
     * Private methods                                                         *
     *                                                                         *
     **************************************************************************/

    private void updateValue() {
        T newValue = comboBox.getValue();

        SelectionModel<T> listViewSM = listView.getSelectionModel();

        if (newValue == null) {
            listViewSM.clearSelection();
        } else {
            // RT-22386: We need to test to see if the value is in the comboBox
            // items list. If it isn't, then we should clear the listview
            // selection
            int indexOfNewValue = getIndexOfComboBoxValueInItemsList();
            if (indexOfNewValue == -1) {
                listSelectionLock = true;
                listViewSM.clearSelection();
                listSelectionLock = false;
            } else {
                int index = comboBox.getSelectionModel().getSelectedIndex();
                if (index >= 0 && index < comboBoxItems.size()) {
                    T itemsObj = comboBoxItems.get(index);
                    if (itemsObj != null && itemsObj.equals(newValue)) {
                        listViewSM.select(index);
                    } else {
                        listViewSM.select(newValue);
                    }
                } else {
                    // just select the first instance of newValue in the list
                    int listViewIndex = comboBoxItems.indexOf(newValue);
                    if (listViewIndex == -1) {
                        // RT-21336 Show the ComboBox value even though it doesn't
                        // exist in the ComboBox items list (part one of fix)
                        updateDisplayNode();
                    } else {
                        listViewSM.select(listViewIndex);
                    }
                }
            }
        }
    }


    @Override protected void updateDisplayNode() {
        if (getEditor() != null) {
            super.updateDisplayNode();
        } else {
            T value = comboBox.getValue();
            int index = getIndexOfComboBoxValueInItemsList();
            if (index > -1) {
                buttonCell.setItem(null);
                buttonCell.updateIndex(index);
            } else {
                // RT-21336 Show the ComboBox value even though it doesn't
                // exist in the ComboBox items list (part two of fix)
                buttonCell.updateIndex(-1);
                boolean empty = updateDisplayText(buttonCell, value, false);

                // Note that empty boolean collected above. This is used to resolve
                // RT-27834, where we were getting different styling based on whether
                // the cell was updated via the updateIndex method above, or just
                // by directly updating the text. We fake the pseudoclass state
                // for empty, filled, and selected here.
                buttonCell.pseudoClassStateChanged(PSEUDO_CLASS_EMPTY,    empty);
                buttonCell.pseudoClassStateChanged(PSEUDO_CLASS_FILLED,   !empty);
                buttonCell.pseudoClassStateChanged(PSEUDO_CLASS_SELECTED, true);
            }
        }
    }

    // return a boolean to indicate that the cell is empty (and therefore not filled)
    private boolean updateDisplayText(ListCell<T> cell, T item, boolean empty) {
        if (empty) {
            if (cell == null) return true;
            cell.setGraphic(null);
            cell.setText(null);
            return true;
        } else if (item instanceof Node) {
            Node currentNode = cell.getGraphic();
            Node newNode = (Node) item;
            if (currentNode == null || ! currentNode.equals(newNode)) {
                cell.setText(null);
                cell.setGraphic(newNode);
            }
            return newNode == null;
        } else {
            // run item through StringConverter if it isn't null
            StringConverter<T> c = comboBox.getConverter();
            String s = item == null ? comboBox.getPromptText() : (c == null ? item.toString() : c.toString(item));
            cell.setText(s);
            cell.setGraphic(null);
            return s == null || s.isEmpty();
        }
    }

    private int getIndexOfComboBoxValueInItemsList() {
        T value = comboBox.getValue();
        int index = comboBoxItems.indexOf(value);
        return index;
    }

    private void updateButtonCell() {
        buttonCell = comboBox.getButtonCell() != null ?
                comboBox.getButtonCell() : getDefaultCellFactory().call(listView);
        buttonCell.setMouseTransparent(true);
        buttonCell.updateListView(listView);

        // As long as the screen-reader is concerned this node is not a list item.
        // This matters because the screen-reader counts the number of list item
        // within combo and speaks it to the user.
        buttonCell.setAccessibleRole(AccessibleRole.NODE);
    }

    private void updateCellFactory() {
        Callback<ListView<T>, ListCell<T>> cf = comboBox.getCellFactory();
        cellFactory = cf != null ? cf : getDefaultCellFactory();
        listView.setCellFactory(cellFactory);
    }

    private Callback<ListView<T>, ListCell<T>> getDefaultCellFactory() {
        return new Callback<ListView<T>, ListCell<T>>() {
            @Override public ListCell<T> call(ListView<T> listView) {
                return new ListCell<T>() {
                    @Override public void updateItem(T item, boolean empty) {
                        super.updateItem(item, empty);
                        updateDisplayText(this, item, empty);
                    }
                };
            }
        };
    }

    private ListView<T> createListView() {
        final ListView<T> _listView = new ListView<T>() {

            {
                getProperties().put("selectFirstRowByDefault", false);
            }

            @Override protected double computeMinHeight(double width) {
                return 30;
            }

            @Override protected double computePrefWidth(double height) {
                double pw;
                if (getSkin() instanceof ListViewSkin) {
                    ListViewSkin<?> skin = (ListViewSkin<?>)getSkin();
                    if (itemCountDirty) {
                        skin.updateRowCount();
                        itemCountDirty = false;
                    }

                    int rowsToMeasure = -1;
                    if (comboBox.getProperties().containsKey(COMBO_BOX_ROWS_TO_MEASURE_WIDTH_KEY)) {
                        rowsToMeasure = (Integer) comboBox.getProperties().get(COMBO_BOX_ROWS_TO_MEASURE_WIDTH_KEY);
                    }

                    pw = Math.max(comboBox.getWidth(), skin.getMaxCellWidth(rowsToMeasure) + 30);
                } else {
                    pw = Math.max(100, comboBox.getWidth());
                }

                // need to check the ListView pref height in the case that the
                // placeholder node is showing
                if (getItems().isEmpty() && getPlaceholder() != null) {
                    pw = Math.max(super.computePrefWidth(height), pw);
                }

                return Math.max(50, pw);
            }

            @Override protected double computePrefHeight(double width) {
                return getListViewPrefHeight();
            }
        };

        _listView.setId("list-view");
        _listView.placeholderProperty().bind(comboBox.placeholderProperty());
        _listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        _listView.setFocusTraversable(false);

        _listView.getSelectionModel().selectedIndexProperty().addListener(o -> {
            if (listSelectionLock) return;
            int index = listView.getSelectionModel().getSelectedIndex();
            comboBox.getSelectionModel().select(index);
            updateDisplayNode();
            comboBox.notifyAccessibleAttributeChanged(AccessibleAttribute.TEXT);
        });

        comboBox.getSelectionModel().selectedItemProperty().addListener(o -> {
            listViewSelectionDirty = true;
        });

        _listView.addEventFilter(MouseEvent.MOUSE_RELEASED, t -> {
            // RT-18672: Without checking if the user is clicking in the
            // scrollbar area of the ListView, the comboBox will hide. Therefore,
            // we add the check below to prevent this from happening.
            EventTarget target = t.getTarget();
            if (target instanceof Parent) {
                List<String> s = ((Parent) target).getStyleClass();
                if (s.contains("thumb")
                        || s.contains("track")
                        || s.contains("decrement-arrow")
                        || s.contains("increment-arrow")) {
                    return;
                }
            }

            if (isHideOnClickEnabled()) {
                comboBox.hide();
            }
        });

        _listView.setOnKeyPressed(t -> {
            // TODO move to behavior, when (or if) this class becomes a SkinBase
            if (t.getCode() == KeyCode.ENTER ||
                    t.getCode() == KeyCode.SPACE ||
                    t.getCode() == KeyCode.ESCAPE) {
                comboBox.hide();
            }
        });

        return _listView;
    }

    private double getListViewPrefHeight() {
        double ph;
        if (listView.getSkin() instanceof VirtualContainerBase) {
            int maxRows = comboBox.getVisibleRowCount();
            VirtualContainerBase<?,?,?> skin = (VirtualContainerBase<?,?,?>)listView.getSkin();
            ph = skin.getVirtualFlowPreferredHeight(maxRows);
        } else {
            double ch = comboBoxItems.size() * 25;
            ph = Math.min(ch, 200);
        }

        return ph;
    }



    /**************************************************************************
     *
     * API for testing
     *
     *************************************************************************/

    public ListView<T> getListView() {
        return listView;
    }




    /***************************************************************************
     *                                                                         *
     * Stylesheet Handling                                                     *
     *                                                                         *
     **************************************************************************/

    // These three pseudo class states are duplicated from Cell
    private static final PseudoClass PSEUDO_CLASS_SELECTED =
            PseudoClass.getPseudoClass("selected");
    private static final PseudoClass PSEUDO_CLASS_EMPTY =
            PseudoClass.getPseudoClass("empty");
    private static final PseudoClass PSEUDO_CLASS_FILLED =
            PseudoClass.getPseudoClass("filled");



    @Override
    public Object queryAccessibleAttribute(AccessibleAttribute attribute, Object... parameters) {
        switch (attribute) {
            case FOCUS_ITEM: {
                if (comboBox.isShowing()) {
                    /* On Mac, for some reason, changing the selection on the list is not
                     * reported by VoiceOver the first time it shows.
                     * Note that this fix returns a child of the PopupWindow back to the main
                     * Stage, which doesn't seem to cause problems.
                     */
                    return listView.queryAccessibleAttribute(attribute, parameters);
                }
                return null;
            }
            case TEXT: {
                String accText = comboBox.getAccessibleText();
                if (accText != null && !accText.isEmpty()) return accText;
                String title = comboBox.isEditable() ? getEditor().getText() : buttonCell.getText();
                if (title == null || title.isEmpty()) {
                    title = comboBox.getPromptText();
                }
                return title;
            }
            case SELECTION_START: return getEditor().getSelection().getStart();
            case SELECTION_END: return getEditor().getSelection().getEnd();
            default: return super.queryAccessibleAttribute(attribute, parameters);
        }
    }
}

