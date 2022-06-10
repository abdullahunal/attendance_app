package com.example.codeit.attendance.consepts.member;

import android.bluetooth.BluetoothDevice;

import java.util.List;

public class BluetoothMember extends Member {

    private String groupId;

    private List<String> attributes;

    public BluetoothMember(BluetoothDevice bluetoothDevice) {
        super(bluetoothDevice.getName(), bluetoothDevice.getAddress());
    }

    public BluetoothMember(String deviceName, String mac) {
        super(deviceName, mac);
    }
}
