package com.example.codeit.attendance.connection;

import android.bluetooth.BluetoothDevice;

public interface BluetoothConnectionListener {

    void bluetoothDeviceConnected(BluetoothDevice bluetoothDevice);

    void bluetoothDeviceDisconnected(BluetoothDevice bluetoothDevice);
}
