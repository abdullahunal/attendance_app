package com.example.codeit.attendancecheck.display;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.codeit.attendancecheck.consepts.member.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberDisplayImpl implements MemberDisplay {

    private final List<Member> memberList;

    private final RecyclerView recyclerView;

    public MemberDisplayImpl(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.memberList = new ArrayList<>();
    }

    @Override
    public List<Member> getMembers() {
        return memberList;
    }

    @Override
    public void addMember(Member member) {
        memberList.add(member);
        updateDisplay();

    }

    @Override
    public void removeMember(Member member) {
        memberList.remove(member);
        updateDisplay();
    }

    private synchronized void updateDisplay() {
        recyclerView.removeAllViews();
        for (Member member : memberList) {
            View view = parseToDisplayItem(member);
            recyclerView.addView(view);
        }
    }

    private View parseToDisplayItem(Member member) {
        TextView textView = new TextView(recyclerView.getContext());
        textView.setText("Name: " + member.getName() + "MAC: " + member.getId());
        return textView;
    }


    private void addToDisplay(Member member) {
        TextView textView = new TextView(recyclerView.getContext());
        textView.setText("Name: " + member.getName() + "MAC: " + member.getId());
        recyclerView.addView(textView);
    }

    private void removeFromDisplay(Member member) {
        TextView textView = new TextView(recyclerView.getContext());
        textView.setText("Name: " + member.getName() + "MAC: " + member.getId());
        recyclerView.removeView(textView);
    }
}
