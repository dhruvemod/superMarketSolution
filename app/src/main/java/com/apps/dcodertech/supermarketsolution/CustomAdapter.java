package com.apps.dcodertech.supermarketsolution;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.dcodertech.supermarketsolution.data.InventoryContract;


/**
 * Created by dhruve on 12/11/2017.
 */
//Cursor adapter class
public class CustomAdapter extends CursorAdapter {
    private final inventoryActivity activity;

    public CustomAdapter(inventoryActivity context, Cursor c) {
        super(context.getContext(), c,0);
        this.activity = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        TextView nameTextView = view.findViewById(R.id.product_name);
        TextView quantityTextView = view.findViewById(R.id.quantity);
        TextView priceTextView = view.findViewById(R.id.price);
        Button sale = view.findViewById(R.id.sale);
        String name = cursor.getString(cursor.getColumnIndex(InventoryContract.StockEntry.COLUMN_NAME));
        final int quantity = cursor.getInt(cursor.getColumnIndex(InventoryContract.StockEntry.COLUMN_QUANTITY));
        String price = cursor.getString(cursor.getColumnIndex(InventoryContract.StockEntry.COLUMN_PRICE));
        nameTextView.setText(name);
        quantityTextView.setText(String.valueOf(quantity));
        priceTextView.setText(price);

        final long id = cursor.getLong(cursor.getColumnIndex(InventoryContract.StockEntry._ID));
        sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.clickOnSale(id, quantity);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.clickOnViewItem(id);
            }
        });

    }
}
