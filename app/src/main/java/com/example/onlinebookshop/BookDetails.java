package com.example.onlinebookshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class BookDetails extends AppCompatActivity {
EditText EtAddBookName;
    EditText EtAddBookDes;
    EditText EtAddBookAuthor;
    ImageView IvBookImage;
    String BookImageURL;
    Button BTNback;
    EditText Etprice;

    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference mReference;
    private FirebaseAuth fireauth;
    private DatabaseReference mDatabaseRef;
    String name;
    String des;
    String author;
    String price;
Button BtnAddBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
    init();

    BtnAddBook.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
             name=EtAddBookName.getText().toString().trim();
             des=EtAddBookDes.getText().toString().trim();
             author=EtAddBookAuthor.getText().toString().trim();
             price=Etprice.getText().toString().trim();

            if(name.isEmpty())
            {
                Toast.makeText(BookDetails.this, "Name is empty", Toast.LENGTH_SHORT).show();
            }
           else if(des.isEmpty())
            {
                Toast.makeText(BookDetails.this, "Description is empty", Toast.LENGTH_SHORT).show();
            }
            else if(author.isEmpty())
            {
                Toast.makeText(BookDetails.this, "Name is empty", Toast.LENGTH_SHORT).show();
            }
            else if(price.isEmpty())
            {
                Toast.makeText(BookDetails.this, "Price is empty", Toast.LENGTH_SHORT).show();
            }
            else {
                ImageUpload();

            }
        }
    });
    BTNback.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(BookDetails.this,Home.class);
            startActivity(intent);

            BookDetails.this.finish();
        }
    });

    }

    private void DataUpload() {

        Book book   =   new Book(name,des,author,price,BookImageURL);

        FirebaseDatabase.getInstance().getReference()
                .child("BookDetail")
                .push()
                .setValue(book)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(BookDetails.this, "Added", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(BookDetails.this, "failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(BookDetails.this, name+" has successfully add ", Toast.LENGTH_SHORT).show();
                BookDetails.this.finish();

            }
        });

    }


    public void choosePicture(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data!=null && data.getData()!=null)
        {
            imageUri = data.getData();
            IvBookImage.setImageURI(imageUri);
        }
    }

    private void ImageUpload() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image....");
        pd.show();


        if (imageUri !=null)
        {
            StorageReference storageRef = mReference.child("BookDetail").child(fireauth.getUid());
            storageRef.putFile(imageUri)
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful())
                            {
                                storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        BookImageURL = uri.toString();
                                        DataUpload();
                                    }
                                });
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pd.dismiss();
                            Toast.makeText(BookDetails.this, "Failed to upload the image", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progressPercent = (100.00 * snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    pd.setMessage("Percentage: "+(int)progressPercent+ "%");
                }

            });
        }
        else
        {
            Toast.makeText(this, "No file is Uploaded", Toast.LENGTH_SHORT).show();
        }

        BTNback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(BookDetails.this,Home.class);
                startActivity(intent);
                BookDetails.this.finish();
            }
        });


    }
    public void init(){

        EtAddBookName=findViewById(R.id.EtAddBookNameUp);
        EtAddBookDes=findViewById(R.id.EtAddBookDesUp);
        EtAddBookAuthor=findViewById(R.id.EtAddBookAuthorUp);
        BtnAddBook=findViewById(R.id.BtnAddBookDBUp);
        IvBookImage=findViewById(R.id.IvBookImage);
        BTNback=findViewById(R.id.BTNback);
        Etprice=findViewById(R.id.ETprice);
        fireauth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        mReference = storage.getReference().child("BookDetail");

    }




}