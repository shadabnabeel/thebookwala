package com.example.saarc1.bookwala;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;

    public static final String USER_ID = "userid";

    public static final String BOOK_NAME = "bookname";

    public static final String BOOK_AUTHOR = "bookauthor";

    public static final String BOOK_PRICE = "bookprice";

    public static final String BOOK_ID = "bookid";

    private ImageView cart_nav;


    RecyclerView recyclerView;
    SearchView searchView;
    private String searchText;
    EditText searchBar;
    Button searchBtn;

    ImageView imageView;

    private List<Book> bookList = new ArrayList<>();


    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("The two most powerful warriors are patience and time.");
        progressDialog.show();
        recyclerView = getView().findViewById(R.id.recyclerview_id);
        searchBar = getView().findViewById(R.id.searchbar);
        searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    return true;
                }
                return false;
            }
        });

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                searchText = searchBar.getText().toString().toUpperCase().trim();
            }
        });




        databaseReference = FirebaseDatabase.getInstance().getReference().child("allbooks");



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
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void performSearch() {

        FirebaseDatabase.getInstance().getReference("allbooks").orderByChild("bookName").startAt(searchText).endAt(searchText + "\uf8ff").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {

                bookList.clear();
                for (DataSnapshot bookSnapshot : dataSnapshot1.getChildren()){
                    Book book = bookSnapshot.getValue(Book.class);

                    bookList.add(book);
                }

                BookList adapter = new BookList(getActivity(), bookList);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                recyclerView.setAdapter(adapter);
                searchBar.setText(null);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}
