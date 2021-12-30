package com.example.codeit.attendance.check;

import android.content.Intent;

import com.example.codeit.attendance.MainActivity;
import com.example.codeit.attendance.consepts.member.Member;

import java.util.ArrayList;
import java.util.List;

public class AttendanceResultManager implements AttendanceResultListener {

    private final MainActivity mainActivity;

    public AttendanceResultManager(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void attendanceDone(CheckResult result) {

        Intent intent = new Intent(mainActivity.getApplicationContext(), ResultActivity.class);

        intent.putExtra("name","name");
        intent.putExtra("mac","mac");
        mainActivity.startActivity(intent);
    }
}
