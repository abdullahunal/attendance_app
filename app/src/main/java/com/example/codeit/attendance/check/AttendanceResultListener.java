package com.example.codeit.attendance.check;

@FunctionalInterface
public interface AttendanceResultListener {

    void attendanceDone(CheckResult checkResult);
}
