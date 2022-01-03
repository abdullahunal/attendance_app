package com.example.codeit.attendance.listusers;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.view.View;
import android.widget.Button;

import com.example.codeit.attendance.connection.BluetoothConnectionListener;

@Deprecated
public class UserListingManager implements View.OnClickListener {

    private final Button btnListUsers;

    private final BluetoothAdapter bluetoothAdapter;

    private final BluetoothConnectionListener bluetoothConnectionListener;

    public UserListingManager(Button btnListUsers,
            BluetoothConnectionListener bluetoothConnectionListener) {
        this.btnListUsers = btnListUsers;
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.bluetoothConnectionListener = bluetoothConnectionListener;
        btnListUsers.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        for (BluetoothDevice bondedDevice : bluetoothAdapter.getBondedDevices()) {
            if (bondedDevice.getBondState() == BluetoothDevice.BOND_BONDED) {
                bluetoothConnectionListener.bluetoothDeviceConnected(bondedDevice);
            }
        }
    }
}
