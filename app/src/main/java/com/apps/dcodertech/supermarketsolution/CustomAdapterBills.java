package com.apps.dcodertech.supermarketsolution;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.apps.dcodertech.supermarketsolution.data.SaleContract;
import com.apps.dcodertech.supermarketsolution.data.inventoryDB;

public class CustomAdapterBills extends CursorAdapter {
    inventoryDB inventoryDB;

    private BillActivity activity;
    public CustomAdapterBills(BillActivity activity, Cursor cursor){
        super(activity.getContext(), cursor,0);
        this.activity=activity;
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.bills_resource, viewGroup, false);
    }
    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        TextView nameTextView = view.findViewById(R.id.itemNameLog);
        TextView quantityTextView = view.findViewById(R.id.quantityLog);
        TextView dateTextView = view.findViewById(R.id.dateBillLog);
        TextView totalTextView = view.findViewById(R.id.totalLog);
        TextView priceTextView = view.findViewById(R.id.itemPriceLog);
        String name = cursor.getString(cursor.getColumnIndex(SaleContract.SaleEntry.COLUMN_NAME));
        String quantity = cursor.getString(cursor.getColumnIndex(SaleContract.SaleEntry.COLUMN_QUANTITY));
        String date = cursor.getString(cursor.getColumnIndex(SaleContract.SaleEntry.COLUMN_DATE));
        String d=date.substring(0,10);
        String da=date.substring(29,34);
        String total = cursor.getString(cursor.getColumnIndex(SaleContract.SaleEntry.COLUMN_TOTAL));
        String price = cursor.getString(cursor.getColumnIndex(SaleContract.SaleEntry.COLUMN_PRICE));
        nameTextView.setText(name);
        priceTextView.setText(price+" Rs");
        quantityTextView.setText(quantity);
        dateTextView.setText(d+", ");
        totalTextView.setText(total+" Rs");

    }

}
