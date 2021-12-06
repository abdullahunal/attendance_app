package com.example.codeit.attendancecheck.clientapi.signin;

import android.view.View;
import android.widget.Button;

import com.example.codeit.attendancecheck.consepts.member.Member;
import com.example.codeit.attendancecheck.display.MemberDisplay;
import com.example.codeit.attendancecheck.persistence.PersistenceDAO;

public class SignInManager implements View.OnClickListener {

    private final Button signInButton;

    private final MemberDisplay display;

    private final PersistenceDAO persistenceDAO;

    public SignInManager(Button signInButton, MemberDisplay display,
            PersistenceDAO persistenceDAO) {
        this.signInButton = signInButton;
        this.display = display;
        this.persistenceDAO = persistenceDAO;
        signInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        for (Member member : display.getMembers()) {
            persistenceDAO.create(member);
        }
    }
}
