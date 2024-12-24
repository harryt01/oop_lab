package hust.soict.dsai.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class PainterController {
    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton penRadioButton;

    @FXML
    private RadioButton eraserRadioButton;

    @FXML
    private ToggleGroup toolToggleGroup;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        Circle newCircle;
        if (eraserRadioButton.isSelected()) {
            //eraser mode: draw with white (canvas color)
            newCircle = new Circle(event.getX(), event.getY(), 8, Color.WHITE);
        } else {
            //pen mode: draw with black
            newCircle = new Circle(event.getX(), event.getY(), 4, Color.BLACK);
        }
        drawingAreaPane.getChildren().add(newCircle);
    }
}
