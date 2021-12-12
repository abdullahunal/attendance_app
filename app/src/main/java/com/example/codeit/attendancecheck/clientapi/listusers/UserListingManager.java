package com.example.codeit.attendancecheck.clientapi.listusers;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.view.View;
import android.widget.Button;

import com.example.codeit.attendancecheck.consepts.member.Member;
import com.example.codeit.attendancecheck.display.MemberDisplay;

import java.util.ArrayList;
import java.util.List;

public class UserListingManager implements View.OnClickListener {

    private final Button btnListUsers;

    private final BluetoothAdapter bluetoothAdapter;

    private final MemberDisplay display;

    public UserListingManager(Button btnListUsers,
            BluetoothAdapter bluetoothAdapter,
            MemberDisplay display) {
        this.btnListUsers = btnListUsers;
        this.bluetoothAdapter = bluetoothAdapter;
        this.display = display;
        btnListUsers.setOnClickListener(this);
    }

    public List<Member> listUsers() {
        List<Member> members = new ArrayList<>();
        for (BluetoothDevice bondedDevice : bluetoothAdapter.getBondedDevices()) {
            String name = bondedDevice.getName();
            String address = bondedDevice.getAddress();
            members.add(new Member(name, address));
        }
        members.add(new Member("Hüseyin", "işlşilişliş"));
        members.add(new Member("Abdullah", "aaaaaaaaa"));
        members.add(new Member("Hamza", "aslidjaslik"));
        members.add(new Member("Muhamemd", "ououououou"));
        return members;
    }

    @Override
    public void onClick(View v) {
        for (Member member : listUsers()) {
            display.addMember(member);
        }
    }
}
