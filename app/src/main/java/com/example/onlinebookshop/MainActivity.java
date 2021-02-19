package com.example.onlinebookshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView TvSignInDetail;
    TextView TvUsernameSI;
    TextView check;
    EditText EtUsernameEnterSI;
    EditText EtPassSI;
    Button BtnSI;
    Button BtnSUf;
    private FirebaseAuth fireauth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        BtnSUf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        BtnSI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String email= EtUsernameEnterSI.getText().toString().trim();
                String password= EtPassSI.getText().toString().trim();
                if(email.isEmpty()||password.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Missing Field", Toast.LENGTH_SHORT).show();


                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    EtUsernameEnterSI.setError("Please Provide valid email ");
                    EtUsernameEnterSI.requestFocus();
                }
                else if (password.length()<6)
                {
                    EtPassSI.setError("Min Password length should not be 6 character");
                    EtPassSI.requestFocus();
                }
                else {

                    fireauth.signInWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(MainActivity.this, "Login Successful ", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity.this, Home.class);
                                        startActivity(intent);
                                        EtUsernameEnterSI.setText("");
                                        EtPassSI.setText("");
                                    }
                                    else{
                                        Toast.makeText(MainActivity.this, "Sorry please Try again ", Toast.LENGTH_SHORT).show();
                                        EtPassSI.setError("Email/Password is wrong");
                                    }


                                }
                            });


                    {

                }

            }

                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);
            }});



    }

    public void init(){

        TvSignInDetail= findViewById(R.id.TvSignInDetail);
        TvUsernameSI= findViewById(R.id.TvUsernameSI);
        EtUsernameEnterSI= findViewById(R.id.EtUsernameEnterSI);
        EtPassSI= findViewById(R.id.EtPassSI);
        BtnSI= findViewById(R.id.BtnSI);
        BtnSUf= findViewById(R.id.BtnSUf);

        fireauth = FirebaseAuth.getInstance();


    }

}