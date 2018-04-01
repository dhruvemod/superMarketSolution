package com.apps.dcodertech.supermarketsolution.data;
import android.provider.BaseColumns;

public class SaleContract {
public SaleContract(){}
public static class SaleEntry implements BaseColumns{
    public static final String TABLE_NAME = "saleInfo";
    public static final String _ID = BaseColumns._ID;
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_TOTAL = "total";
    public static final String COLUMN_DATE = "date";

}
    public static final String CREATE_TABLE = "CREATE TABLE " +
            SaleEntry.TABLE_NAME + "(" +
            SaleEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            SaleEntry.COLUMN_NAME + " TEXT NOT NULL," +
            SaleEntry.COLUMN_PRICE + " TEXT NOT NULL," +
            SaleEntry.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0," +
            SaleEntry.COLUMN_TOTAL + " TEXT NOT NULL,"+
            SaleEntry.COLUMN_DATE + " TEXT NOT NULL"+");";

}
