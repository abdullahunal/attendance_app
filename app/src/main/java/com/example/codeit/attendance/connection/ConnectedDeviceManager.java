package com.example.codeit.attendance.connection;

import static android.bluetooth.BluetoothDevice.ACTION_ACL_CONNECTED;
import static android.bluetooth.BluetoothDevice.ACTION_ACL_DISCONNECTED;
import static android.bluetooth.BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.codeit.attendance.MainActivity;

public class ConnectedDeviceManager {

    private final MainActivity mainActivity;

    private final BluetoothConnectionListener connectionListener;

    public ConnectedDeviceManager(MainActivity mainActivity,
            BluetoothConnectionListener connectionListener) {
        this.mainActivity = mainActivity;
        this.connectionListener = connectionListener;
    }

    public void listen() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_ACL_CONNECTED);
        filter.addAction(ACTION_ACL_DISCONNECT_REQUESTED);
        filter.addAction(ACTION_ACL_DISCONNECTED);
        mainActivity.registerReceiver(mReceiver, filter);
    }

    //The BroadcastReceiver that listens for bluetooth broadcasts
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothDevice connectedDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            if (ACTION_ACL_CONNECTED.equals(action)){
                connectionListener.bluetoothDeviceConnected(connectedDevice);
                // TODO: aunal: baglanti yakalandi, burada disconnect yapilacak!
            }

//            switch (action) {
//                case ACTION_FOUND: //Device found
//                    break;
//                case ACTION_ACL_CONNECTED: //Device is now connected
//                    connectionListener.bluetoothDeviceConnected(connectedDevice);
//                    break;
//                case ACTION_DISCOVERY_FINISHED: //Done searching
//                    break;
//                case ACTION_ACL_DISCONNECT_REQUESTED: //Device is about to disconnect
//                    break;
//                case ACTION_ACL_DISCONNECTED: //Device has disconnected
//                    break;
//            }
        }
    };
}
