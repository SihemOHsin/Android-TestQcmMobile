package com.lti.testqcmmobilesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "testqcmdb";
    private static final String TABLE_USERS = "userdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_NAME = "name";
    private static final String KEY_PR = "prenom";
    private static final String KEY_CLASS = "classe";


    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_DATE + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_PR + " TEXT,"
                + KEY_CLASS + " TEXT" +")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void insertUserDetails(String date, String name, String prenom, String classe) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            ContentValues cValues = new ContentValues();
            cValues.put(KEY_DATE, date);
            cValues.put(KEY_NAME, name);
            cValues.put(KEY_PR, prenom);
            cValues.put(KEY_CLASS, classe);

            //cValues.put(KEY_SCORE, score);
            db.insert(TABLE_USERS, null, cValues);
        } catch (Exception e) {
            Log.e("DbHandler", "Error inserting user details", e);
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }


    public ArrayList<HashMap<String, String>> getUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        try {
            String query = "SELECT date, name, prenom, classe FROM " + TABLE_USERS;
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                HashMap<String, String> user = new HashMap<>();
                user.put("date", cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                user.put("name", cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                user.put("prenom", cursor.getString(cursor.getColumnIndex(KEY_PR)));
                user.put("classe", cursor.getString(cursor.getColumnIndex(KEY_CLASS)));
                userList.add(user);
            }
        } catch (Exception e) {
            Log.e("DbHandler", "Error getting user details", e);
        } finally {
            db.close();
        }
        return userList;
    }
}
