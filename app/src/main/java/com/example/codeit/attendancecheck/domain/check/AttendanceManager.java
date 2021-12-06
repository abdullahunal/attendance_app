package com.example.codeit.attendancecheck.domain.check;

import com.example.codeit.attendancecheck.consepts.group.GroupManager;
import com.example.codeit.attendancecheck.consepts.member.MemberManager;

public class AttendanceManager {

    private final GroupManager groupManager;
    private final MemberManager memberManager;

    public AttendanceManager(GroupManager groupManager, MemberManager memberManager) {
        this.groupManager = groupManager;
        this.memberManager = memberManager;
    }
}
