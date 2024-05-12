package com.example.recyclersqliteactivity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Userlist extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> name , description, Age;
    DBHelper DB;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        DB = new DBHelper(this);
        name = new ArrayList<>();
        description = new ArrayList<>();
        Age = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleview);
        adapter = new MyAdapter(this,name,description,Age);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata() {
        Cursor cursor = DB.getdata();
        if(cursor.getCount()==0){
            Toast.makeText(Userlist.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            while (cursor.moveToNext())
            {
                name.add(cursor.getString(0));
                description.add(cursor.getString(1));
                Age.add(cursor.getString(2));
            }
        }
    }


}