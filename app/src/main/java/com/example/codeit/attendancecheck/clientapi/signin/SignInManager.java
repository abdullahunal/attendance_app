package com.example.codeit.attendancecheck.clientapi.signin;

import android.view.View;
import android.widget.Button;

import com.example.codeit.attendancecheck.consepts.member.Member;
import com.example.codeit.attendancecheck.display.MemberDisplay;
import com.example.codeit.attendancecheck.persistence.IDatabaseOperations;

public class SignInManager implements View.OnClickListener {

    private final Button signInButton;

    private final MemberDisplay display;

    private final IDatabaseOperations databaseOps;

    public SignInManager(Button signInButton, MemberDisplay display,
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
