package com.example.codeit.attendance.clientapi.register;

import android.view.View;
import android.widget.Button;

import com.example.codeit.attendance.consepts.member.Member;
import com.example.codeit.attendance.display.MemberDisplay;
import com.example.codeit.attendance.persistence.IDatabaseOperations;

public class RegisterManager implements View.OnClickListener {

    private final Button signInButton;

    private final MemberDisplay display;

    private final IDatabaseOperations databaseOps;

    public RegisterManager(Button signInButton, MemberDisplay display,
            IDatabaseOperations databaseOps) {
        this.signInButton = signInButton;
        this.display = display;
        this.databaseOps = databaseOps;
        signInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        for (Member member : display.getMembers()) {
            databaseOps.create(member);
        }
    }
}
