package com.apps.dcodertech.supermarketsolution;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.apps.dcodertech.supermarketsolution.data.inventoryDB;

public class inventoryActivity extends android.support.v4.app.Fragment {
    inventoryDB dbHelper;
    CustomAdapter adapter;
    int lastItem = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_inventory,container,false);
        dbHelper = new inventoryDB(getContext());
        final FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), detailsActivity.class);
                startActivity(intent);
            }
        });

        final ListView listView = rootView.findViewById(R.id.list_view);
        View emptyView = rootView.findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);

        Cursor cursor = dbHelper.readStockInfo();

        adapter = new CustomAdapter(inventoryActivity.this, cursor);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == 0) return;
                final int currentFirstVisibleItem = view.getFirstVisiblePosition();
                if (currentFirstVisibleItem > lastItem) {
                    fab.show();
                } else if (currentFirstVisibleItem < lastItem) {
                    fab.hide();
                }
                lastItem = currentFirstVisibleItem;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.swapCursor(dbHelper.readStockInfo());
    }

    public void clickOnViewItem(long id) {
        Intent intent = new Intent(getContext(), detailsActivity.class);
        intent.putExtra("itemId", id);
        startActivity(intent);
    }

    public void clickOnSale(long id, int quantity) {
        dbHelper.sellItem(id, quantity);
        adapter.swapCursor(dbHelper.readStockInfo());
    }
}
