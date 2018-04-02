package com.apps.dcodertech.supermarketsolution;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.apps.dcodertech.supermarketsolution.data.SalesDB;

public class BillActivity extends Fragment {
    CustomAdapterBills customAdapterBills;
    SalesDB salesDB;
    Cursor cursor;
    int lastItem = 0;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_tab_fragment, container, false);
        final FloatingActionButton fab = rootView.findViewById(R.id.fabForBill);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),BillAddActivity.class);
                startActivity(intent);
            }
        });

        salesDB=new SalesDB(getContext());
        listView = rootView.findViewById(R.id.list_viewForBills);
        cursor=salesDB.readStockInfo();
        customAdapterBills=new CustomAdapterBills(this,cursor);
        listView.setAdapter(customAdapterBills);
        return rootView;

    }

    @Override
    public void onResume() {
        super.onResume();
        Cursor cursor=salesDB.readStockInfo();
        customAdapterBills=new CustomAdapterBills(this,cursor);
        listView.setAdapter(customAdapterBills);
    }
}
