package com.example.mynewapp;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.ArrayList;
import java.util.HashMap;


public class ProductPage extends Activity {

    ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
    ProductListAdapter mAdaptr;
    Firebase ref;
    TextView tv;
    ListView lv;
    String selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prodpagelayout);

        Firebase.setAndroidContext(this);

         lv=(ListView) findViewById(R.id.listViewProds);
        ref = new Firebase("https://androtestapp.firebaseio.com/electronics");

        Query queryRef = ref.orderByChild("product_price");


        ProdInfo pi = new ProdInfo();

        mAdaptr = new ProductListAdapter(ref, this, R.layout.productsmodule, pi.getProduct_name());
        lv.setAdapter(mAdaptr);
        mAdaptr.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                lv.setSelection(mAdaptr.getCount() - 1);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        // selected item
                        selected = ((TextView) view.findViewById(R.id.prodName)).getText().toString();
//
//                         Toast toast = Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT);
//                        toast.show();
                        String str;
                        str="MotoG";
                        if(selected==str)
                        {   System.out.println("motog");
                            Intent gotoMotog = new Intent(ProductPage.this,MotoG.class);
                            startActivity(gotoMotog);
                        }
                        else if(selected=="MotoX ")
                        {   System.out.println("motox");
                            Intent gotoMotoX = new Intent(ProductPage.this,MotoX.class);
                            startActivity(gotoMotoX);
                        }
                        else if(selected=="Lenovo G510 "){
                            System.out.println("lenovo");
                            Intent gotoLen = new Intent(ProductPage.this,LenCls.class);
                            startActivity(gotoLen);
                        }

                    }
                });
            }
        });


        //to get json form of data use below method.

        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                System.out.println(snapshot.getValue());
                HashMap<String, String> map_value = (HashMap<String, String>) snapshot.getValue();
                System.out.println(snapshot.getKey() + " specs is " + map_value.get("product_specs"));
                mylist.add(map_value);
                int numRows = map_value.size();


            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

//        returns data normally in simple format, not json

//        Query queryRef = ref.orderByChild("product_price");
//        queryRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
//                ProdInfo facts = snapshot.getValue(ProdInfo.class);
//                System.out.println(snapshot.getKey() + " was " + facts.getProduct_specs() + " meters tall");
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//            }



     }

}
