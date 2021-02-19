package com.example.onlinebookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class Home extends AppCompatActivity {


    Button BtnSignOut;
    Button BtnSearch;
    Button BtnMyAccount;
    Button BtnShipping;
    Button BTNback;
    Button AddaBook;
    FirebaseAuth FirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        init();
       /* if(FirebaseAuth.getCurrentUser().getEmail()!="admin@gmail.com")
        {
            AddaBook.setVisibility(View.GONE);

        }*/

        BtnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Home.this,MainActivity.class);
                startActivity(intent);
                FirebaseAuth.getInstance().signOut();
                Home.this.finish();

            }
        });

        BtnMyAccount.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,MyAccount.class);
                startActivity(intent);

            }
        }));
        BtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Home.this,Books_List.class);
           startActivity(intent);
            }
        });
        AddaBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,BookDetails.class);
                startActivity(intent);
            }
        });
        BTNback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(Home.this,MainActivity.class);
                startActivity(intent);
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Home.this, "Signed Out", Toast.LENGTH_SHORT).show();
                Home.this.finish();

            }
        });
        BtnShipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,ShippingDetail.class);
                startActivity(intent);
            }
        });




    }



    public void init()
    {
        BtnSignOut=findViewById(R.id.BtnSignOut);
        BtnSearch=findViewById(R.id.BtnSearch);


        BtnMyAccount=findViewById(R.id.BtnMyAccount);
        BtnShipping=findViewById(R.id.BtnShipping);
        AddaBook=findViewById(R.id.AddaBook);
        BTNback=findViewById(R.id.BTNback);

    }
}