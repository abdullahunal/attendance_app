package com.example.codeit.attendance.server;

import android.bluetooth.BluetoothDevice;

import com.example.codeit.attendance.clientapi.connection.BluetoothConnectionListener;
import com.example.codeit.attendance.consepts.member.BluetoothMember;
import com.example.codeit.attendance.consepts.member.Member;
import com.example.codeit.attendance.display.MemberDisplay;

public class ConnectedDeviceService implements BluetoothConnectionListener {

    private final MemberDisplay display;

    public ConnectedDeviceService(MemberDisplay display) {
        this.display = display;
    }

    @Override
    public void bluetoothDeviceConnected(BluetoothDevice device) {
        Member member = new BluetoothMember(device);
        display.addMember(member);
    }

    @Override
    public void bluetoothDeviceDisconnected(BluetoothDevice device) {
        Member member = new BluetoothMember(device);
        display.removeMember(member);
    }
}
