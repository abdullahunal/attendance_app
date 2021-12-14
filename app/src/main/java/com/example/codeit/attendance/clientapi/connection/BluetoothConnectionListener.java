package com.example.codeit.attendance.clientapi.connection;

import android.bluetooth.BluetoothDevice;

public interface BluetoothConnectionListener {

    void bluetoothDeviceConnected(BluetoothDevice bluetoothDevice);

    void bluetoothDeviceDisconnected(BluetoothDevice bluetoothDevice);
}