package com.example.codeit.attendance.layout;

import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

public enum ComponentName {

    MAIN_LAYOUT(View.class),

    CONNECTED_DEVICES(RecyclerView.class),

    LIST_USERS(Button.class),

    CONNECTION_ON_OF(ToggleButton.class),

    CHECK(Button.class),

    REGISTER(Button.class);


    private final Class<? extends View> viewClass;

    <T extends View> ComponentName(Class<T> viewClass) {
        this.viewClass = viewClass;
    }

    public Class<? extends View> getViewClass() {
        return viewClass;
    }
}
