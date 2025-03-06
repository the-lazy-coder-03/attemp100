package com.example.attemp100;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InputtedProducts {
    private static int nextId = 1; // Static counter for auto-incrementing IDs

    private int id;
    private String name;
    private int quantity;
    private double price;
    private LocalDateTime created;
    private LocalDateTime updated;

    // Constructor for new products (without id parameter)
    public InputtedProducts(String name, int quantity, double price) {
        this.id = nextId++; // Auto-generate the ID
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.created = LocalDateTime.now(); // Set the current date and time
        this.updated = LocalDateTime.now(); // Set the current date and time
    }

    // Constructor for updating existing products (with id parameter)
    public InputtedProducts(int id, String name, int quantity, double price, LocalDateTime created) {
        this.id = id; // Use the existing ID
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.created = created; // Keep the original created date
        this.updated = LocalDateTime.now(); // Update the updated date
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getCreatedDateTime() {
        return created; // Return the LocalDateTime object
    }

    public String getCreated() {
        return created.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getUpdated() {
        return updated.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public String toString() {
        return name + " - $" + price + " (Qty: " + quantity + ")";
    }
}