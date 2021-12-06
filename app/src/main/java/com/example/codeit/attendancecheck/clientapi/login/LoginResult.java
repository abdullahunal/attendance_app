package com.example.codeit.attendancecheck.clientapi.login;

import com.example.codeit.attendancecheck.consepts.member.Member;

import java.util.ArrayList;
import java.util.List;

public class LoginResult {

    private final List<Member> successfullyMembers;

    private final List<Member> failedMembers;

    public LoginResult() {
        this.successfullyMembers = new ArrayList<>();
        this.failedMembers = new ArrayList<>();
    }

    public List<Member> getSuccessfullyMembers() {
        return successfullyMembers;
    }

    public List<Member> getFailedMembers() {
        return failedMembers;
    }
}
