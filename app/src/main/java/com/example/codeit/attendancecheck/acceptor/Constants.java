package com.example.codeit.attendancecheck.acceptor;

public class Constants {
    // Message types sent from the BluetoothChatService Handler
    static int MESSAGE_STATE_CHANGE = 1;
    static int MESSAGE_READ = 2;
    static int MESSAGE_WRITE = 3;
    static int MESSAGE_DEVICE_NAME = 4;
    static int MESSAGE_TOAST = 5;

    // Key names received from the BluetoothChatService Handler
   static String DEVICE_NAME = "device_name";
   static String TOAST = "toast";
}
