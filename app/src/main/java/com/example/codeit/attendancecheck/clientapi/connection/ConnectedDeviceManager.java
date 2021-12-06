package com.example.codeit.attendancecheck.clientapi.connection;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.example.codeit.attendancecheck.MainActivity;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class ConnectedDeviceManager {



    // Debugging
    private static final String TAG = "BluetoothChatService";

    // Name for the SDP record when creating server socket
    private static final String NAME_SECURE = "BluetoothChatSecure";
    private static final String NAME_INSECURE = "BluetoothChatInsecure";

    // Unique UUID for this application
    private static final UUID MY_UUID_SECURE =
            UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");
    private static final UUID MY_UUID_INSECURE =
            UUID.fromString("8ce255c0-200a-11e0-ac64-0800200c9a66");




    private final MainActivity mainActivity;

    private final BluetoothConnectionListener connectionListener;

    public ConnectedDeviceManager(MainActivity mainActivity,
            BluetoothConnectionListener connectionListener) {
        this.mainActivity = mainActivity;
        this.connectionListener = connectionListener;
    }

    public void initialize() {
        if(1==1) return;
        BroadcastReceiver receiver = new BtReceiver();
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        mainActivity.registerReceiver(receiver, filter);

        int requestCode = 1;
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        mainActivity.startActivityForResult(discoverableIntent, requestCode);
        try {
            extracted();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    // Don't forget to unregister the ACTION_FOUND receiver.
//    unregisterReceiver(receiver);

    private class BtReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Set<BluetoothDevice> bondedDevices =
                    BluetoothAdapter.getDefaultAdapter().getBondedDevices();

            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                connectionListener.bluetoothDeviceConnected(device);
            }
        }
    }



    private void extracted() throws IOException {
        BluetoothServerSocket mmServerSocket =
                BluetoothAdapter.getDefaultAdapter()
                        .listenUsingRfcommWithServiceRecord(NAME_SECURE, MY_UUID_SECURE);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                BluetoothSocket socket = null;
                // Keep listening until exception occurs or a socket is returned.
                while (true) {
                    try {
                        socket = mmServerSocket.accept();
                    } catch (IOException e) {
                        Log.e("TAG", "Socket's accept() method failed", e);
                        break;
                    }

                    if (socket != null) {
                        // A connection was accepted. Perform work associated with
                        // the connection in a separate thread.
                        try {
                            manageMyConnectedSocket(socket);
                            mmServerSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }

            private void manageMyConnectedSocket(BluetoothSocket socket) throws IOException {
                if (!socket.isConnected()) {
                    socket.connect();
                }
                BluetoothDevice remoteDevice = socket.getRemoteDevice();

                System.out.println();
            }
        };

        runnable.run();
    }
}
