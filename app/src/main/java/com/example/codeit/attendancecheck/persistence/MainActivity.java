package com.example.deneme2;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText_name, editText_mac;
    Button button_add, button_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_name = findViewById(R.id.edittext_name);
        editText_mac = findViewById(R.id.edittext_mac);
        button_add = findViewById(R.id.button_add);
        button_view = findViewById(R.id.button_view);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringName = editText_name.getText().toString();
                String stringMac = editText_mac.getText().toString();

                if (stringName.length() <= 0 || stringMac.length() <= 0) {
                    Toast.makeText(MainActivity.this, "Boş bırakma dayı", Toast.LENGTH_SHORT).show();
                } else {
                    DBHelper dbHelper = new DBHelper(MainActivity.this);
                    User user = new User(stringName, stringMac);
                    dbHelper.addUser(user);
                    Toast.makeText(MainActivity.this, "Kullanıcı eklendi", Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(getIntent());
                }
            }
        });

        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ViewUserActivity.class);
                startActivity(intent);
            }
        });
    }
}