package me.nihalismail.bitcoincalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateProfit(View view)
    {
        //Declaration Stuff

        EditText sellingPriceInput = (EditText) findViewById(R.id.sellingPriceInput);
        EditText buyingPriceInput = (EditText) findViewById(R.id.buyingPriceInput);
        EditText quantityInput = (EditText) findViewById(R.id.quantityInput);
        TextView profitText = (TextView) findViewById(R.id.profitInput);


        float sellingPrice,buyingPrice,quanity,amountOfBitcoin,profit;

        //Getting Input from EditText

        sellingPrice=Float.parseFloat(sellingPriceInput.getText().toString());
        buyingPrice=Float.parseFloat(buyingPriceInput.getText().toString());
        quanity=Float.parseFloat(quantityInput.getText().toString());

        //Logic Stuff
        amountOfBitcoin=quanity/buyingPrice;
        profit=(sellingPrice*amountOfBitcoin)-(buyingPrice*amountOfBitcoin);

        //Display part
        profitText.setText(String.valueOf(profit));
    }
}