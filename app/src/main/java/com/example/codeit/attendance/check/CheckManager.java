package com.example.codeit.attendance.check;

import android.os.Build;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.example.codeit.attendance.consepts.export.ExcelExportation;
import com.example.codeit.attendance.consepts.member.Member;
import com.example.codeit.attendance.display.MemberDisplay;
import com.example.codeit.attendance.persistence.IDatabaseOperations;

public class CheckManager implements View.OnClickListener {

    private final Button loginButton;

    private final MemberDisplay display;

    private final IDatabaseOperations databaseOps;

    private final AttendanceResultListener attendanceResultListener;

    private final CheckResult checkResult;

    public CheckManager(Button loginButton, MemberDisplay display,
                        IDatabaseOperations databaseOps, AttendanceResultListener attendanceResultListener) {
        this.loginButton = loginButton;
        this.display = display;
        this.databaseOps = databaseOps;
        this.attendanceResultListener = attendanceResultListener;
        this.checkResult = new CheckResult();
        loginButton.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) { // check button
        checkResult.evaluateResult(display.getMembers(), databaseOps.readAll());
        ExcelExportation.exportToExcel(checkResult);
        attendanceResultListener.attendanceDone(checkResult);
    }
}
