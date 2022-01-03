package com.example.codeit.attendance.server;

import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.example.codeit.attendance.connection.Connection;

public class ConnectionOnOffService implements CompoundButton.OnCheckedChangeListener {

    private final Connection connection;

    private final ToggleButton connOnOffButton;

    public ConnectionOnOffService(Connection connection, ToggleButton connOnOffButton) {
        this.connection = connection;
        this.connOnOffButton = connOnOffButton;
    }

    public void start() {
        this.connOnOffButton.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            connection.connect();
        } else {
            connection.disconnect();
        }
    }
}
