package com.example.codeit.attendancecheck.consepts.member;

import android.bluetooth.BluetoothDevice;

import java.util.ArrayList;
import java.util.List;

public class BluetoothMember extends Member {

    private String groupId;

    private List<String> attributes;

    public BluetoothMember(BluetoothDevice bluetoothDevice) {
        super(bluetoothDevice.getAddress(), bluetoothDevice.getName());
    }

    public BluetoothMember(String deviceName, String mac) {
        super(deviceName, mac);
    }
}
