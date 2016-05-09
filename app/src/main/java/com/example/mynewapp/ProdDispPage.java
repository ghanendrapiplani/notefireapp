package com.example.mynewapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;

public class ProdDispPage extends Activity {
    TextView prod_price,prod_rev,product_name,prod_specifics;
    String prodnameIntent,prodidIntent;
    Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        prodnameIntent = getIntent().getExtras().getString("key_prod_name");
        prodidIntent=getIntent().getExtras().getString("prod_id_key");
        setContentView(R.layout.detailedproductpage);
        prod_price=(TextView) findViewById(R.id.tvDetailProdPrice);
        prod_rev=(TextView) findViewById(R.id.tvDetailProdReviews);
        prod_specifics=(TextView) findViewById(R.id.tvDetailProdSpecs);
        product_name=(TextView) findViewById(R.id.tvDetailProdName);

        ref = new Firebase("https://androtestapp.firebaseio.com/electronics/detailed_prod_info");

        product_name.setText(prodnameIntent);
        System.out.println("new app =====" + prodnameIntent);
        System.out.println("new app ====="+ prodidIntent);


    }
}
