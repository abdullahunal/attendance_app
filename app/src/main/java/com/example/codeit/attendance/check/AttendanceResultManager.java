package com.example.codeit.attendance.check;

import android.content.Intent;

import com.example.codeit.attendance.MainActivity;

public class AttendanceResultManager implements AttendanceResultListener {

    private final MainActivity mainActivity;

    public AttendanceResultManager(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void attendanceDone(CheckResult result) {

        Intent intent = new Intent(mainActivity.getApplicationContext(), ResultActivity.class);

        intent.putExtra("present", result.getPresentMembers().toArray());
        intent.putExtra("absent", result.getAbsentMembers().toArray());
        intent.putExtra("unregistered", result.getUnregisteredMembers().toArray());

        /*intent.putExtra("boyutAbsent", result.getAbsentMembers().size());
        intent.putExtra("boyutPresent", result.getPresentMembers().size());
        intent.putExtra("boyutUnregistered", result.getUnregisteredMembers().size());

        for (int i = 0; i < result.getAbsentMembers().size(); i++) {
            intent.putExtra("keyName" + (i + 1), result.getAbsentMembers().get(i).getName());
            intent.putExtra("keyMac" + (i + 1), result.getAbsentMembers().get(i).getMac());
        }

        for (int i = 0; i < result.getPresentMembers().size(); i++) {
            intent.putExtra("keyNamePresent" + (i + 1), result.getPresentMembers().get(i).getName());
            intent.putExtra("keyMacPresent" + (i + 1), result.getPresentMembers().get(i).getMac());
        }

        for (int i = 0; i < result.getUnregisteredMembers().size(); i++) {
            intent.putExtra("keyNameUnregistered" + (i + 1), result.getUnregisteredMembers().get(i).getName());
            intent.putExtra("keyMacUnregistered" + (i + 1), result.getUnregisteredMembers().get(i).getMac());
        }*/

        /*intent.putExtra("keyName1", result.getAbsentMembers().get(0).getName());
        intent.putExtra("keyMac1", result.getAbsentMembers().get(0).getMac());
        intent.putExtra("keyName2", result.getAbsentMembers().get(1).getName());
        intent.putExtra("keyMac2", result.getAbsentMembers().get(1).getMac());*/


        // intent.putExtra(CheckResult.PRESENT_NAME, result.getPresentMembers().toArray());
        // intent.putExtra(CheckResult.PRESENT_MAC,result.getPresentMembers().toArray());

        // intent.putExtra(CheckResult.PRESENT_NAME, result.getPresentMembers().get(0).getName());

        // 1
        /*intent.putExtra("listeboyutu", result.getPresentMembers().size());
        for (int i = 0; i < result.getPresentMembers().size(); i++) {
            intent.putExtra("keyName" + i + 1, result.getPresentMembers().get(i).getName());
        }
        for (int i = 0; i < result.getPresentMembers().size(); i++) {
            intent.putExtra("keyMac" + i + 1, result.getPresentMembers().get(i).getMac());
        }*/


        /*try {
            intent.putExtra("key1", result.getPresentMembers().get(0).getName());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        // intent.putExtra("key2", result.getPresentMembers().get(1).getName());

        // intent.putExtra(CheckResult.PRESENT_MAC, result.getPresentMembers().toArray());

        mainActivity.startActivity(intent);
    }
}