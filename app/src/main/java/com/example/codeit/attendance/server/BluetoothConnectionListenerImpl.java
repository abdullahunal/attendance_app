package com.example.codeit.attendance.server;

import android.bluetooth.BluetoothDevice;

import com.example.codeit.attendance.connection.BluetoothConnectionListener;
import com.example.codeit.attendance.consepts.member.BluetoothMember;
import com.example.codeit.attendance.consepts.member.Member;
import com.example.codeit.attendance.display.MemberDisplay;

import java.util.logging.Logger;

public class BluetoothConnectionListenerImpl implements BluetoothConnectionListener {

    private final Logger LOG = Logger.getLogger("BluetoothConnectionListenerImpl");

    private final MemberDisplay display;

    public BluetoothConnectionListenerImpl(MemberDisplay display) {
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
        LOG.info("Device connection has closed. Member name: " + member.getName());
    }
}
