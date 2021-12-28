package com.example.codeit.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codeit.attendance.consepts.member.Member;
import com.example.codeit.attendance.persistence.DBHelper;
import com.example.codeit.attendance.persistence.edit.EditDBManager;
import com.example.codeit.attendance.persistence.edit.ViewAndEditDBActivity;
import com.example.codeit.attendance.server.AttendanceServer;
import com.example.codeit.attendance.server.Server;

public class MainActivity extends AppCompatActivity {

    private final Server server;

    public MainActivity() {
        this.server = new AttendanceServer(this);
    }

    Button button_add, button_view_edit;
    EditText editText_name, editText_mac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        server.start();

        // taşınacak
        button_add = findViewById(R.id.btn_add);
        button_view_edit = findViewById(R.id.btnListUsers);
        editText_name = findViewById(R.id.edittext_name);
        editText_mac = findViewById(R.id.edittext_mac);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = editText_name.getText().toString();
                String stringMac = editText_mac.getText().toString();

                if (stringName.length() <= 0 || stringMac.length() <= 0) {
                    Toast.makeText(MainActivity.this, "Boş bırakma dayı", Toast.LENGTH_SHORT).show();
                } else {
                    DBHelper dbHelper = new DBHelper(MainActivity.this);
                    Member member = new Member(stringName, stringMac);
                    dbHelper.create(member);
                    Toast.makeText(MainActivity.this, "Kullanıcı eklendi", Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(getIntent());
                }
            }
        });

        button_view_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewAndEditDBActivity.class);
                startActivity(intent);
            }
        });
        //taşınacak
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopServer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopServer();
    }

    private void stopServer() {
        server.stop();
        finishAndRemoveTask();
        System.exit(0);
    }
}