package com.apps.dcodertech.supermarketsolution.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SalesDB extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "sales.db";
    public final static int DATABASE_VERSION = 1;
    public SalesDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SaleContract.CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void insert(Sale item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SaleContract.SaleEntry.COLUMN_NAME, item.getProductNametable());
        values.put(SaleContract.SaleEntry.COLUMN_PRICE, item.getPriceTable2());
        values.put(SaleContract.SaleEntry.COLUMN_QUANTITY,item.getQuantity());
        values.put(SaleContract.SaleEntry.COLUMN_TOTAL, item.getTotaltable());
        values.put(SaleContract.SaleEntry.COLUMN_DATE,item.getDateTable());


        long id = db.insert(InventoryContract.StockEntry.TABLE_NAME, null, values);
    }
    public Cursor readStockInfo() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                SaleContract.SaleEntry._ID,
                SaleContract.SaleEntry.COLUMN_NAME,
                SaleContract.SaleEntry.COLUMN_PRICE,
                SaleContract.SaleEntry.COLUMN_QUANTITY,
                SaleContract.SaleEntry.COLUMN_TOTAL,
                SaleContract.SaleEntry.COLUMN_DATE

        };
        Cursor cursor = db.query(
                SaleContract.SaleEntry.TABLE_NAME,
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
                SaleContract.SaleEntry._ID,
                SaleContract.SaleEntry.COLUMN_NAME,
                SaleContract.SaleEntry.COLUMN_PRICE,
                SaleContract.SaleEntry.COLUMN_QUANTITY,
                SaleContract.SaleEntry.COLUMN_TOTAL,
                SaleContract.SaleEntry.COLUMN_DATE


        };
        String selection = SaleContract.SaleEntry._ID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(itemId)};

        Cursor cursor = db.query(
                SaleContract.SaleEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        return cursor;
    }
}
