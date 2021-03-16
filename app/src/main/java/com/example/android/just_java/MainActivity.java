package com.example.android.just_java;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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
        displayMessage(createOrderSummary());
        //displayPrice(orders * 5);
    }

    public void increment(View view){
        orders++;
        displayQuantity(orders);
    }

    public void decrement(View view){
        orders--;
        if (orders <= 0){
            orders = 0;
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
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }

    private int calculatePrice(int quantity) {
        int price = quantity * 5;
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        if (whippedCream.isChecked()){
            price += quantity * 1;
        }
        if (chocolate.isChecked()){
            price += quantity * 2;
        }
        return price;
    }

    private String createOrderSummary(){
        EditText nameBox = (EditText) findViewById(R.id.name_edit_text);
        name = nameBox.getText().toString();
        String priceMessage = "Name: " + name;
        priceMessage += "\nQuantity: " + orders;
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        if (whippedCream.isChecked()){
            priceMessage += "\nAdd Whipped Cream";
        }
        if (chocolate.isChecked()){
            priceMessage += "\nAdd Chocolate";
        }
        priceMessage += "\nTotal: $" + calculatePrice(orders);
        priceMessage += "\nThank You!";

        return priceMessage;
    }
}