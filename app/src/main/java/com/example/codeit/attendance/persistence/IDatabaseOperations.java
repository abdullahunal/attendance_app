package com.example.codeit.attendance.persistence;

import com.example.codeit.attendance.consepts.member.Member;

import java.util.List;
import java.util.Optional;

public interface IDatabaseOperations {

    void create(Member member);

    Optional<Member> readByAddress(String mac);

    List<Member> readAll();

    boolean updateUser(Member member);

    boolean deleteUser(int id);
}
