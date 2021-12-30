package com.example.codeit.attendance.check;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codeit.attendance.MainActivity;
import com.example.codeit.attendance.R;
import com.example.codeit.attendance.consepts.member.Member;
import com.example.codeit.attendance.display.ICustomItemClickListener;
import com.example.codeit.attendance.display.RecyclerViewAdapter;
import com.example.codeit.attendance.persistence.MemberAdapter;
import com.example.codeit.attendance.persistence.edit.ViewAndEditDBActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TEMSILI SINIF
public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_checked_members);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_check);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        List<Member> presentMembers = new ArrayList<>();


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String mac = intent.getStringExtra("mac");
        presentMembers.add(new Member(name,mac));


        RecyclerViewAdapter adapter_items = new RecyclerViewAdapter(presentMembers, new ICustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        });

        recyclerView.setAdapter(adapter_items);
    }
}

