package com.example.codeit.attendance.persistence.edit;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codeit.attendance.R;
import com.example.codeit.attendance.consepts.member.Member;
import com.example.codeit.attendance.persistence.DBHelper;
import com.example.codeit.attendance.persistence.MemberAdapter;

import java.util.List;

public class ViewAndEditDBActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edit_db);

        refreshFromDb();
    }

    private void refreshFromDb() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DBHelper dbHelper = new DBHelper(this);
        List<Member> members = dbHelper.readAll();

        if (members.size() > 0) {
            MemberAdapter memberAdapter = new MemberAdapter(members, ViewAndEditDBActivity.this);
            recyclerView.setAdapter(memberAdapter);

        } else {
            Toast.makeText(this, "Veritabanı boş", Toast.LENGTH_SHORT).show();
        }
    }
}
