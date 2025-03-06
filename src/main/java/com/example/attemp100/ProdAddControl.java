package com.example.attemp100;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class ProdAddControl {

    @FXML
    private TextField AddName;

    @FXML
    private Button AddProdCancel;

    @FXML
    private TextField PriceAdd;

    @FXML
    private Button ProdAddBtn;

    @FXML
    private TextField QuantityAdd;

    // Callback to pass data back to the main controller
    private Consumer<InputtedProducts> onSaveCallback;

    // Store the selected product for updates
    private InputtedProducts selectedProduct;

    public void setOnSaveCallback(Consumer<InputtedProducts> callback) {
        this.onSaveCallback = callback;
    }

    public void setProductDetails(InputtedProducts product) {
        this.selectedProduct = product;
        // Populate the form with the selected product's details
        AddName.setText(product.getName());
        PriceAdd.setText(String.valueOf(product.getPrice()));
        QuantityAdd.setText(String.valueOf(product.getQuantity()));
    }

    @FXML
    void SwitchToMainGui(ActionEvent event) {
        // Close the "Add Product" window
        ((Stage) AddName.getScene().getWindow()).close();
    }

    @FXML
    void initialize() {
        assert AddName != null : "fx:id=\"AddName\" was not injected: check your FXML file 'ProdAdd.fxml'.";
        assert AddProdCancel != null : "fx:id=\"AddProdCancel\" was not injected: check your FXML file 'ProdAdd.fxml'.";
        assert PriceAdd != null : "fx:id=\"PriceAdd\" was not injected: check your FXML file 'ProdAdd.fxml'.";
        assert ProdAddBtn != null : "fx:id=\"ProdAddBtn\" was not injected: check your FXML file 'ProdAdd.fxml'.";
        assert QuantityAdd != null : "fx:id=\"QuantityAdd\" was not injected: check your FXML file 'ProdAdd.fxml'.";

        // Set action for the "Add Product" button
        ProdAddBtn.setOnAction(event -> saveProduct());
    }

    private void saveProduct() {
        try {
            // Get data from the form fields
            String name = AddName.getText();
            double price = Double.parseDouble(PriceAdd.getText());
            int quantity = Integer.parseInt(QuantityAdd.getText());

            // Create a new InputtedProducts object
            InputtedProducts product;
            if (selectedProduct == null) {
                // Add new product
                product = new InputtedProducts(
                        name,
                        quantity,
                        price,
                        "2023-10-01", // Placeholder for created date
                        "2023-10-01" // Placeholder for updated date
                );
            } else {
                // Update existing product
                product = new InputtedProducts(
                        selectedProduct.getId(), // Keep the same ID
                        name,
                        quantity,
                        price,
                        selectedProduct.getCreated(), // Keep the original created date
                        "2023-10-01" // Update the updated date
                );
            }

            // Pass the product back to the main controller
            if (onSaveCallback != null) {
                onSaveCallback.accept(product);
            }

            // Close the "Add Product" window
            ((Stage) AddName.getScene().getWindow()).close();
        } catch (NumberFormatException e) {
            System.err.println("Invalid input: Please enter valid numbers for price and quantity.");
        }
    }
}