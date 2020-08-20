package com.example.saarc1.bookwala;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPickerListener;
import com.squareup.picasso.Picasso;


public class bookdescription extends AppCompatActivity {

    TextView book_desc_name;
    TextView book_desc_author;
    TextView book_desc_price;
    ImageView book_desc_img;

    private String image;

    int Quantity;
    int TPrice;
    int pRice;



    Button add_to_cart;
    DatabaseReference databaseCart;
    FirebaseAuth user;
    private Spinner numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookdescription);

        book_desc_img = findViewById(R.id.book_desc_img);


        add_to_cart = findViewById(R.id.add_to_cart);

        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtocart();
            }
        });

        book_desc_name = findViewById(R.id.book_desc_name);
        book_desc_author = findViewById(R.id.book_desc_author);
        book_desc_price = findViewById(R.id.book_desc_Price);
        numberPicker = findViewById(R.id.numberPicker);


        Intent intent = getIntent();

        String name = intent.getExtras().getString("bookName");
        String author = intent.getExtras().getString("bookAuthor");
        String price = intent.getExtras().getString("bookPrice");
        image = intent.getExtras().getString("bookImage");


        book_desc_name.setText(name);
        book_desc_author.setText(author);
        book_desc_price.setText(price);
        Picasso.get().load(image).into(book_desc_img);

        pRice = Integer.parseInt(price);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numberPicker, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numberPicker.setAdapter(adapter);

        numberPicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Quantity = Integer.parseInt(parent.getItemAtPosition(position).toString());
                TPrice = Quantity * pRice;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String item_id = intent.getExtras().getString("bookId");

        databaseCart  = FirebaseDatabase.getInstance().getReference("shoppingCart").child(user).child(item_id);


    }

    private void addtocart() {
        String bookName = book_desc_name.getText().toString().trim();
        String bookPrice = book_desc_price.getText().toString().trim();
        String bookAuthor = book_desc_author.getText().toString().trim();
        String bookImage = image;
        String quantity = Integer.toString(Quantity).trim();
        String totalPrice =  Integer.toString(TPrice).trim();






        String id = databaseCart.push().getKey();

        cartAdapter cart = new cartAdapter(id,bookName,bookPrice,bookAuthor, bookImage ,quantity,totalPrice);

        databaseCart.setValue(cart);

        Toast.makeText(this,"Added to cart",Toast.LENGTH_LONG).show();

    }

}
