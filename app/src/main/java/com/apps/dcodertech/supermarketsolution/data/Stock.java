package com.apps.dcodertech.supermarketsolution.data;

public class Stock {
    private final String productName;
    private final String price;
    private final int quantity;
    private String phone;

    public Stock(String productName, String price, int quantity, String phone) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.phone = phone;


    }



    public String getPhone() {
        return phone;
    }

    public String getProductName() {
        return productName;
    }

    public String getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "StockItem{" +
                "productName='" + productName + '\'' +
                ", price='" + price + '\'' +
                ", quantity=" + quantity +
                '}';
    }

}
