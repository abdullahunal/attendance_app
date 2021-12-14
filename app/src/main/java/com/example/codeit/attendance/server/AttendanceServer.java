package com.example.codeit.attendance.server;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.codeit.attendance.MainActivity;
import com.example.codeit.attendance.clientapi.connection.BluetoothConnection;
import com.example.codeit.attendance.clientapi.connection.BluetoothConnectionListener;
import com.example.codeit.attendance.clientapi.connection.ConnectedDeviceManager;
import com.example.codeit.attendance.clientapi.connection.Connection;
import com.example.codeit.attendance.clientapi.listusers.UserListingManager;
import com.example.codeit.attendance.clientapi.login.CheckManager;
import com.example.codeit.attendance.clientapi.signin.RegisterManager;
import com.example.codeit.attendance.display.MemberDisplay;
import com.example.codeit.attendance.display.MemberDisplayImpl;
import com.example.codeit.attendance.layout.ComponentName;
import com.example.codeit.attendance.layout.ComponentProvider;
import com.example.codeit.attendance.persistence.DBHelper;
import com.example.codeit.attendance.persistence.IDatabaseOperations;

import java.util.EnumMap;

public class AttendanceServer extends Server {

    private final MainActivity mainActivity;

    public AttendanceServer(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void startServer() {

        ComponentProvider componentProvider = ComponentProvider.getInstance();
        EnumMap<ComponentName, View> components = componentProvider.getComponents(mainActivity);

        Context context = mainActivity.getApplicationContext();

        // Display
        RecyclerView recyclerView = componentProvider.getComponent(ComponentName.CONNECTED_DEVICES);
        MemberDisplay display = new MemberDisplayImpl(mainActivity,recyclerView);


        BluetoothConnectionListener connectedDeviceService = new ConnectedDeviceService(display);
        ConnectedDeviceManager connectedDeviceManager = new ConnectedDeviceManager(mainActivity, connectedDeviceService);
        connectedDeviceManager.initialize();


        // Bluetooth On/Off
        Connection connection = new BluetoothConnection();
        ToggleButton connOnOffButton = componentProvider.getComponent(ComponentName.CONNECTION_ON_OF);
        ConnectionOnOffService connOnOffService = new ConnectionOnOffService(connection, connOnOffButton);
        connOnOffService.start();


        // List Users
        Button btnListUsers = componentProvider.getComponent(ComponentName.LIST_USERS);
        UserListingManager listingManager =
                new UserListingManager(btnListUsers, BluetoothAdapter.getDefaultAdapter(), display);


        // Persistence
//        PersistenceDAO persistenceDAO = new CachePersistenceDAO();
        IDatabaseOperations databaseOps = new DBHelper(context);

        Button btnRegister = componentProvider.getComponent(ComponentName.REGISTER);
        RegisterManager registerManager = new RegisterManager(btnRegister, display, databaseOps);


        Button loginButton = componentProvider.getComponent(ComponentName.CHECK);
        CheckManager checkManager = new CheckManager(loginButton, display, databaseOps);

    }


    @Override
    public void stopServer() {

    }
}

// switch olacak, signin modu ve login modu arasında gidip gelmeyi sağlayacak
// sign in modunda iken bağlanan cihazları listeleyeck, sonra hoca tek tek veya tümünü seçerek onları db ye kaydedecek.
// isterse hiç seçmeden direk kaydedilsin de diyebilir.
// sonra login moduna geçer
// artık bağlanan kullanıcılar db den kontrol edilir ve yeşil ya da kırmızı listeye düşerler.
// yeşil liste db de olanlar,
// kırmızı liste db de olmayan ama şu an bağlanmış olanlar.
// kod tasarımı düzenlenecek, servis içinde onClick falan var, düzenlenecek.

// dosya ve data paylaşımı olacak.
// istatistik tutulacak.
// dersler group olarak tanımlanacak.
// db düzenleme için arayüz olacak, yani şunu sil, şunu güncelle vs.

