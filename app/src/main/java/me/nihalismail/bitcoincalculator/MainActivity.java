package me.nihalismail.bitcoincalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText quantityInput = (EditText) findViewById(R.id.quantityInput);
        quantityInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        calculateProfit();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void calculateProfit()
    {
        //Declaration Stuff

        EditText sellingPriceInput = (EditText) findViewById(R.id.sellingPriceInput);
        EditText buyingPriceInput = (EditText) findViewById(R.id.buyingPriceInput);
        EditText quantityInput = (EditText) findViewById(R.id.quantityInput);
        TextView profitText = (TextView) findViewById(R.id.profitInput);


        float sellingPrice,buyingPrice,quantity,amountOfBitcoin,profit;

        //Getting Input from EditText
        if(!TextUtils.isEmpty(sellingPriceInput.getText().toString())) {
            sellingPrice = Float.parseFloat(sellingPriceInput.getText().toString());
        }
        else
        {
            sellingPrice=0;
        }

        if(!TextUtils.isEmpty(buyingPriceInput.getText().toString())) {
            buyingPrice = Float.parseFloat(buyingPriceInput.getText().toString());
        }
        else
        {
            buyingPrice=0;
        }

        if(!TextUtils.isEmpty(quantityInput.getText().toString())) {
            quantity = Float.parseFloat(quantityInput.getText().toString());
        }
        else
        {
            quantity=0;
        }

        //Logic Stuff
        if(buyingPrice!=0) {
            amountOfBitcoin = quantity / buyingPrice;
            profit = (sellingPrice * amountOfBitcoin) - (buyingPrice * amountOfBitcoin);

            //Display part
            profitText.setText(String.valueOf(profit));
        }
    }
}