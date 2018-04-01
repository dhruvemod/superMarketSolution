package com.apps.dcodertech.supermarketsolution.data;

import android.provider.BaseColumns;

public class InventoryContract {
    public InventoryContract() {

    }

    public static class StockEntry implements BaseColumns {
        public static final String TABLE_NAME = "stockInfo";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_Phone = "phone";


    }

    public static final String CREATE_TABLE = "CREATE TABLE " +
            StockEntry.TABLE_NAME + "(" +
            StockEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            StockEntry.COLUMN_NAME + " TEXT NOT NULL," +
            StockEntry.COLUMN_PRICE + " TEXT NOT NULL," +
            StockEntry.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0," +
            StockEntry.COLUMN_Phone + " TEXT NOT NULL" +");";
}
