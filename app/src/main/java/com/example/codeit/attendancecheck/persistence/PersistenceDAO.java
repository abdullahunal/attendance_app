package com.example.codeit.attendancecheck.persistence;

import com.example.codeit.attendancecheck.consepts.member.Member;

import java.util.List;
import java.util.Optional;

public interface PersistenceDAO {

    boolean create(Member member);

    List<Member> readByName(String name);

    Optional<Member> readByAddress(String address);

    boolean update(String oldAddress, Member newMember);

    boolean deleteByAddress(String address);
}
