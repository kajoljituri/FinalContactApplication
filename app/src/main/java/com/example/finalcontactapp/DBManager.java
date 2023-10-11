package com.example.finalcontactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(Contact c) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME,c.getName());
        contentValue.put(DatabaseHelper.PHONENUMBER, c.getPhoneNumber());
        contentValue.put(DatabaseHelper.CIRCLE, c.getCircle());
        contentValue.put(DatabaseHelper.NICKNAME, c.getnickname());
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] tablecolumns = new String[] { DatabaseHelper.ID, DatabaseHelper.NAME, DatabaseHelper.CIRCLE, DatabaseHelper.NICKNAME, DatabaseHelper.PHONENUMBER};
        Cursor cursorVar = database.query(DatabaseHelper.TABLE_NAME, tablecolumns, null, null, null, null, null);
        if (cursorVar != null) {
            cursorVar.moveToFirst();
        }

        return cursorVar;
    }

    public Cursor fetch1() {
        String[] tablecolumns = new String[] { DatabaseHelper.ID, DatabaseHelper.NAME, DatabaseHelper.CIRCLE, DatabaseHelper.NICKNAME, DatabaseHelper.PHONENUMBER};
        Cursor cursorVar = database.query(DatabaseHelper.TABLE_NAME, tablecolumns, null, null, null, null, null);
        if (cursorVar != null) {
            cursorVar.moveToFirst();
        }

        return cursorVar;
    }

    public int updateTable(long id, String name, String phnum,String nickname,String circle) {
        ContentValues contentValuesVar = new ContentValues();
        contentValuesVar.put(DatabaseHelper.NAME, name);
        contentValuesVar.put(DatabaseHelper.PHONENUMBER, phnum);
        contentValuesVar.put(DatabaseHelper.NICKNAME, nickname);
        contentValuesVar.put(DatabaseHelper.CIRCLE, circle);
        contentValuesVar.put(DatabaseHelper.ID,id);

        int i = database.update(DatabaseHelper.TABLE_NAME, contentValuesVar, DatabaseHelper.ID + " = " + id, null);
        return i;
    }

    public void delete(long id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.ID + "=" + id, null);
    }

}
