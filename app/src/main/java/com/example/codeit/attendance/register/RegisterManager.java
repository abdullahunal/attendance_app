package com.example.codeit.attendance.register;

import android.os.Build;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.example.codeit.attendance.consepts.member.Member;
import com.example.codeit.attendance.display.MemberDisplay;
import com.example.codeit.attendance.persistence.IDatabaseOperations;

import java.util.List;
import java.util.Optional;

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        List<Member> members = display.getMembers();
        for (Member member : members) {
            Optional<Member> optionalMember = databaseOps.readByAddress(member.getMac());
            if (!optionalMember.isPresent()) {
                databaseOps.create(member);
            }
        }
    }
}