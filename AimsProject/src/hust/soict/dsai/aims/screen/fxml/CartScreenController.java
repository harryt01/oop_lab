package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;

public class CartScreenController {

    private Cart cart;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    public CartScreenController(Cart cart) {
        this.cart = cart;
    }

    @FXML
    private void initialize() {
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));

        tblMedia.setItems(this.cart.getItemsOrdered());
        
        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Media>() {
            @Override
            public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                if (newValue != null) {
                    updateButtonBar(newValue);
                }
            }
        });
    }
    
    void updateButtonBar(Media media) {
        btnRemove.setVisible(true);
        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        } else {
            btnPlay.setVisible(false);
        }
    }
    
    @FXML
    void btnRemovePressed(ActionEvent event) {
    	Media media = tblMedia.getSelectionModel().getSelectedItem();
    	cart.removeMedia(media);
    }
    
    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        double totalCost = 0;
        
        for (Media media : cart.getItemsOrdered()) {
            totalCost += media.getCost(); 
        }
        
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Order placed successfully.");
    	alert.setHeaderText("Total: $" + String.format("%.2f", totalCost));
    	alert.setContentText("Thank you for your order.");
    	
    	alert.showAndWait();
    	
    	cart.clear();
    }
    
    public void updateTotalCost() {
        double totalCost = 0;
        
        //total cost from items in the cart
        for (Media media : cart.getItemsOrdered()) {
            totalCost += media.getCost(); 
        }

        //update the label with the new cost
        lblTotalCost.setText("$" + String.format("%.2f", totalCost));
    }
    
    @FXML
    void btnPlayPressed(ActionEvent event) {

    }
}
