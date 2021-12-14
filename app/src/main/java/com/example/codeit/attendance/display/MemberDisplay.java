package com.example.codeit.attendance.display;

import com.example.codeit.attendance.consepts.member.Member;

import java.util.List;

public interface MemberDisplay {

    void addMember(Member member);

    void removeMember(Member member);

    List<Member> getMembers();
}
