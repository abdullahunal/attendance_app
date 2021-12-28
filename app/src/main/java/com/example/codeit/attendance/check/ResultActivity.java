package com.example.codeit.attendance.check;

import java.util.List;

// TEMSILI SINIF
public class ResultActivity {

    public void setResult(CheckResult checkResult) {
        // TODO: Dummy Method!
        //  burasi sonuclarin listelendigi activity
        //  burada aldi sonuclar listeleyecek.

        checkResult.getAbsentMembers();
        checkResult.getPresentMembers();
        checkResult.getUnregisteredMembers();

    }
}
