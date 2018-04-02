package com.apps.dcodertech.supermarketsolution;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apps.dcodertech.supermarketsolution.data.InventoryContract;
import com.apps.dcodertech.supermarketsolution.data.Sale;
import com.apps.dcodertech.supermarketsolution.data.SalesDB;
import com.apps.dcodertech.supermarketsolution.data.Stock;

import java.util.Date;
import java.util.Calendar;


public class BillAddActivity extends AppCompatActivity {
    AutoCompleteTextView textView;
    EditText quant;
    Button buttonSubmit;
    private SalesDB salesDB;
    long currentItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_add);
        textView=findViewById(R.id.autoCompleteTextView);
        quant=findViewById(R.id.quantEdit);
        salesDB=new SalesDB(this);
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
                    Sale sale = new Sale(name, "210", q, String.valueOf(124), String.valueOf(currentTime));
                    salesDB.insert(sale);
                    Toast.makeText(BillAddActivity.this, "Sale made!", Toast.LENGTH_SHORT).show();
                }
            finish();
            }
        });

    }

}
