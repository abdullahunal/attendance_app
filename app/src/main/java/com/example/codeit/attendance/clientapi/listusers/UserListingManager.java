package com.example.codeit.attendance.clientapi.listusers;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.view.View;
import android.widget.Button;

import com.example.codeit.attendance.consepts.member.Member;
import com.example.codeit.attendance.display.MemberDisplay;

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

            if (bondedDevice.getBondState()== BluetoothDevice.BOND_BONDED) {
                String name = bondedDevice.getName();
                String address = bondedDevice.getAddress();
                members.add(new Member(name, address));
            }
        }
        return members;
    }

    @Override
    public void onClick(View v) {
        for (Member member : listUsers()) {
            display.addMember(member);
        }
    }
}
