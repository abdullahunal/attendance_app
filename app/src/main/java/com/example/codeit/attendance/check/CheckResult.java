package com.example.codeit.attendance.check;

import com.example.codeit.attendance.consepts.member.Member;

import java.util.ArrayList;
import java.util.List;

public class CheckResult {

    public static final String PRESENT_NAME = "PRESENT_NAME";
    public static final String PRESENT_MAC = "PRESENT_MAC";
    public static final String ABSENT_NAME = "ABSENT_NAME";
    public static final String ABSENT_MAC = "ABSENT_MAC";
    public static final String UNREGISTERED_NAME = "UNREGISTERED_NAME";
    public static final String UNREGISTERED_MAC = "UNREGISTERED_MAC";

    /**
     * Derse gelmis kayitli ogrenciler
     */
    private List<Member> presentMembers;

    /**
     * Derse gelmis ama kayitli olmayan ogrenciler
     */
    private List<Member> unregisteredMembers;

    /**
     * Derse gelmemis ogrenciler
     */
    private List<Member> absentMembers;

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

        presentMembers = findCommonMembersWithMac(checkedMembers, registeredMembers);

        unregisteredMembers = findMissingMembersWithMac(checkedMembers, registeredMembers);

        absentMembers = findMissingMembersWithMac(registeredMembers, checkedMembers);

        //TODO: aunal: bu iki listeyi kullanarak asagidaki adamlari hesapla
        // - listemde olup db de de olanlar (BURDA!) presentMembers
        // - listemde olup db de olmayanlar (kayıt edilmemiş olanlar) unregisteredMembers
        // - listemde olmayıp db de olanlar (kayitli ama derse gelmemiş) absentMembers
    }

    private List<Member> findMissingMembersWithMac(List<Member> arrayListFirst, List<Member> arrayListSecond) {
        ArrayList<String> macListFirst = new ArrayList<>();
        ArrayList<String> macListSecond = new ArrayList<>();
        List<Member> targetList = new ArrayList<>();

        for (Member member : arrayListFirst) {
            macListFirst.add(member.getMac());
        }
        for (Member member : arrayListSecond) {
            macListSecond.add(member.getMac());
        }

        macListFirst.removeAll(macListSecond);

        for (Member member : arrayListFirst) {
            for (String mac : macListFirst) {
                if (member.getMac() == mac) {
                    targetList.add(member);
                    break;
                }
            }
        }
        return targetList;
    }

    private List<Member> findCommonMembersWithMac(List<Member> arrayListFirst, List<Member> arrayListSecond) {
        List<Member> targetList = new ArrayList<>();

        for (Member memberFirst : arrayListFirst) {
            for (Member memberSecond : arrayListSecond) {
                if (memberFirst.getMac() == memberSecond.getMac())
                    targetList.add(memberFirst);
            }
        }
        return targetList;
    }

    private List<Member> findMissingMembers(List<Member> arrayListFirst, List<Member> arrayListSecond) {
        arrayListFirst.removeAll(arrayListSecond);
        return arrayListFirst;
    }

    private List<Member> findCommonMembers(List<Member> arrayListFirst, List<Member> arrayListSecond) {
        arrayListFirst.retainAll(arrayListSecond);
        return arrayListFirst;
    }
}
