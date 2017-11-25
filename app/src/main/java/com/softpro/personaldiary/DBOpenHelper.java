package com.softpro.personaldiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 15-Jul-16.
 */
public class DBOpenHelper extends SQLiteOpenHelper {
    String create_query = "create table " + DBConstants.TABLE_NAME + "(" +
            DBConstants.ID + " integer auto increment primary key," +
            DBConstants.NAME + " text," +
            DBConstants.DOB + " text," +
            DBConstants.ADDRESS + " text," +
            DBConstants.PHONE + " text," +
            DBConstants.EMAIL + " text," +
            DBConstants.BLOODGROUP + " text," +
            DBConstants.PAN + " text," +
            DBConstants.FAX + " text," +
            DBConstants.PASSWORD + " text," +
            DBConstants.BACKGROUND + " long);";
    String create_second_query = "create table " + DBConstants.SECOND_TABLE_NAME + "(" +
            DBConstants.EMAIL + " text," +
            DBConstants.DATE + " text primary key," +
            DBConstants.CONTENT + " text);";
    String create_third_query = "create table " + DBConstants.THIRD_TABLE_NAME + "(" +
            DBConstants.EMAIL + " text," +
            DBConstants.CONTACTNAME + " text primary key," +
            DBConstants.CONTACTNUMBER + " text);";


    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_query);
        db.execSQL(create_second_query);
        db.execSQL(create_third_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists '" + DBConstants.TABLE_NAME + "'");
        db.execSQL("drop table if exists '" + DBConstants.SECOND_TABLE_NAME + "'");
        db.execSQL("drop table if exists '" + DBConstants.THIRD_TABLE_NAME + "'");
        db.execSQL(create_second_query);
        db.execSQL(create_query);
        db.execSQL(create_third_query);
    }
}
