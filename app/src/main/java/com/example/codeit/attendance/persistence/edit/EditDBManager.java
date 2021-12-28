package com.example.codeit.attendance.persistence.edit;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codeit.attendance.MainActivity;
import com.example.codeit.attendance.R;
import com.example.codeit.attendance.consepts.member.Member;
import com.example.codeit.attendance.persistence.DBHelper;

public class EditDBManager extends MainActivity {

    /*Button button_add,button_view_edit;
    EditText editText_name,editText_mac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        button_add=findViewById(R.id.btn_add);
        button_view_edit=findViewById(R.id.button_edit);
        editText_name = findViewById(R.id.edittext_name);
        editText_mac = findViewById(R.id.edittext_mac);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = editText_name.getText().toString();
                String stringMac = editText_mac.getText().toString();

                if (stringName.length() <= 0 || stringMac.length() <= 0) {
                    Toast.makeText(v.getContext(), "Boş bırakma dayı", Toast.LENGTH_SHORT).show();
                } else {
                    DBHelper dbHelper = new DBHelper(v.getContext());
                    Member member = new Member(stringName, stringMac);
                    dbHelper.create(member);
                    Toast.makeText(v.getContext(), "Kullanıcı eklendi", Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(getIntent());
                }
            }
        });

        button_view_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewAndEditDBActivity.class);
                startActivity(intent);
            }
        });
    }*/


    /*Button button_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_list = findViewById(R.id.btnListUsers);

        button_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),ViewAndEditDBActivity.class);
                startActivity(intent);
            }
        });
    }*/
}
