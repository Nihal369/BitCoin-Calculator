package me.nihalismail.bitcoincalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Unocoin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unocoin);

        //Object declarations
        EditText quantityInput = (EditText) findViewById(R.id.quantityInput);
        EditText sellingPriceInput = (EditText) findViewById(R.id.sellingPriceInput);
        EditText buyingPriceInput = (EditText) findViewById(R.id.buyingPriceInput);
        CheckBox netBanking=(CheckBox) findViewById(R.id.netBanking);
        final RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        final int selectedPlatform=radioGroup.getCheckedRadioButtonId();


        //Listeners
        quantityInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateProfit(selectedPlatform);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        sellingPriceInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateProfit(selectedPlatform);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        buyingPriceInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateProfit(selectedPlatform);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        netBanking.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                calculateProfit(selectedPlatform);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                calculateProfit(radioGroup.getCheckedRadioButtonId());
            }
        });
    }

    public void calculateProfit(int selectedPlatform)
    {
        EditText sellingPriceInput = (EditText) findViewById(R.id.sellingPriceInput);
        EditText buyingPriceInput = (EditText) findViewById(R.id.buyingPriceInput);
        EditText quantityInput = (EditText) findViewById(R.id.quantityInput);
        TextView profitText = (TextView) findViewById(R.id.profitInput);
        TextView taxText = (TextView) findViewById(R.id.taxText);
        CheckBox netBanking = (CheckBox) findViewById(R.id.netBanking);


        float sellingPrice, buyingPrice, quantity, amountOfBitcoin, profit, tax;

        //Getting Input from EditText
        if (!TextUtils.isEmpty(sellingPriceInput.getText().toString())) {
            sellingPrice = Float.parseFloat(sellingPriceInput.getText().toString());
        } else {
            sellingPrice = 0;
        }

        if (!TextUtils.isEmpty(buyingPriceInput.getText().toString())) {
            buyingPrice = Float.parseFloat(buyingPriceInput.getText().toString());
        } else {
            buyingPrice = 0;
        }

        if (!TextUtils.isEmpty(quantityInput.getText().toString())) {
            quantity = Float.parseFloat(quantityInput.getText().toString());
        } else {
            quantity = 0;
        }


        //Logic Stuff
        if(selectedPlatform==R.id.unocoinRadio)
        {

            if (buyingPrice != 0) {
                amountOfBitcoin = quantity / buyingPrice;
                profit = (sellingPrice * amountOfBitcoin) - (buyingPrice * amountOfBitcoin);

                //Unocoin fee of 1% and GST on the 1% at 18%
                tax = (quantity * (1.0f / 100.0f));
                tax += (tax * (18.0f / 100.0f));

                //Selling fee
                tax *= 2;


                //Netbanking to buy coins instantly costs nearly 2% extra
                if (netBanking.isChecked()) {
                    tax += (quantity * (2.0f / 100.0f));
                    tax += (tax * (9.0f / 100.0f));
                }

                //Display final tax
                taxText.setText(String.valueOf(tax));

                //Actual profit received by the user
                profit -= tax;

                //Display profit
                profitText.setText(String.valueOf(profit));
            }
        }

        else
        {
            if (buyingPrice != 0) {
                amountOfBitcoin = quantity / buyingPrice;
                profit = (sellingPrice * amountOfBitcoin) - (buyingPrice * amountOfBitcoin);

                //Koinex fee of 0.25%
                tax = (quantity * (0.25f / 100.0f));

                //Koinex sell fee free till 2018 JAN 1
                //Selling fee
                //tax *= 2;


                //Netbanking to buy coins instantly costs 2% extra
                if (netBanking.isChecked()) {
                    tax += (quantity * (2 / 100.0f));
                }

                //Display final tax
                taxText.setText(String.valueOf(tax));

                //Actual profit received by the user
                profit -= tax;

                //Display profit
                profitText.setText(String.valueOf(profit));
            }
        }
    }
}