package com.example.saarc1.bookwala;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity  {

    private BottomNavigationView bottom_nav_bar;
    private FrameLayout main_frame;

    private HomeFragment homeFragment;
    private  CartFragment cartFragment;
    private CategoriesFragment categoriesFragment;
    private ProfileFragment profileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottom_nav_bar = (BottomNavigationView) findViewById(R.id.bottom_nav_bar);

        main_frame = (FrameLayout) findViewById(R.id.main_frame);

        homeFragment = new HomeFragment();
        cartFragment = new CartFragment();
        categoriesFragment = new CategoriesFragment();
        profileFragment = new ProfileFragment();


        bottom_nav_bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case  R.id.nav_home :

                        setfragment(homeFragment);

                        return true;


                    case R.id.nav_cart:

                          setfragment(cartFragment);

                          return true;

                    case R.id.nav_categories:

                        setfragment(categoriesFragment);
                        return true;

                    case R.id.nav_profile:

                        setfragment(profileFragment);
                        return true;



                        default:
                            return false;
                }
            }

            private void setfragment(Fragment fragment) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frame,fragment);
                fragmentTransaction.commit();
            }
        });

        if (savedInstanceState == null) {
            bottom_nav_bar.setSelectedItemId(R.id.nav_home);
        }

    }



}
