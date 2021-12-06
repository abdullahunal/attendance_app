package com.example.codeit.attendancecheck.persistence;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.codeit.attendancecheck.consepts.member.Member;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CachePersistenceDAO implements PersistenceDAO {

    private final Set<Member> dataSource = new HashSet<>();

    @Override
    public boolean create(Member member) {
        return dataSource.add(member);
    }

    @Override
    public List<Member> readByName(String name) {
        List<Member> members = new ArrayList<>();
        for (Member member : dataSource) {
            if (member.getName().equalsIgnoreCase(name)) {
                members.add(member);
            }
        }
        return members;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Optional<Member> readByAddress(String address) {
        for (Member member : dataSource) {
            if (member.getMac().equalsIgnoreCase(address)) {
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean update(String oldAddress, Member newMember) {
        for (Member member : dataSource) {
            if (member.getMac().equalsIgnoreCase(oldAddress)) {
                member.setId(newMember.getId());
                member.setName(newMember.getName());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByAddress(String address) {
        while (dataSource.iterator().hasNext()) {
            Member member = dataSource.iterator().next();
            if (member.getMac().equalsIgnoreCase(address)) {
                dataSource.remove(member);
                return true;
            }
        }
        return false;
    }
}
