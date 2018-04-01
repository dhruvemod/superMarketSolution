package com.apps.dcodertech.supermarketsolution;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.Calendar;


public class BillAddActivity extends AppCompatActivity {
    AutoCompleteTextView textView;
    EditText quant;
    Button buttonSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_add);
        textView=findViewById(R.id.autoCompleteTextView);
        quant=findViewById(R.id.quantEdit);

        buttonSubmit=findViewById(R.id.buttonSub);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String name=textView.getText().toString();
//                String q=quant.getText().toString();

                Date currentTime = Calendar.getInstance().getTime();
                Toast.makeText(BillAddActivity.this, String.valueOf(currentTime), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
