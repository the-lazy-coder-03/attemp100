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

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ProdAdd;

    @FXML
    private Button ProdDelete;

    @FXML
    private Button ProdUpdate;

    @FXML
    private TableView<com.example.attemp100.InputtedProducts> productTable;

    @FXML
    private TableColumn<com.example.attemp100.InputtedProducts, Integer> idColumn;

    @FXML
    private TableColumn<com.example.attemp100.InputtedProducts, String> nameColumn;

    @FXML
    private TableColumn<com.example.attemp100.InputtedProducts, Integer> quantityColumn;

    @FXML
    private TableColumn<com.example.attemp100.InputtedProducts, Double> priceColumn;

    @FXML
    private TableColumn<com.example.attemp100.InputtedProducts, String> createdColumn;

    @FXML
    private TableColumn<com.example.attemp100.InputtedProducts, String> updatedColumn;

    // ObservableList to store products
    private ObservableList<com.example.attemp100.InputtedProducts> productList = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        assert ProdAdd != null : "fx:id=\"ProdAdd\" was not injected: check your FXML file 'yuurski.fxml'.";
        assert ProdDelete != null : "fx:id=\"ProdDelete\" was not injected: check your FXML file 'yuurski.fxml'.";
        assert ProdUpdate != null : "fx:id=\"ProdUpdate\" was not injected: check your FXML file 'yuurski.fxml'.";
        assert productTable != null : "fx:id=\"productTable\" was not injected: check your FXML file 'yuurski.fxml'.";

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
}