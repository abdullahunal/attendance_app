package com.example.codeit.attendancecheck.clientapi.connection;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

public class BluetoothConnection implements Connection {

    private final BluetoothAdapter adapter;

    // TODO: data transmit asamasinda lazim olacak.
    private BluetoothServerSocket bluetoothServerSocket;
    private BluetoothSocket bluetoothSocket;

    public BluetoothConnection() {
        this.adapter = BluetoothAdapter.getDefaultAdapter();
    }

    public BluetoothConnection(BluetoothAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void connect() {
        enableBluetooth();
    }

    @Override
    public void disconnect() {
        disableBluetooth();
    }

    private void enableBluetooth() {
        if (!adapter.isEnabled()) {
            adapter.enable();
        }
    }

    private void disableBluetooth() {
        if (adapter.isEnabled()) {
            adapter.disable();
        }
    }
}
