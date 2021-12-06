package com.example.codeit.attendancecheck.consepts.member;

import java.util.List;

public abstract class Member {

    private String groupId;

    private  String id;

    private  String name;

    private List<String> attributes;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
