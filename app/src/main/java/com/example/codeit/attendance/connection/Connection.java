package com.example.codeit.attendance.connection;

import com.example.codeit.attendance.consepts.member.Member;

public interface Connection {

    void connect();

    void disconnect();

    default Member getMember() {
        return null;
    }

    default void sendOKResponse() {
    }

    default void sendNOResponse() {
    }

    default void shareData(AttendanceData attendanceData) {
    }
}
