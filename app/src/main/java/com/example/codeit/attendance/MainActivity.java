package com.example.codeit.attendance;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codeit.attendance.server.AttendanceServer;
import com.example.codeit.attendance.server.Server;

public class MainActivity extends AppCompatActivity {

    private final Server server;

    public MainActivity() {
        this.server = new AttendanceServer(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        server.start();

    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        stopServer();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        stopServer();
//    }

    private void stopServer() {
        server.stop();
        finishAndRemoveTask();
        System.exit(0);
    }
}