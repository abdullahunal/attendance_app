package com.example.codeit.attendancecheck.server;

import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.codeit.attendancecheck.MainActivity;
import com.example.codeit.attendancecheck.clientapi.connection.BluetoothConnection;
import com.example.codeit.attendancecheck.clientapi.connection.BluetoothConnectionListener;
import com.example.codeit.attendancecheck.clientapi.connection.ConnectedDeviceManager;
import com.example.codeit.attendancecheck.clientapi.connection.Connection;
import com.example.codeit.attendancecheck.clientapi.login.LoginManager;
import com.example.codeit.attendancecheck.clientapi.signin.SignInManager;
import com.example.codeit.attendancecheck.components.ComponentName;
import com.example.codeit.attendancecheck.components.ComponentProvider;
import com.example.codeit.attendancecheck.consepts.group.GroupLeader;
import com.example.codeit.attendancecheck.consepts.group.GroupManager;
import com.example.codeit.attendancecheck.consepts.member.Member;
import com.example.codeit.attendancecheck.consepts.member.MemberManager;
import com.example.codeit.attendancecheck.display.MemberDisplay;
import com.example.codeit.attendancecheck.display.MemberDisplayImpl;
import com.example.codeit.attendancecheck.persistence.DBHelper;
import com.example.codeit.attendancecheck.persistence.IDatabaseOperations;
import com.example.codeit.attendancecheck.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class AttendanceServer extends Server {

    private final MainActivity mainActivity;

    private final List<Member> successfullyMembers = new ArrayList<>();
    private final List<Member> failedMembers = new ArrayList<>();

    public AttendanceServer(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void startServer() {

        ComponentProvider componentProvider = ComponentProvider.getInstance();
        EnumMap<ComponentName, View> components = componentProvider.getComponents(mainActivity);


        RecyclerView recyclerView = componentProvider.getComponent(ComponentName.CONNECTED_DEVICES);
        MemberDisplay display = new MemberDisplayImpl(recyclerView);

        BluetoothConnectionListener connectedDeviceService = new ConnectedDeviceService(display);
        ConnectedDeviceManager connectedDeviceManager =
                new ConnectedDeviceManager(mainActivity, connectedDeviceService);
        connectedDeviceManager.initialize();


        // Bluetooth On/Off
        Connection connection = new BluetoothConnection();
        ToggleButton connOnOffButton =
                componentProvider.getComponent(ComponentName.CONNECTION_ON_OF);
        ConnectionOnOffService connOnOffService =
                new ConnectionOnOffService(connection, connOnOffButton);
        connOnOffService.start();


        // Persistence
//        PersistenceDAO persistenceDAO = new CachePersistenceDAO();
        IDatabaseOperations databaseOps = new DBHelper(mainActivity.getApplicationContext());

        Button signInButton = componentProvider.getComponent(ComponentName.SIGN);
        SignInManager signInManager = new SignInManager(signInButton, display, databaseOps);


        Button loginButton = componentProvider.getComponent(ComponentName.LOGIN);
        LoginManager loginManager = new LoginManager(loginButton, display, databaseOps);

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

