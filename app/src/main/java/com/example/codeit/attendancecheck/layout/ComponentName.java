package com.example.codeit.attendancecheck.layout;

import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

public enum ComponentName {

    MAIN_LAYOUT(View.class),

    CONNECTED_DEVICES(RecyclerView.class),

    CONNECTION_ON_OF(ToggleButton.class),

    LIST_USERS(Button.class),

    LOGIN(Button.class),

    SIGN(Button.class);


    private final Class<? extends View> viewClass;

    <T extends View> ComponentName(Class<T> viewClass) {
        this.viewClass = viewClass;
    }

    public Class<? extends View> getViewClass() {
        return viewClass;
    }
}
