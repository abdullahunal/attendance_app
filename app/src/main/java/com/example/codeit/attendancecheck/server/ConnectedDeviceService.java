package com.example.codeit.attendancecheck.server;

import android.bluetooth.BluetoothDevice;

import com.example.codeit.attendancecheck.clientapi.connection.BluetoothConnectionListener;
import com.example.codeit.attendancecheck.consepts.member.BluetoothMember;
import com.example.codeit.attendancecheck.consepts.member.Member;
import com.example.codeit.attendancecheck.display.MemberDisplay;

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
