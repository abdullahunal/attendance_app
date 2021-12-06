package com.example.codeit.attendancecheck.persistence;

import com.example.codeit.attendancecheck.consepts.member.Member;

import java.util.List;
import java.util.Optional;

public interface IDatabaseOperations {

    void create(Member member);

    Optional<Member> readByAddress(String mac);

    // User read(User user);

    List<Member> readAll();

    boolean updateUser(Member member);

    boolean deleteUser(int id);
}
