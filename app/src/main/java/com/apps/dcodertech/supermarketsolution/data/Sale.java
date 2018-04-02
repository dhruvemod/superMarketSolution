package com.apps.dcodertech.supermarketsolution.data;

public class Sale {
    private final String productNametable;
    private final String priceTable2;
    private final String dateTable;
    private final String quantity;
    private final String totaltable;
    public Sale(String productNameTable,String priceTable2,String quantity,String totaltable,String dateTable){
        this.productNametable=productNameTable;
        this.priceTable2=priceTable2;
        this.quantity=quantity;
        this.dateTable=dateTable;
        this.totaltable=totaltable;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getDateTable() {
        return dateTable;
    }

    public String getPriceTable2() {
        return priceTable2;
    }

    public String getProductNametable() {
        return productNametable;
    }

    public String getTotaltable() {
        return totaltable;
    }
}

