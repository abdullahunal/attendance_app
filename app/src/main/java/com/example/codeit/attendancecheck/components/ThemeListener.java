package com.example.codeit.attendancecheck.components;

import android.widget.CompoundButton;

import androidx.appcompat.widget.SwitchCompat;

public class ThemeListener implements SwitchCompat.OnCheckedChangeListener {

    private final SwitchCompat themeSwitch;

    private final ThemeManager themeManager;

    public ThemeListener(SwitchCompat themeSwitch, ThemeManager themeManager) {
        this.themeSwitch = themeSwitch;
        this.themeManager = themeManager;
    }

    public void listen() {
        themeSwitch.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        themeManager.switchTheme();
    }
}