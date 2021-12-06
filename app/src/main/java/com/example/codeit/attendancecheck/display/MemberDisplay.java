package com.example.codeit.attendancecheck.display;

import com.example.codeit.attendancecheck.consepts.member.Member;

import java.util.List;

public interface MemberDisplay {

    void addMember(Member member);

    void removeMember(Member member);

    List<Member> getMembers();
}
