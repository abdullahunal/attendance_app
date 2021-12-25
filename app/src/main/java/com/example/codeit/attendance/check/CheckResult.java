package com.example.codeit.attendance.check;

import com.example.codeit.attendance.consepts.member.Member;

import java.util.ArrayList;
import java.util.List;

public class CheckResult {

    /**
     * Derse gelmis kayitli ogrenciler
     */
    private final List<Member> presentMembers;

    /**
     * Derse gelmis ama kayitli olmayan ogrenciler
     */
    private final List<Member> unregisteredMembers;

    /**
     * Derse gelmemis ogrenciler
     */
    private final List<Member> absentMembers;

    public CheckResult() {
        this.presentMembers = new ArrayList<>();
        this.unregisteredMembers = new ArrayList<>();
        this.absentMembers = new ArrayList<>();
    }

    public List<Member> getPresentMembers() {
        return presentMembers;
    }

    public List<Member> getUnregisteredMembers() {
        return unregisteredMembers;
    }

    public List<Member> getAbsentMembers() {
        return absentMembers;
    }

    public void evaluateResult(List<Member> checkedMembers, List<Member> registeredMembers) {
        //TODO: aunal: bu iki listeyi kullanarak asagidaki adamlari hesapla
        // - listemde olup db de de olanlar (BURDA!) presentMembers
        // - listemde olup db de olmayanlar (kayıt edilmemiş olanlar) unregisteredMembers
        // - listemde olmayıp db de olanlar (kayitli ama derse gelmemiş) absentMembers
    }
}
