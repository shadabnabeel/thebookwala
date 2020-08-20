package com.example.saarc1.bookwala;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class ProfileFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    private TextView userEmail;
    private Button logoutbutton;
    private DatabaseReference databaseReference;
    private TextView welcome_user;
    private ImageView profileImage;
    private TextView currentOrders, previousOrders, changeAddress;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(uid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userinfo userInfo = dataSnapshot.getValue(userinfo.class);

                Map<String,Object> map = (Map<String, Object>) dataSnapshot.getValue();
               Object name = map.get("name");

                welcome_user.setText(name.toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        userEmail = getView().findViewById(R.id.userEmail);
        logoutbutton = getView().findViewById(R.id.logoutButton);
        welcome_user = getView().findViewById(R.id.welcome_user);

        currentOrders = getView().findViewById(R.id.currentOrders);

        changeAddress = getView().findViewById(R.id.changeAddress);

        currentOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),currentOrders.class);
                startActivity(intent);

            }
        });


        changeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), address_get.class);
                startActivity(intent);
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(getActivity(),Login.class));
        }


        FirebaseUser user = firebaseAuth.getCurrentUser();
        userEmail.setText(user.getEmail());


        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();

                Intent intent = new Intent(getActivity(),Login.class);
                startActivity(intent);
            }
        });


    }


}
