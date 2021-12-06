package com.example.codeit.attendancecheck.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper implements IDatabaseOperations {

    // versiyon
    private static final int DATABASE_VERSION = 1;

    // veritabanı ismi
    private static final String DATABASE_NAME = "user_database";

    // tablo adı
    private static final String TABLE_NAME = "USER";

    // tablo sütunları
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String MAC = "mac";
    private SQLiteDatabase sqLiteDatabase;

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT NOT NULL," + MAC + " TEXT NOT NULL);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // ekle

    public void addUser(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.NAME, user.getName());
        contentValues.put(DBHelper.MAC, user.getMac());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DBHelper.TABLE_NAME, null, contentValues);
    }

    public List<User> getUserList() {
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<User> userList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String mac = cursor.getString(2);
                userList.add(new User(id, name, mac));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return userList;
    }

    public boolean updateUser(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.NAME, user.getName());
        contentValues.put(DBHelper.MAC, user.getMac());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME, contentValues, ID + " = ?", new String[]
                {String.valueOf(user.getId())});
        return true;
    }

    public boolean deleteUser(int id) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ?", new String[]
                {String.valueOf(id)});
        return true;
    }

}