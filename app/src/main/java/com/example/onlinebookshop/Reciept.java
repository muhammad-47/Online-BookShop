package com.example.onlinebookshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Reciept extends AppCompatActivity {
    TextView BookName;
    TextView Author;
    TextView Price;
    TextView Description;
    TextView Name;
    TextView Address;
    TextView Phone;
    TextView Email;
    Button BTNback;


    private FirebaseUser user;
    private String userId;

    String name1,email1,Address1,Phone1;
    String name,author,des,price;

    private DatabaseReference auth;



    private DatabaseReference mDatabaseRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciept);
        init();
        name=getIntent().getStringExtra("name");
        author=getIntent().getStringExtra("author");
        des=getIntent().getStringExtra("des");
        price=getIntent().getStringExtra("price");
        BookName.setText("Book Name : "+name);
        Author.setText("Author Name : "+author);
        Description.setText("Description : "+des);
        Price.setText(" Price : "+price);

        mDatabaseRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users users = snapshot.getValue(Users.class);
                if (users !=null)
                {
                    name1 = users.name;
                    email1 = users.Email;
                    Phone1 = users.Phone;
                    Address1 = users.Address;

                    Name.setText("Name : "+name1);
                    Email.setText("Email : "+email1);
                    Phone.setText("Phone Number : "+Phone1);
                    Address.setText("Email Address : "+Address1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        BTNback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(Reciept.this,Home.class);
                startActivity(intent);
                Reciept.this.finish();

            }
        });


    }


    public void init()
    {

        BookName.findViewById(R.id.BookNameR);
        Author.findViewById(R.id.AuthorNameR);
        Price.findViewById(R.id.PriceR);
        Description.findViewById(R.id.BookDesR);
        Name.findViewById(R.id.NameR);
        Address.findViewById(R.id.AddressR);
        Phone.findViewById(R.id.PhoneR);
        Email.findViewById(R.id.EmailR);
        BTNback=findViewById(R.id.BTNback);


    }
}