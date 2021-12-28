package com.example.codeit.attendance.display;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codeit.attendance.MainActivity;
import com.example.codeit.attendance.consepts.member.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberDisplayImpl implements MemberDisplay {

    private final List<Member> memberList;
    private final RecyclerView recycler_view;
    private final CardView cardView;
    private final MainActivity mainActivity;

    public MemberDisplayImpl(MainActivity mainActivity, RecyclerView recycler_view, CardView cardView) {
        this.recycler_view = recycler_view;
        this.cardView = cardView;
        this.mainActivity = mainActivity;

        LinearLayoutManager layoutManager = new LinearLayoutManager(mainActivity.getApplicationContext());

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);

        recycler_view.setLayoutManager(layoutManager);

        memberList = new ArrayList<Member>();

        RecyclerViewAdapter adapter_items = new RecyclerViewAdapter(memberList, new ICustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d("position", "Tıklanan Pozisyon:" + position);
                Member member = memberList.get(position);
                Toast.makeText(mainActivity.getApplicationContext(), "pozisyon:" + " " + position + " " + "Ad:" + member.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        recycler_view.setHasFixedSize(true);

        recycler_view.setAdapter(adapter_items);

        recycler_view.setItemAnimator(new DefaultItemAnimator());
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
        recycler_view.removeAllViews();
        RecyclerViewAdapter adapter_items = new RecyclerViewAdapter(memberList, new ICustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d("position", "Tıklanan Pozisyon:" + position);
                Member member = memberList.get(position);
                Toast.makeText(mainActivity.getApplicationContext(), "pozisyon:" + " " + position + " " + "Ad:" + member.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        recycler_view.setAdapter(adapter_items);

        /*for (Member member : memberList) {
            CardView view = parseToDisplayItem(member);
            recycler_view.addView(view);
        }*/
    }

    private CardView parseToDisplayItem(Member member) {


        // cardView. setText("Name: " + member.getName() + "MAC: " + member.getMac());
        return cardView;
    }


    private void addToDisplay(Member member) {
        TextView textView = new TextView(recycler_view.getContext());
        textView.setText("Name: " + member.getName() + "MAC: " + member.getId());
        recycler_view.addView(textView);
    }

    private void removeFromDisplay(Member member) {
        TextView textView = new TextView(recycler_view.getContext());
        textView.setText("Name: " + member.getName() + "MAC: " + member.getId());
        recycler_view.removeView(textView);
    }
}
