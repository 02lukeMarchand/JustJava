package com.example.android.just_java;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int orders = 0;
    String name = "Luke Marchand";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //displayMessage(createOrderSummary());
        //displayPrice(orders * 5);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        EditText nameBox = (EditText) findViewById(R.id.name_edit_text);
        name = nameBox.getText().toString();
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_subject, name));
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void increment(View view){
        orders++;
        if (orders > 100){
            Toast.makeText(this, "you cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            orders = 100;
        }
        displayQuantity(orders);
    }

    public void decrement(View view){
        orders--;
        if (orders < 1){
            Toast.makeText(this, "you cannot have less than 1 coffees", Toast.LENGTH_SHORT).show();
            orders = 1;
        }
        displayQuantity(orders);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        //TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        //priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private void displayMessage(String message) {
        //TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        //priceTextView.setText(message);
    }

    private String calculatePrice(int quantity) {
        int price = quantity * 5;
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        if (whippedCream.isChecked()){
            price += quantity * 1;
        }
        if (chocolate.isChecked()){
            price += quantity * 2;
        }
        return "$" + price;
    }

    private String createOrderSummary(){
        EditText nameBox = (EditText) findViewById(R.id.name_edit_text);
        name = nameBox.getText().toString();
        String priceMessage = getString(R.string.order_summary_name, name);
        priceMessage += "\n" + getString(R.string.order_summary_quantity, orders);
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        if (whippedCream.isChecked()){
            priceMessage += "\n" + getString(R.string.whipped_cream_summary);
        }
        if (chocolate.isChecked()){
            priceMessage += "\n" + getString(R.string.chocolate_summary);
        }
        priceMessage += "\n" + getString(R.string.order_summary_price, calculatePrice(orders));
        priceMessage += "\n" + getString(R.string.thank_you);

        return priceMessage;
    }
}