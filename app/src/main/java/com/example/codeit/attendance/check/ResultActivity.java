package com.example.codeit.attendance.check;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codeit.attendance.R;
import com.example.codeit.attendance.consepts.member.Member;
import com.example.codeit.attendance.display.ICustomItemClickListener;
import com.example.codeit.attendance.display.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_checked_members);

        RecyclerView recyclerViewPresent = findViewById(R.id.recycler_view_check_present);
        recyclerViewPresent.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPresent.setHasFixedSize(true);

        RecyclerView recyclerViewAbsent = findViewById(R.id.recycler_view_check_absent);
        recyclerViewAbsent.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAbsent.setHasFixedSize(true);

        RecyclerView recyclerViewUnregistered = findViewById(R.id.recycler_view_check_unregistered);
        recyclerViewUnregistered.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewUnregistered.setHasFixedSize(true);

        Intent intent = getIntent();

       /* int boyutAbsent = intent.getIntExtra("boyutAbsent", 5);
        int boyutPresent = intent.getIntExtra("boyutPresent",5);
        int boyutUnregistered = intent.getIntExtra("boyutUnregistered",5);*/
/*        String name = intent.getStringExtra("keyName1");
        String mac = intent.getStringExtra("keyMac1");
        String name2 = intent.getStringExtra("keyName2");
        String mac2 = intent.getStringExtra("keyMac2");
        System.out.println("name: " + name + "   mac: " + mac);
        System.out.println("name2: " + name2 + "   mac2: " + mac2 + " boyut : " + boyut);*/

        List<Member> presentMembers = new ArrayList<>();
        Object[] presentObj = (Object[]) intent.getExtras().get("present");
        for (Object object : presentObj) {
            presentMembers.add((Member) object);
        }

        List<Member> absentMembers = new ArrayList<>();
        Object[] absentObj = (Object[]) intent.getExtras().get("absent");
        for (Object object : absentObj) {
            absentMembers.add((Member) object);
        }

        List<Member> unregisteredMembers = new ArrayList<>();
        Object[] unregisteredObj = (Object[]) intent.getExtras().get("unregistered");
        for (Object object : unregisteredObj) {
            unregisteredMembers.add((Member) object);
        }


        /*for (int i = 0; i < boyutPresent; i++) {
            presentMembers.add(new Member(intent.getStringExtra("keyNamePresent" + (i + 1)), intent.getStringExtra("keyMacPresent" + (i + 1))));
        }

        for (int i = 0; i < boyutAbsent; i++) {
            absentMembers.add(new Member(intent.getStringExtra("keyMac" + (i + 1)), intent.getStringExtra("keyName" + (i + 1))));
           // System.out.println("ifade: " + intent.getStringExtra("keyMac" + (i + 1)) + "    " + intent.getStringExtra("keyName" + (i + 1)));
        }

        for (int i = 0; i < boyutUnregistered; i++) {
            unregisteredMembers.add(new Member(intent.getStringExtra("keyMacUnregistered" + (i + 1)), intent.getStringExtra("keyNameUnregistered" + (i + 1))));
            // System.out.println("ifade: " + intent.getStringExtra("keyMac" + (i + 1)) + "    " + intent.getStringExtra("keyName" + (i + 1)));
        }*/

        // absentMembers.add(new Member("1231231", data));


        // 1
        // int listeBoyutu = intent.getIntExtra("listeboyutu", 10);

        /*for (int i = 0; i < 1; i++) {
            presentMembersName.add(intent.getStringExtra("keyName" + i + 1));
        }

        for (int i = 0; i < 1; i++) {
            presentMembersMac.add(intent.getStringExtra("keyMac" + i + 1));
        }

        for (int i = 0; i < 1; i++) {
            presentMembers.add(new Member(presentMembersName.get(i), presentMembersMac.get(i)));
        }*/

        // 2
        /*presentMembers.add(new Member(presentMembersName.get(0), "123456"));
        presentMembers.add(new Member(presentMembersName.get(1), "4685489"));*/

        /*for (int i = 0; i < intent.getExtras().size(); i++) {
            presentMembers.add(i,intent.getParcelableExtra(keyGenerator.getKey()));
        }*/

        //3
        //String data2 = intent.getStringExtra("key2");
        //String data3 = intent.getStringExtra("key3");

        //presentMembers.add(new Member(data,"asd123"));
        //presentMembers.add(new Member(data2,"as13123d"));
        //presentMembers.add(new Member(data3,"as12312d"));

        // 4
        // dizi dolmuyor
        //Member[] name = intent.getStringArrayExtra(CheckResult.PRESENT_NAME);
        //Member[] mac = intent.getStringArrayExtra(CheckResult.PRESENT_MAC);

        /*for (int i = 0; i < name.length; i++) {
            presentMembers.add(new Member(name[i], mac[i]));
        }*/

        setRecyclerView(recyclerViewPresent, presentMembers);
        setRecyclerView(recyclerViewAbsent, absentMembers);
        setRecyclerView(recyclerViewUnregistered, unregisteredMembers);
    }

    private void setRecyclerView(RecyclerView recyclerView, List<Member> members) {
        RecyclerViewAdapter adapter_items = new RecyclerViewAdapter(members, new ICustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        });

        if (members.size() > 0) { // presentMembers.size() > 0
            try {
                recyclerView.setAdapter(adapter_items);
            } catch (Exception e) {
                Toast.makeText(this, "Exception fırlatıldı", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Liste boş", Toast.LENGTH_SHORT).show();
        }
    }
}

