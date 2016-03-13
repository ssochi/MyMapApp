package com.example.administrator.mymapapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by Administrator on 2016/3/13.
 */
public class SQLitedb extends SQLiteOpenHelper {
    public SQLitedb(Context context) {
        super(context, "mSQLitedb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        List<String> List = BulidingGroup.getBulidingGroup();
        for(int i = 0;i<List.size();i++){
            String name = List.get(i);
            db.execSQL("CREATE TABLE " + name + "(Content TEXT DEFAULT \"\")");
            System.out.println("CREATE TABLE " + name + "(Content TEXT DEFAULT \"\")");
            db.execSQL("CREATE TABLE " + name + "History(Content TEXT DEFAULT \"\")");
            System.out.println("CREATE TABLE " + name + "History(Content TEXT DEFAULT \"\")");
            db.execSQL("CREATE TABLE " + name + "Job(Content TEXT DEFAULT \"\")");
            System.out.println("CREATE TABLE " + name + "Job(Content TEXT DEFAULT \"\")");
            db.execSQL("CREATE TABLE " + name + "Activity(Content TEXT DEFAULT \"\")");
            System.out.println("CREATE TABLE " + name + "Activity(Content TEXT DEFAULT \"\")");
            db.execSQL("CREATE TABLE " + name + "Bouns(Content TEXT DEFAULT \"\")");
            System.out.println("CREATE TABLE " + name + "Bouns(Content TEXT DEFAULT \"\")");
            db.execSQL("CREATE TABLE " + name + "treeHoles(Content TEXT DEFAULT \"\")");
            System.out.println("CREATE TABLE " + name + "treeHoles(Content TEXT DEFAULT \"\")");
        }



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
