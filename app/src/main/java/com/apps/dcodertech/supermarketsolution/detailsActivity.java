package com.apps.dcodertech.supermarketsolution;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.apps.dcodertech.supermarketsolution.data.InventoryContract;
import com.apps.dcodertech.supermarketsolution.data.Stock;
import com.apps.dcodertech.supermarketsolution.data.inventoryDB;

public class detailsActivity extends AppCompatActivity {
    private inventoryDB dbHelper;
    EditText nameEditText;
    EditText price;
    EditText phone;
    EditText quantityEdit;

    long currentItemId;
    ImageButton decreaseQuantity;
    ImageButton increaseQuantity;
    Boolean ItemInfoChange = false;
    Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        nameEditText = findViewById(R.id.product_name_edit);
        price = findViewById(R.id.price_edit);
        quantityEdit = findViewById(R.id.quantity_edit);
        phone = findViewById(R.id.dealer);
        decreaseQuantity = findViewById(R.id.decrease_quantity);
        increaseQuantity = findViewById(R.id.increase_quantity);

        dbHelper = new inventoryDB(this);
        currentItemId = getIntent().getLongExtra("itemId", 0);
        if (currentItemId == 0) {

        } else {
            valuesEdit(currentItemId);
        }

        decreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubOne();
                ItemInfoChange = true;
            }
        });

        increaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sumOne();
                ItemInfoChange = true;
            }
        });


    }
    @Override
    public void onBackPressed() {
        finish();
    }

    private void SubOne() {
        String currentValString = quantityEdit.getText().toString();
        int currentVal;
        if (currentValString.isEmpty()) {
            return;
        } else if (currentValString.equals("0")) {
            return;
        } else {
            currentVal = Integer.parseInt(currentValString);
            quantityEdit.setText(String.valueOf(currentVal - 1));
        }
    }

    private void sumOne() {
        String currentValString = quantityEdit.getText().toString();
        int currentVal;
        if (currentValString.isEmpty()) {
            currentVal = 0;
        } else {
            currentVal = Integer.parseInt(currentValString);
        }
        quantityEdit.setText(String.valueOf(currentVal + 1));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        //checking if there exists any item to delete
        if (currentItemId == 0) {
            MenuItem deleteOneItemMenuItem = menu.findItem(R.id.action_delete_item);
            MenuItem deleteAllMenuItem = menu.findItem(R.id.action_delete_all_data);
            deleteOneItemMenuItem.setVisible(false);
            deleteAllMenuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                if (!addToDB()) {
                    return true;
                }
                finish();
                return true;
            case R.id.order:
                order();
                return true;
            case R.id.action_delete_item:
                // delete one item
                showDeleteConfirmationDialog(currentItemId);
                return true;
            case R.id.action_delete_all_data:
                //delete all data
                showDeleteConfirmationDialog(0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean addToDB() {
        //Checking all the values for null
        boolean check = true;
        if (!checkForVal(nameEditText, "name")) {
            check = false;
        }
        if (!checkForVal(price, "price")) {
            check = false;
        }

        if (!checkForVal(quantityEdit, "quantity")) {
            check = false;
        }
        if (!checkForVal(phone, "supplier's phone")) {
            check = false;
        }
        if (!check) {
            return false;
        }

        if (currentItemId == 0) {
            Stock item = new Stock(
                    nameEditText.getText().toString().trim(),
                    price.getText().toString().trim(),
                    Integer.parseInt(quantityEdit.getText().toString().trim()), phone.getText().toString().trim());

            dbHelper.insert(item);
        } else {
            int quantity = Integer.parseInt(quantityEdit.getText().toString().trim());
            dbHelper.updateItem(currentItemId, quantity);
        }
        return true;
    }

    //Checking for null entries
    private boolean checkForVal(EditText text, String description) {
        if (TextUtils.isEmpty(text.getText())) {
            text.setError("Missing product " + description);
            return false;
        } else {
            text.setError(null);
            return true;
        }
    }

    private void valuesEdit(long itemId) {
        Cursor cursor = dbHelper.readItemInfo(itemId);
        cursor.moveToFirst();
        nameEditText.setText(cursor.getString(cursor.getColumnIndex(InventoryContract.StockEntry.COLUMN_NAME)));
        price.setText(cursor.getString(cursor.getColumnIndex(InventoryContract.StockEntry.COLUMN_PRICE)));
        phone.setText(cursor.getString(cursor.getColumnIndex(InventoryContract.StockEntry.COLUMN_Phone)));
        quantityEdit.setText(cursor.getString(cursor.getColumnIndex(InventoryContract.StockEntry.COLUMN_QUANTITY)));
        nameEditText.setEnabled(false);
        phone.setEnabled(false);
        price.setEnabled(false);
    }


    private int deleteAllRowsFromTable() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        return database.delete(InventoryContract.StockEntry.TABLE_NAME, null, null);
    }

    private int deleteOneItemFromTable(long itemId) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String selection = InventoryContract.StockEntry._ID + "=?";
        String[] selectionArgs = {String.valueOf(itemId)};
        return database.delete(
                InventoryContract.StockEntry.TABLE_NAME, selection, selectionArgs);
    }

    //Confirming if user wants to delete the item
    private void showDeleteConfirmationDialog(final long itemId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (itemId == 0) {
                    deleteAllRowsFromTable();
                } else {
                    deleteOneItemFromTable(itemId);
                }
                finish();
            }
        });
        builder.setNegativeButton("cancel", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void order() {
        Toast.makeText(this, "opening the phone app", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone.getText().toString().trim()));
        startActivity(intent);
    }
}
