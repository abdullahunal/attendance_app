package com.example.deneme2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewUserActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DBHelper dbHelper = new DBHelper(this);
        List<User> users = dbHelper.getUserList();

        if (users.size() > 0) {
            UserAdapter userAdapter = new UserAdapter(users,ViewUserActivity.this);
            recyclerView.setAdapter(userAdapter);

        }else{
            Toast.makeText(this, "Veritabanı boş", Toast.LENGTH_SHORT).show();
        }
    }
}