package com.example.codeit.attendance.layout;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.codeit.attendance.R;

import java.util.EnumMap;

/**
 * Holds layout components. Each component is type of a {@link View} instance.
 * <p>
 * A component can be a Button, TextView, Slider etc.
 */
public class ComponentContainer {

    private final EnumMap<ComponentName, View> components;

    public ComponentContainer(AppCompatActivity mainActivity) {
        this.components = parseXmlLayoutToComponents(mainActivity);
    }

    public <T extends View> T getComponent(ComponentName componentName) {
        Class<? extends View> viewClass = componentName.getViewClass();
        View view = components.get(componentName);
        return (T) view;
    }

    private EnumMap<ComponentName, View> parseXmlLayoutToComponents(AppCompatActivity mainActivity) {

        EnumMap<ComponentName, View> views = new EnumMap<>(ComponentName.class);

        views.put(ComponentName.MAIN_LAYOUT, mainActivity.findViewById(R.id.main_layout));

        views.put(ComponentName.CONNECTED_DEVICES, mainActivity.findViewById(R.id.recycler_view));

        views.put(ComponentName.LIST_USERS, mainActivity.findViewById(R.id.btnListUsers));

        views.put(ComponentName.CONNECTION_ON_OF, mainActivity.findViewById(R.id.btnOnOff));

        views.put(ComponentName.CHECK, mainActivity.findViewById(R.id.btnCheck));

        views.put(ComponentName.REGISTER, mainActivity.findViewById(R.id.btnRegister));

        views.put(ComponentName.CARD_VIEW, mainActivity.findViewById(R.id.card_view));

        return views;
    }
}
