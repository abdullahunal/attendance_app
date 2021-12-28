package com.example.codeit.attendance.persistence.edit;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.codeit.attendance.MainActivity;
import com.example.codeit.attendance.R;
import com.example.codeit.attendance.consepts.member.Member;
import com.example.codeit.attendance.persistence.DBHelper;

public class EditDBManager {

    private final MainActivity mainActivity;

    public EditDBManager(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void initialize() {
        Button button_add = mainActivity.findViewById(R.id.btn_add);
        EditText editText_name = mainActivity.findViewById(R.id.edittext_name);
        EditText editText_mac = mainActivity.findViewById(R.id.edittext_mac);
        button_add.setOnClickListener(buttonAddClickListener(editText_name, editText_mac));

        Button button_view_edit = mainActivity.findViewById(R.id.btnListUsers);
        button_view_edit.setOnClickListener(buttonListClickListener());
    }

    @NonNull
    private View.OnClickListener buttonListClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity, ViewAndEditDBActivity.class);
                mainActivity.startActivity(intent);
            }
        };
    }

    @NonNull
    private View.OnClickListener buttonAddClickListener(EditText editText_name,
            EditText editText_mac) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText_name.getText().toString();
                String mac = editText_mac.getText().toString();

                if (name.isEmpty() || mac.isEmpty()) {
                    Toast.makeText(mainActivity, "Boş bırakma dayı", Toast.LENGTH_SHORT).show();
                } else {
                    try (DBHelper dbHelper = new DBHelper(mainActivity)) {
                        Member member = new Member(name, mac);
                        dbHelper.create(member);
                    }
                    Toast.makeText(mainActivity, "Kullanıcı eklendi", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
}
