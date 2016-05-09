package com.example.mynewapp;


import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;

public class ProductListAdapter extends FirebaseListAdapter<ProdInfo>{
    private String prod_id;

    public ProductListAdapter(Query ref, Activity activity, int layout, String prod_id) {
        super(ref, ProdInfo.class, layout, activity);
        this.prod_id = prod_id;
    }

    @Override
    protected void populateView(View view, ProdInfo prodinfo) {
        // Map a Chat object to an entry in our listview
        String product_name = prodinfo.getProduct_name();
        TextView prodname = (TextView) view.findViewById(R.id.prodName);
        prodname.setText(product_name);
        ((TextView) view.findViewById(R.id.prodSpecs)).setText(prodinfo.getProduct_specs());
    }
}
