package com.example.attemp100;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class HelloController {

    @FXML
    private Button ProdAdd;

    @FXML
    private Button ProdUpdate;

    @FXML
    private Button ProdDelete;

    @FXML
    private TableView<InputtedProducts> productTable;

    @FXML
    private TableColumn<InputtedProducts, Integer> idColumn;

    @FXML
    private TableColumn<InputtedProducts, String> nameColumn;

    @FXML
    private TableColumn<InputtedProducts, Integer> quantityColumn;

    @FXML
    private TableColumn<InputtedProducts, Double> priceColumn;

    @FXML
    private TableColumn<InputtedProducts, String> createdColumn;

    @FXML
    private TableColumn<InputtedProducts, String> updatedColumn;

    // ObservableList to store products
    private ObservableList<InputtedProducts> productList = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        // Bind the table columns to the InputtedProducts properties
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        createdColumn.setCellValueFactory(new PropertyValueFactory<>("created"));
        updatedColumn.setCellValueFactory(new PropertyValueFactory<>("updated"));

        // Bind the table to the product list
        productTable.setItems(productList);

        // Set action for the "ProdAdd" button
        ProdAdd.setOnAction(event -> openAddProductWindow());

        // Set action for the "ProdUpdate" button
        ProdUpdate.setOnAction(event -> openUpdateProductWindow());
    }

    private void openAddProductWindow() {
        try {
            // Load the "Add Product" FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/attemp100/ProdAdd.fxml"));
            Parent addProductRoot = loader.load();

            // Get the controller for the "Add Product" window
            ProdAddControl prodAddControl = loader.getController();

            // Set the callback to handle the saved product
            prodAddControl.setOnSaveCallback(product -> {
                // Add the product to the list
                productList.add(product);
            });

            // Create a new scene and stage
            Scene addProductScene = new Scene(addProductRoot);
            Stage addProductStage = new Stage();
            addProductStage.setTitle("Add Product");
            addProductStage.setScene(addProductScene);

            // Set modality to block interaction with the main window
            addProductStage.initModality(Modality.APPLICATION_MODAL);

            // Show the "Add Product" window
            addProductStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openUpdateProductWindow() {
        // Get the selected product from the table
        InputtedProducts selectedProduct = productTable.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            System.err.println("No product selected. Please select a product to update.");
            return;
        }

        try {
            // Load the "Add Product" FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/attemp100/ProdAdd.fxml"));
            Parent addProductRoot = loader.load();

            // Get the controller for the "Add Product" window
            ProdAddControl prodAddControl = loader.getController();

            // Populate the form with the selected product's details
            prodAddControl.setProductDetails(selectedProduct);

            // Set the callback to handle the updated product
            prodAddControl.setOnSaveCallback(product -> {
                // Update the product in the list
                int index = productList.indexOf(selectedProduct);
                if (index != -1) {
                    productList.set(index, product);
                }
            });

            // Create a new scene and stage
            Scene addProductScene = new Scene(addProductRoot);
            Stage addProductStage = new Stage();
            addProductStage.setTitle("Update Product");
            addProductStage.setScene(addProductScene);

            // Set modality to block interaction with the main window
            addProductStage.initModality(Modality.APPLICATION_MODAL);

            // Show the "Update Product" window
            addProductStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}