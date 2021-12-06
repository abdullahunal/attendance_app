package com.example.codeit.attendancecheck.clientapi.login;

import android.os.Build;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.example.codeit.attendancecheck.consepts.member.Member;
import com.example.codeit.attendancecheck.display.MemberDisplay;
import com.example.codeit.attendancecheck.persistence.PersistenceDAO;

import java.util.Optional;

public class LoginManager implements View.OnClickListener {

    private Button loginButton;

    private MemberDisplay display;

    private PersistenceDAO persistenceDAO;

    public LoginManager(Button loginButton, MemberDisplay display, PersistenceDAO persistenceDAO) {
        this.loginButton = loginButton;
        this.display = display;
        this.persistenceDAO = persistenceDAO;
        loginButton.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        LoginResult loginResult = new LoginResult();
        for (Member member : display.getMembers()) {

            Optional<Member> optionalMember = persistenceDAO.readByAddress(member.getId());
            // TODO: name alani, kaydederken farkli, check ederken farkli olursa ne olacak?
            if (optionalMember.isPresent()) {
                loginResult.getSuccessfullyMembers().add(member);
            } else {
                loginResult.getFailedMembers().add(member);
            }
        }

//        return loginResult;
    }
}
