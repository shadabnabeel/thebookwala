package com.example.saarc1.bookwala;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class mech_sem4 extends Fragment {

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

    public mech_sem4(){

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mech_sem4_layout,container,false);
        return view;
    }


    @Override
    public void onViewCreated( View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = getView().findViewById(R.id.recyclerview_id4);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("book").child("Engineering").child("branch").child("mechanical").child("Sem4");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                bookList.clear();
                for (DataSnapshot bookSnapshot : dataSnapshot.getChildren()){
                    Book book = bookSnapshot.getValue(Book.class);

                    bookList.add(book);
                }

                BookList adapter = new BookList(getActivity(), bookList);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
