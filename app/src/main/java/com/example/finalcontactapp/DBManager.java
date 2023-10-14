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

    public Cursor fetch(String circle) {

        String[] tablecolumns = new String[] { DatabaseHelper.ID, DatabaseHelper.NAME, DatabaseHelper.CIRCLE, DatabaseHelper.NICKNAME, DatabaseHelper.PHONENUMBER};
        String whereClause = DatabaseHelper.CIRCLE + "=?";
        String[] whereArgs= {circle};
        String orderBy = DatabaseHelper.NAME + " ASC";
        Cursor cursorVar = null;
        if(circle == null){
            cursorVar = database.query(DatabaseHelper.TABLE_NAME, tablecolumns, null, null, null, null, orderBy);

        }else{
            cursorVar = database.query(DatabaseHelper.TABLE_NAME, tablecolumns, whereClause, whereArgs, null, null, orderBy);

        }
        if (cursorVar != null) {
            cursorVar.moveToFirst();
        }

        return cursorVar;
    }

    public int getContactCount(String circle) {
        String whereClause = DatabaseHelper.CIRCLE + "=?";
        String[] whereArgs= {circle};
        Cursor cursorVar = null;

        if(circle == null){

            cursorVar = database.query(DatabaseHelper.TABLE_NAME,new String[]{"COUNT(*)"}  ,null, null, null, null, null);

        }else{
            cursorVar = database.query(DatabaseHelper.TABLE_NAME,new String[]{"COUNT(*)"}  , whereClause, whereArgs, null, null, null);

        }
        int count = 0;
        if (cursorVar != null ){
            if (cursorVar.moveToFirst()){
                count=cursorVar.getInt(0);
            }
            cursorVar.close();
        }
        return count;
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
