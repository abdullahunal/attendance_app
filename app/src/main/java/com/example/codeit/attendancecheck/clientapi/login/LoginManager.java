package com.example.codeit.attendancecheck.clientapi.login;

import android.os.Build;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.example.codeit.attendancecheck.consepts.member.Member;
import com.example.codeit.attendancecheck.display.MemberDisplay;
import com.example.codeit.attendancecheck.persistence.IDatabaseOperations;

import java.util.Optional;

public class LoginManager implements View.OnClickListener {

    private final Button loginButton;

    private final MemberDisplay display;

    private final IDatabaseOperations databaseOps;

    public LoginManager(Button loginButton, MemberDisplay display,
            IDatabaseOperations databaseOps) {
        this.loginButton = loginButton;
        this.display = display;
        this.databaseOps = databaseOps;
        loginButton.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        LoginResult loginResult = new LoginResult();
        for (Member member : display.getMembers()) {

            Optional<Member> optionalMember = databaseOps.readByAddress(member.getMac());
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
