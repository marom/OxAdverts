package com.maro.oxadverts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by maro on 12/04/2015.
 */
public class AdvertDetailsActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advert_details);
        renderAdvertDetails();
    }

    private void renderAdvertDetails() {
        TextView dateAdded = (TextView) findViewById(R.id.dateAdded);
        TextView advertHeader = (TextView) findViewById(R.id.advertHeader);
        TextView price = (TextView) findViewById(R.id.price);
        TextView sellerName = (TextView) findViewById(R.id.sellerName);
        TextView sellerPhone = (TextView) findViewById(R.id.sellerPhone);
        TextView sellerCity = (TextView) findViewById(R.id.sellerCity);
        TextView sellerEmail = (TextView) findViewById(R.id.sellerEmail);
        TextView shortContent = (TextView) findViewById(R.id.shortContent);
        TextView fullContent = (TextView) findViewById(R.id.fullContent);
        TextView link = (TextView) findViewById(R.id.link);

        Intent intent = getIntent();
        // getting attached intent data
        dateAdded.setText(intent.getStringExtra("dateAdded"));
        advertHeader.setText(intent.getStringExtra("advertHeader"));
        price.setText(intent.getStringExtra("price"));
        sellerName.setText(intent.getStringExtra("sellerName"));
        sellerPhone.setText(intent.getStringExtra("sellerPhone"));
        sellerCity.setText(intent.getStringExtra("sellerCity"));
        sellerEmail.setText(intent.getStringExtra("sellerEmail"));
        shortContent.setText(intent.getStringExtra("shortContent"));
        fullContent.setText(intent.getStringExtra("fullContent"));
        link.setText(intent.getStringExtra("link"));
    }
}
