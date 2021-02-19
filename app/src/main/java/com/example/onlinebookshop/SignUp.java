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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    TextView TvEnterDetailSU;
    EditText EtNameSU;
    EditText EtEmailSU;
    EditText EtPassSU;
    EditText EtAddressSU,etPhoneSU;
    Button BtnSU;
    Button BtnBackToSignIn;

    String email,name,password,Address,Phone;
    private FirebaseAuth fireauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();

        BtnSU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 name= EtNameSU.getText().toString().trim();
                 email= EtEmailSU.getText().toString().trim();
                 Address= EtAddressSU.getText().toString().trim();
                 Phone = etPhoneSU.getText().toString().trim();
                 password= EtPassSU.getText().toString().trim();


                if(name.isEmpty()||Address.isEmpty()||email.isEmpty()||Phone.isEmpty()||password.isEmpty())
                {
                    Toast.makeText(SignUp.this, "Field is empty", Toast.LENGTH_SHORT).show();
                }

                else{

                SignupUser();

                }
            }
        });

        BtnBackToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(SignUp.this,MainActivity.class);
                startActivity(intent);

                SignUp.this.finish();
            }
        });


    }

    public void init(){

        TvEnterDetailSU = findViewById(R.id.TvEnterDetailSU);
        EtNameSU=findViewById(R.id.EtNameSU);
        etPhoneSU=findViewById(R.id.etPhoneSU);
        EtAddressSU=findViewById(R.id.EtAddressSU);
        EtEmailSU=findViewById(R.id.EtEmailSU);
        EtPassSU=findViewById(R.id.EtPassSU);
        BtnSU=findViewById(R.id.BtnSU);
        BtnBackToSignIn=findViewById(R.id.BtnBackToSignIn);
        fireauth = FirebaseAuth.getInstance();


    }
    private void SignupUser() {

        fireauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful())
                {
                    Users user = new Users(name,email,Address,Phone);
                    FirebaseDatabase.getInstance().getReference().child("SignUp")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (!task.isSuccessful())
                            {
                                Toast.makeText(SignUp.this, "Sorry User is not Registered! Try again ", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(SignUp.this, "User has been Register", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SignUp.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }


            }
        });




    }







}