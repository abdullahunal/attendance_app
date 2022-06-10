package com.example.codeit.attendance.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.codeit.attendance.consepts.member.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void create(Member member) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.NAME, member.getName());
        contentValues.put(DBHelper.MAC, member.getMac());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DBHelper.TABLE_NAME, null, contentValues);
    }

    public List<Member> readAll() {
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<Member> memberList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String mac = cursor.getString(2);
                memberList.add(new Member(id, name, mac));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return memberList;
    }

    public boolean updateUser(Member member) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.NAME, member.getName());
        contentValues.put(DBHelper.MAC, member.getMac());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME, contentValues, ID + " = ?", new String[]
                {String.valueOf(member.getId())});
        return true;
    }

    public boolean deleteUser(int id) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ?", new String[]
                {String.valueOf(id)});
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Optional<Member> readByAddress(String mac) {
        String sql = "select * from " + TABLE_NAME + " where " + MAC + " = " + "\"" + mac + "\"";
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        List<Member> memberList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String mac2 = cursor.getString(2);
                memberList.add(new Member(id, name, mac2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        if (memberList.size() == 1) {
            return Optional.of(memberList.get(0));
        } else {
            return Optional.empty();
        }
    }
}