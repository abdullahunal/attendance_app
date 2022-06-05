package com.example.codeit.attendance.server;

import android.widget.Button;
import android.widget.ToggleButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.codeit.attendance.MainActivity;
import com.example.codeit.attendance.check.AttendanceResultListener;
import com.example.codeit.attendance.check.AttendanceResultManager;
import com.example.codeit.attendance.check.CheckManager;
import com.example.codeit.attendance.connection.BluetoothConnection;
import com.example.codeit.attendance.connection.BluetoothConnectionListener;
import com.example.codeit.attendance.connection.ConnectedDeviceManager;
import com.example.codeit.attendance.connection.Connection;
import com.example.codeit.attendance.display.MemberDisplay;
import com.example.codeit.attendance.display.MemberDisplayImpl;
import com.example.codeit.attendance.layout.ComponentContainer;
import com.example.codeit.attendance.persistence.DBHelper;
import com.example.codeit.attendance.persistence.IDatabaseOperations;
import com.example.codeit.attendance.persistence.edit.EditDBManager;
import com.example.codeit.attendance.register.RegisterManager;

import static com.example.codeit.attendance.layout.ComponentName.*;

public class AttendanceServer extends Server {

    private final MainActivity mainActivity;

    public AttendanceServer(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void startServer() {
        ComponentContainer componentContainer = new ComponentContainer(mainActivity);

        CardView cardView = componentContainer.getComponent(CARD_VIEW);

        // Display
        RecyclerView recyclerView = componentContainer.getComponent(CONNECTED_DEVICES);
        MemberDisplay display = new MemberDisplayImpl(mainActivity, recyclerView, cardView);


        // Catch Bluetooth Connection
        BluetoothConnectionListener bluetoothConnectionListener = new BluetoothConnectionListenerImpl(display);
        ConnectedDeviceManager acceptedDeviceManager = new ConnectedDeviceManager(mainActivity, bluetoothConnectionListener);
        acceptedDeviceManager.listen();


        // Bluetooth On/Off
        Connection connection = new BluetoothConnection();
        ToggleButton connOnOffButton = componentContainer.getComponent(CONNECTION_ON_OF);
        ConnectionOnOffService connOnOffService = new ConnectionOnOffService(connection, connOnOffButton);
        connOnOffService.start();


//        // List Users
//        Button btnListUsers = componentProvider.getComponent(LIST_USERS);
//        UserListingManager listingManager = new UserListingManager(btnListUsers, bluetoothConnectionListener);


        // Persistence
//        PersistenceDAO persistenceDAO = new CachePersistenceDAO();
        IDatabaseOperations databaseOps = new DBHelper(mainActivity.getApplicationContext());


        Button btnRegister = componentContainer.getComponent(REGISTER);
        RegisterManager registerManager = new RegisterManager(btnRegister, display, databaseOps);


        AttendanceResultListener attendanceResultListener = new AttendanceResultManager(mainActivity);
        Button checkButton = componentContainer.getComponent(CHECK);
        CheckManager checkManager = new CheckManager(checkButton, display, databaseOps, attendanceResultListener);

        EditDBManager editDBManager = new EditDBManager(mainActivity);
        editDBManager.initialize();


//        StatisticManager statisticManager = new StatisticManager();
//        statisticManager.add(attendanceResultListener);
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

