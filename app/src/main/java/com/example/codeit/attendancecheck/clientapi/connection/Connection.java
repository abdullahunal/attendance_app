package com.example.codeit.attendancecheck.clientapi.connection;

import com.example.codeit.attendancecheck.consepts.member.Member;

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
