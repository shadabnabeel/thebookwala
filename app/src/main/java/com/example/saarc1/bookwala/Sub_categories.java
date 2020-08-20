package com.example.saarc1.bookwala;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Sub_categories extends AppCompatActivity {

    ListView listView;
    DatabaseReference databaseReference1;
    private List<Book> bookList = new ArrayList<>();
    RecyclerView recyclerView;

    int images[] = {R.drawable.ccycle1,R.drawable.pcycle1,R.drawable.mech_engg1,R.drawable.civil_engg1,
            R.drawable.ele_engg1,R.drawable.cs_engg1,R.drawable.ele_engg1,R.drawable.ei_engg1,
            R.drawable.cct_engg1,R.drawable.ip_engg1,R.drawable.aero_engg1};

    String[] names = {"C-Cycle","P-Cycle","Mechanical Engineering","Civil Engineering",
            "Electronics and Communication Engineering","Computer Science Engineering",
            "Electrical Engineering","Electronics and Instrumentation Engineering",
            "Cement and Ceramics Technology Engineering","Industrial Production Engineering",
            "Aeronautical Engineering"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_categories);

        listView = findViewById(R.id.sub_listView);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(Sub_categories.this,Ccycle.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(Sub_categories.this,PCycle.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(Sub_categories.this,mechanical_engg.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(Sub_categories.this,civil_eng.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(Sub_categories.this,ENC_eng.class);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(Sub_categories.this,cs_eng.class);
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(Sub_categories.this,ee_eng.class);
                        startActivity(intent6);
                        break;
                    case 7:
                        Intent intent7 = new Intent(Sub_categories.this,ei_eng.class);
                        startActivity(intent7);
                        break;
                    case 8:
                        Intent intent8 = new Intent(Sub_categories.this,cct_eng.class);
                        startActivity(intent8);
                        break;
                    case 9:
                        Intent intent9 = new Intent(Sub_categories.this,ip_eng.class);
                        startActivity(intent9);
                        break;
                    case 10:
                        Intent intent10 = new Intent(Sub_categories.this,au_eng.class);
                        startActivity(intent10);
                        break;

                }
            }
        });

        }

        class CustomAdapter extends BaseAdapter{


            @Override
            public int getCount() {
                return images.length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = getLayoutInflater().inflate(R.layout.sub_layout,null);
                ImageView imageView = convertView.findViewById(R.id.img);
                TextView textView = convertView.findViewById(R.id.branch_name);
                imageView.setImageResource(images[position]);
                textView.setText(names[position]);
                return convertView;
            }
        }
}
