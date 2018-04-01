package com.apps.dcodertech.supermarketsolution.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class inventoryDB extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "inventory.db";
    public final static int DATABASE_VERSION = 1;

    public inventoryDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(InventoryContract.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insert(Stock item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InventoryContract.StockEntry.COLUMN_NAME, item.getProductName());
        values.put(InventoryContract.StockEntry.COLUMN_PRICE, item.getPrice());
        values.put(InventoryContract.StockEntry.COLUMN_QUANTITY, item.getQuantity());
        values.put(InventoryContract.StockEntry.COLUMN_Phone, item.getPhone());


        long id = db.insert(InventoryContract.StockEntry.TABLE_NAME, null, values);
    }

    public Cursor readStockInfo() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                InventoryContract.StockEntry._ID,
                InventoryContract.StockEntry.COLUMN_NAME,
                InventoryContract.StockEntry.COLUMN_PRICE,
                InventoryContract.StockEntry.COLUMN_QUANTITY,
                InventoryContract.StockEntry.COLUMN_Phone,

        };
        Cursor cursor = db.query(
                InventoryContract.StockEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }

    public Cursor readItemInfo(long itemId) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                InventoryContract.StockEntry._ID,
                InventoryContract.StockEntry.COLUMN_NAME,
                InventoryContract.StockEntry.COLUMN_PRICE,
                InventoryContract.StockEntry.COLUMN_QUANTITY,
                InventoryContract.StockEntry.COLUMN_Phone,

        };
        String selection = InventoryContract.StockEntry._ID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(itemId)};

        Cursor cursor = db.query(
                InventoryContract.StockEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        return cursor;
    }

    public void sellItem(long itemId, int CurrentQuantity) {
        SQLiteDatabase db = getWritableDatabase();
        //logic for quantity to keep always greater or equal to 0
        int quantityCount = 0;
        if (CurrentQuantity > 0) {
            quantityCount = CurrentQuantity - 1;
        }
        ContentValues values = new ContentValues();
        values.put(InventoryContract.StockEntry.COLUMN_QUANTITY, quantityCount);
        String selection = InventoryContract.StockEntry._ID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(itemId)};
        db.update(InventoryContract.StockEntry.TABLE_NAME,
                values, selection, selectionArgs);
    }

    public void updateItem(long currentItemId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InventoryContract.StockEntry.COLUMN_QUANTITY, quantity);
        String selection = InventoryContract
                .StockEntry._ID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(currentItemId)};
        db.update(InventoryContract.StockEntry.TABLE_NAME,
                values, selection, selectionArgs);
    }
}
