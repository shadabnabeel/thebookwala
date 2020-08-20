package com.example.saarc1.bookwala;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartFragment extends Fragment {

    DatabaseReference databaseReference;
    ListView listCart;
    TextView mTotal;
    Button btn_confirm;
    int DEFAULT_CART_VALUE = 00;
    TextView totalBooks, deliveryCharges;
    RadioButton cod;

    private List<cartAdapter> cartList = new ArrayList<>();


    public  CartFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();


        listCart = getView().findViewById(R.id.listCart);

        mTotal = (TextView)getView().findViewById(R.id.totalCartValue);

        btn_confirm = (Button)getView().findViewById(R.id.confBtn);

        totalBooks = getView().findViewById(R.id.totalBooks);

        deliveryCharges = getView().findViewById(R.id.deliveryCharges);

        cod = getView().findViewById(R.id.codBtn);


        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("shoppingCart").child(userId);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                cartList.clear();
                int sum=0;
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    cartAdapter cart = dataSnapshot1.getValue(cartAdapter.class);

                    Map<String,Object> map = (Map<String, Object>) dataSnapshot1.getValue();
                    Object price = map.get("totalPrice");
                    int pValue = Integer.parseInt(String.valueOf(price));
                    sum = sum+pValue;

                    if (sum >= 750) {

                        mTotal.setText(String.valueOf(sum));
                        totalBooks.setText(String.valueOf(sum));
                        deliveryCharges.setText("Free");
                    } else {

                        mTotal.setText(String.valueOf(sum + 30));
                        totalBooks.setText(String.valueOf(sum));
                        deliveryCharges.setText("30");
                    }

                    cartList.add(cart);
                }


                if (getActivity()!=null) {
                    cartList adapter = new cartList(getActivity(), cartList);
                    listCart.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setCancelable(true);
                builder.setTitle("Are You Sure You Want To Confirm Your Order");
                builder.setMessage("The Order Once Placed Cannot Be Cancelled, Press Yes to Confirm Your Order");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        saveOrder();

                    }
                });
                builder.show();
            }
        });

    }


    private void saveOrder(){
        final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference("shoppingCart").child(userId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        FirebaseDatabase.getInstance().getReference("orders").child(userId).setValue(dataSnapshot.getValue());
                        FirebaseDatabase.getInstance().getReference().child("shoppingCart").child(userId).removeValue();

                        mTotal.setText(String.valueOf(DEFAULT_CART_VALUE));
                        totalBooks.setText(String.valueOf(DEFAULT_CART_VALUE));
                        deliveryCharges.setText(String.valueOf(DEFAULT_CART_VALUE));

                        Intent intent = new Intent(getActivity(), ThankYou.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

}