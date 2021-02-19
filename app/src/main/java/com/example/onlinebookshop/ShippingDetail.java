package com.example.onlinebookshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ShippingDetail extends AppCompatActivity {

    Button BTNback;
    TextView TvAccount;
    TextView TvMAName;
    TextView TvMAEmail;
    TextView TvMAPhone;
    TextView TvMAaddress;



    String name1,email1,Address1,Phone1;


    FirebaseAuth FirebaseAuth;
    private DatabaseReference auth;
    private FirebaseUser user;
    private String userId;

    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_detail);
        init();
        mDatabaseRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users users = snapshot.getValue(Users.class);
                if (users != null) {
                    name1 = users.name;
                    email1 = users.Email;
                    Phone1 = users.Phone;
                    Address1 = users.Address;

                    TvMAName.setText("Name : " + name1);
                    TvMAEmail.setText("Email : " + email1);
                    TvMAPhone.setText("Phone Number : " + Phone1);
                    TvMAaddress.setText("Email Address : " + Address1);
                }}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

            });
        BTNback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(ShippingDetail.this,Home.class);
                startActivity(intent);
                ShippingDetail.this.finish();

            }
        });
    }

    public void init()
    {
        TvAccount= findViewById(R.id.TvAccount);
        TvMAName= findViewById(R.id.TvMAName);
        TvMAEmail= findViewById(R.id.TvMAEmail);
        TvMAaddress= findViewById(R.id.TvMAaddress);
        TvMAPhone=findViewById(R.id.TvMAphone);
        BTNback=findViewById(R.id.BTNback);



    }


}