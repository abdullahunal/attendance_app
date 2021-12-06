package com.example.codeit.attendancecheck.components;

import android.view.View;

import com.example.codeit.attendancecheck.MainActivity;
import com.example.codeit.attendancecheck.R;

import java.util.EnumMap;
import java.util.Objects;

/**
 * Holds layout components. Each component is type of a {@link View} instance.
 * <p>
 * A component can be a Button, TextView, Slider etc.
 */
public class ComponentProvider {

    private static ComponentProvider instance;

    private ComponentProvider() {
        //Prevent form the reflection api.
        if (instance != null) {
            throw new RuntimeException(
                    "Use getInstance() method to get the single instance of this class.");
        }
    }

    public static ComponentProvider getInstance() {
        if (instance == null) {
            synchronized (ComponentProvider.class) {
                if (instance == null) instance = new ComponentProvider();
            }
        }
        return instance;
    }

    // -------------------------------------------- -------------------------------------------- \\

    private EnumMap<ComponentName, View> components;

    public EnumMap<ComponentName, View> getComponents(MainActivity mainActivity) {
        Objects.requireNonNull(mainActivity);
        if (components == null)
            components = parseXmlLayoutToComponents(mainActivity);
        return components;
    }

    public <T extends View> T getComponent(ComponentName componentName) {
        Class<? extends View> viewClass = componentName.getViewClass();
        View view = components.get(componentName);
        return (T) view;
    }

    private EnumMap<ComponentName, View> parseXmlLayoutToComponents(MainActivity mainActivity) {

        EnumMap<ComponentName, View> views = new EnumMap<>(ComponentName.class);


        views.put(ComponentName.MAIN_LAYOUT, mainActivity.findViewById(R.id.main_layout));

        views.put(ComponentName.CONNECTED_DEVICES, mainActivity.findViewById(R.id.rvConnectedDevices));

        views.put(ComponentName.CONNECTION_ON_OF, mainActivity.findViewById(R.id.btnOnOff));

        views.put(ComponentName.LOGIN, mainActivity.findViewById(R.id.btnLogin));

        views.put(ComponentName.SIGN, mainActivity.findViewById(R.id.btnSignIn));


        return views;
    }
}
