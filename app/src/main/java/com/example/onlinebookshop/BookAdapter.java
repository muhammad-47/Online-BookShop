package com.example.onlinebookshop;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import static android.app.PendingIntent.getActivity;

public class BookAdapter extends FirebaseRecyclerAdapter<Book, BookAdapter.BookViewHolder> {
    Context context;


    public BookAdapter(@NonNull FirebaseRecyclerOptions<Book> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull BookViewHolder holder, int position, @NonNull Book model) {
        holder.TvNameView.setText(model.getName());
        holder.TvDesView.setText(model.getDes());
        holder.TvAuthorView.setText(model.getAuthor());
holder.TvPriceView.setText(model.getPrice());
holder.shopnow.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String name,author,des,price;
        name=model.getName();
        author=model.getAuthor();
        des=model.getDes();
        price=model.getPrice();
        Intent intent=new Intent(holder.itemView.getContext(),Reciept.class);
        intent.putExtra("name",name);
        intent.putExtra("author",author);
        intent.putExtra("des",des);
        intent.putExtra("price",price);
        holder.itemView.getContext().startActivity(intent);


    }
});
        Glide.with(holder.ivBookImage.getContext()).load(model.getImageUrl()).into(holder.ivBookImage);
        holder.IvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogPlus dialog = DialogPlus.newDialog(context)
                        .setContentHolder(new ViewHolder(R.layout.book_update_dialouge))
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View v=dialog.getHolderView();
                EditText etName,etDes,etAuthor,etprice;
                Button btnUpdate;
                etName=v.findViewById(R.id.EtAddBookNameUp);
                etDes=v.findViewById(R.id.EtAddBookDesUp);
                etAuthor=v.findViewById(R.id.EtAddBookAuthorUp);
                btnUpdate=v.findViewById(R.id.BtnAddBookDBUp);
                etprice=v.findViewById(R.id.ETprice);

                etName.setText(model.getName());
                etDes.setText(model.getDes());
                etAuthor.setText(model.getAuthor());
                etprice.setText(model.getPrice());

                dialog.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("name",etName.getText().toString().trim());
                        map.put("author",etAuthor.getText().toString().trim());
                        map.put("des",etDes.getText().toString().trim());
                        FirebaseDatabase.getInstance().getReference()
                                .child("BookDetail")
                                .child(getRef(position).getKey())
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(context, "Data updated", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                        etName.setText("");
                                        etAuthor.setText("");
                                        etDes.setText("");
                                        etprice.setText("");
                                    }
                                });
                    }
                });

            }
        });
        holder.IvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("BookDETAIL")
                        .removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);
        return new BookViewHolder(view);

    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        TextView TvNameView, TvDesView, TvAuthorView,TvPriceView;
        ImageView IvEdit, IvDelete,ivBookImage;
        Button shopnow;


        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            TvNameView = itemView.findViewById(R.id.TvNameView);
            TvDesView = itemView.findViewById(R.id.TvDesView);
            TvAuthorView = itemView.findViewById(R.id.TvAuthorView);
            ivBookImage = itemView.findViewById(R.id.ivBookImage);
            TvPriceView=itemView.findViewById(R.id.TvPriceView);
            IvEdit = itemView.findViewById(R.id.IvEdit);
            IvDelete = itemView.findViewById(R.id.IvDelete);
            shopnow=itemView.findViewById(R.id.Btnshopnow);


        }
    }


}
