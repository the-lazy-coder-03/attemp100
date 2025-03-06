package com.example.attemp100;

public class InputtedProducts {
    private static int nextId = 1; // Static counter for auto-incrementing IDs

    private int id;
    private String name;
    private int quantity;
    private double price;
    private String created;
    private String updated;

    // Constructor for new products (without id parameter)
    public InputtedProducts(String name, int quantity, double price, String created, String updated) {
        this.id = nextId++; // Auto-generate the ID
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.created = created;
        this.updated = updated;
    }

    // Constructor for updating existing products (with id parameter)
    public InputtedProducts(int id, String name, int quantity, double price, String created, String updated) {
        this.id = id; // Use the existing ID
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.created = created;
        this.updated = updated;
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

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    @Override
    public String toString() {
        return name + " - $" + price + " (Qty: " + quantity + ")";
    }
}