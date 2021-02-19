package com.example.onlinebookshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Books_List extends AppCompatActivity {
RecyclerView recyclerView;
BookAdapter bookAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books__list);
        recyclerView=findViewById(R.id.recyclerviewBooks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();
        FirebaseRecyclerOptions<Book> options =
                new FirebaseRecyclerOptions.Builder<Book>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("BookDetail"), Book.class)
                        .build();
        bookAdapter = new BookAdapter(options,this);
        recyclerView.setAdapter(bookAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        bookAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bookAdapter.stopListening();
    }
}