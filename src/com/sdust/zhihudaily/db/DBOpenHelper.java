package com.sdust.zhihudaily.db;

import com.sdust.zhihudaily.Constants;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




public class DBOpenHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = Constants.DATABASE_VERSION;
    private static final String DB_NAME = Constants.DATABASE_NAME;

    private static DBOpenHelper sDBOpenHelper;

    private void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
        CacheDao.createTable(db, ifNotExists);
    }

   
    private void dropAllTables(SQLiteDatabase db, boolean ifExists) {
        CacheDao.dropTable(db, ifExists);
    }

    public static DBOpenHelper getInstance(Context context) {
        if (sDBOpenHelper == null) {
            synchronized (DBOpenHelper.class) {
                if (sDBOpenHelper == null) {
                    sDBOpenHelper = new DBOpenHelper(context);
                }
            }
        }
        return sDBOpenHelper;
    }

    private DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createAllTables(db, false);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropAllTables(db, true);
        onCreate(db);
    }
}

