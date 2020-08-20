package com.example.saarc1.bookwala;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Ccycle extends AppCompatActivity {
    DatabaseReference databaseReference;

    public static final String USER_ID = "userid";

    public static final String BOOK_NAME = "bookname";

    public static final String BOOK_AUTHOR = "bookauthor";

    public static final String BOOK_PRICE = "bookprice";

    public static final String BOOK_ID = "bookid";

    private ImageView cart_nav;


    RecyclerView recyclerView;

    ImageView imageView;

    private List<Book> bookList = new ArrayList<>();


    public Ccycle() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccycle);
    }
    @Override
    public void onStart() {
        super.onStart();


        recyclerView = findViewById(R.id.recyclerview_idC);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("book").child("Engineering").child("branch").child("Ccyle");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                bookList.clear();
                for (DataSnapshot bookSnapshot : dataSnapshot.getChildren()){
                    Book book = bookSnapshot.getValue(Book.class);

                    bookList.add(book);
                }

                BookList adapter = new BookList(Ccycle.this, bookList);
                recyclerView.setLayoutManager(new GridLayoutManager(Ccycle.this, 2));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
