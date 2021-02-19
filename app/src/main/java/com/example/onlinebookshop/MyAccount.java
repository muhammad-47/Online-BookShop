package com.example.onlinebookshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class MyAccount<userId> extends AppCompatActivity {
    TextView TvAccount;
    TextView TvMAName;
    TextView TvMAEmail;
    TextView TvMAPhone;
    TextView TvMAaddress;
    Button BtnUpdate;
    EditText EtNameMA;
    EditText EtEmailMA;
    EditText EtPhoneMA;
    EditText EtAddressMA;
    Button BtnSave;
    String name;
    String email;
    String address;
    String phone;Button BTNback;
    Button BtnBackHome;


    private FirebaseUser user;
    private String userId;

    String name1,email1,Address1,Phone1;
private DatabaseReference auth;


    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        init();

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

                    TvMAName.setText("Name : "+name1);
                    TvMAEmail.setText("Email : "+email1);
                    TvMAPhone.setText("Phone Number : "+Phone1);
                    TvMAaddress.setText("Email Address : "+Address1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        EtPhoneMA.setVisibility(View.GONE);
        EtEmailMA.setVisibility(View.GONE);
        EtNameMA.setVisibility(View.GONE);
        EtAddressMA.setVisibility(View.GONE);
        BtnSave.setVisibility(View.GONE);

        BtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TvMAPhone.setVisibility(View.GONE);
                TvMAName.setVisibility(View.GONE);
                TvMAaddress.setVisibility(View.GONE);
                TvMAEmail.setVisibility(View.GONE);
                BtnUpdate.setVisibility(View.GONE);

                EtPhoneMA.setVisibility(View.VISIBLE);
                EtEmailMA.setVisibility(View.VISIBLE);
                EtNameMA.setVisibility(View.VISIBLE);
                EtAddressMA.setVisibility(View.VISIBLE);
                BtnSave.setVisibility(View.VISIBLE);

                EtNameMA.setText(name1);
                EtEmailMA.setText(email1);
                EtAddressMA.setText(Address1);
                EtPhoneMA.setText(Phone1);


            }
        });
        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 name = EtNameMA.getText().toString().trim();
                 email = EtEmailMA.getText().toString().trim();
                 address = EtAddressMA.getText().toString().trim();
                 phone = EtPhoneMA.getText().toString().trim();

                TvMAName.setText("Name : "+name);
                TvMAEmail.setText("Email : "+email);
                TvMAPhone.setText("Phone Number : "+phone);
                TvMAaddress.setText("Email Adress : "+address);

                HashMap<String, Object> data = new HashMap<>();
                data.put("name", name);
                data.put("address", address);
                data.put("email", email);
                data.put("phone", phone);


                FirebaseDatabase.getInstance().getReference().child("SignUp")
                        .updateChildren(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MyAccount.this, "Data updated", Toast.LENGTH_SHORT).show();

                    }
                });




                TvMAPhone.setVisibility(View.VISIBLE);
                TvMAName.setVisibility(View.VISIBLE);
                TvMAaddress.setVisibility(View.VISIBLE);
                TvMAEmail.setVisibility(View.VISIBLE);
                BtnUpdate.setVisibility(View.VISIBLE);

                EtPhoneMA.setVisibility(View.GONE);
                EtEmailMA.setVisibility(View.GONE);
                EtNameMA.setVisibility(View.GONE);
                EtAddressMA.setVisibility(View.GONE);
                BtnSave.setVisibility(View.GONE);



            }
        });
       BtnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MyAccount.this,Home.class);
                startActivity(intent);
                MyAccount.this.finish();
            }
        });
        BTNback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(MyAccount.this,Home.class);
                startActivity(intent);
                MyAccount.this.finish();
            }
        });

    }

    public void init(){

        TvAccount= findViewById(R.id.TvAccount);
        TvMAName= findViewById(R.id.TvMAName);
        TvMAEmail= findViewById(R.id.TvMAEmail);
        TvMAaddress= findViewById(R.id.TvMAaddress);
        TvMAPhone=findViewById(R.id.TvMAphone);
        BtnUpdate= findViewById(R.id.BtnUpdate);
        EtAddressMA=findViewById(R.id.EtAdressMA);
        EtNameMA=findViewById(R.id.EtNameMA);
        EtEmailMA=findViewById(R.id.EtEmailMA);
        EtPhoneMA=findViewById(R.id.EtNumberMA);
        BtnSave=findViewById(R.id.BtnSave);
        BtnBackHome=findViewById(R.id.BtnBackHome);
        BTNback=findViewById(R.id.BTNback);
        user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("SignUp");
        userId = user.getUid();



    }


}