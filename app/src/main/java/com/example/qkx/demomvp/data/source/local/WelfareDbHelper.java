package com.example.qkx.demomvp.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by qkx on 16/5/4.
 */
public class WelfareDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "welfare.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + WelfarePersistenceContact.WelfareEntry.TABLE_NAME + " (" +
                    WelfarePersistenceContact.WelfareEntry._ID + TEXT_TYPE + " PRIMARY KEY" + COMMA_SEP +
                    WelfarePersistenceContact.WelfareEntry.COLUMN_NAME_PIC_URL + TEXT_TYPE + COMMA_SEP +
                    WelfarePersistenceContact.WelfareEntry.COLIMN_NAME_USERNAME + TEXT_TYPE +
                    " )";

    public WelfareDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
