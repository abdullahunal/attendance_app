package com.example.codeit.attendance.check;

public class AttendanceResultListenerImpl implements AttendanceResultListener {

    private final ResultActivity resultActivity;

    public AttendanceResultListenerImpl(ResultActivity resultActivity) {
        this.resultActivity = resultActivity;
        // TODO:burada bir activity alacak..
    }

    @Override
    public void attendanceDone(CheckResult checkResult) {

        resultActivity.setResult(checkResult);
        // TODO: .. ve sonucları orada gosterecek

    }
}
