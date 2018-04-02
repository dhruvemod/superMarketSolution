package com.apps.dcodertech.supermarketsolution;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apps.dcodertech.supermarketsolution.data.InventoryContract;
import com.apps.dcodertech.supermarketsolution.data.inventoryDB;
import com.apps.dcodertech.supermarketsolution.data.Sale;
import com.apps.dcodertech.supermarketsolution.data.SaleContract;
import com.apps.dcodertech.supermarketsolution.data.SalesDB;
import com.apps.dcodertech.supermarketsolution.data.Stock;

import java.util.Date;
import java.util.Calendar;


public class BillAddActivity extends AppCompatActivity {
    AutoCompleteTextView textView;
    EditText quant;
    Button buttonSubmit;
    private SalesDB salesDB;
    private inventoryDB inv;
    long currentItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_add);
        textView=findViewById(R.id.autoCompleteTextView);
        quant=findViewById(R.id.quantEdit);
        salesDB=new SalesDB(this);
        inv=new inventoryDB(this);
        buttonSubmit=findViewById(R.id.buttonSub);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView.getText()==null || quant.getText()==null){
                    Toast.makeText(BillAddActivity.this, "Add First", Toast.LENGTH_SHORT).show();

                }
                else {
                    String name = textView.getText().toString();
                    String q = quant.getText().toString();
                    Date currentTime = Calendar.getInstance().getTime();
                    Cursor cursor=inv.readStockInfoCondition(name);
                    //Log.i("second",String.valueOf(cursor.getColumnName(2)));
                   // try{

                    if (cursor != null && cursor.moveToFirst());
                    do {

                        String price=cursor.getString(cursor.getColumnIndex(InventoryContract.StockEntry.COLUMN_PRICE));
                        int p=Integer.parseInt(price);
                        
                        int tot=Integer.parseInt(q)*p;
                        String totl=String.valueOf(tot);
                        Sale sale = new Sale(name, price, q, totl, String.valueOf(currentTime));
                        salesDB.insert(sale);
                    } while (cursor.moveToNext());

                    cursor.close();
                    Toast.makeText(BillAddActivity.this, "Sale made!", Toast.LENGTH_SHORT).show();
               // }
                //catch (Exception e){
                   // }
                }
            finish();
            }
        });

    }

}
