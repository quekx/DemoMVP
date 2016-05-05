package com.example.qkx.demomvp.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.qkx.demomvp.data.Welfare;
import com.example.qkx.demomvp.data.source.WelfareDataSource;

/**
 * Created by qkx on 16/4/28.
 */
public class WelfareLocalDataSource implements WelfareDataSource {
    private static volatile WelfareLocalDataSource INSTANCE;

    private Context mContext;

    private WelfareDbHelper mDbHelper;

    private WelfareLocalDataSource(Context ctx) {
        this.mContext = ctx;
        mDbHelper = new WelfareDbHelper(ctx);
    }

    public static WelfareLocalDataSource getInstance(Context ctx) {
        if (null == INSTANCE) {
            synchronized (WelfareLocalDataSource.class) {
                if (null == INSTANCE) {
                    INSTANCE = new WelfareLocalDataSource(ctx);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void queryHome(GetCallback callback) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                WelfarePersistenceContact.WelfareEntry.COLUMN_NAME_PIC_URL,
                WelfarePersistenceContact.WelfareEntry.COLIMN_NAME_USERNAME
        };

        Cursor cursor = db.query(WelfarePersistenceContact.WelfareEntry.TABLE_NAME,
                projection, null, null, null, null, null);

        Welfare welfare = null;

        if (null != cursor && cursor.moveToFirst()) {
            welfare = new Welfare();
            do {
                String url = cursor.getString(cursor.getColumnIndexOrThrow(WelfarePersistenceContact.WelfareEntry.COLUMN_NAME_PIC_URL));
                String username = cursor.getString(cursor.getColumnIndexOrThrow(WelfarePersistenceContact.WelfareEntry.COLIMN_NAME_USERNAME));

                welfare.addResult(url, username);
            } while (cursor.moveToNext());
        }
        if (null != cursor) {
            cursor.close();
        }

        db.close();

        if (null != welfare) {
            callback.onWelfareGet(welfare);
        } else {
            callback.onDataNotAvailable();
        }
    }

    public void updateWelfare(Welfare welfare) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // clear data saved before
        db.delete(WelfarePersistenceContact.WelfareEntry.TABLE_NAME, null, null);

        for (Welfare.Result r : welfare.results) {
            ContentValues cv = new ContentValues();
            cv.put(WelfarePersistenceContact.WelfareEntry.COLUMN_NAME_PIC_URL, r.url);
            cv.put(WelfarePersistenceContact.WelfareEntry.COLIMN_NAME_USERNAME, r.who);

            db.insert(WelfarePersistenceContact.WelfareEntry.TABLE_NAME, null, cv);
        }
        db.close();
    }

    public void saveWelfare(Welfare welfare) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        for (Welfare.Result r : welfare.results) {
            ContentValues cv = new ContentValues();
            cv.put(WelfarePersistenceContact.WelfareEntry.COLUMN_NAME_PIC_URL, r.url);
            cv.put(WelfarePersistenceContact.WelfareEntry.COLIMN_NAME_USERNAME, r.who);

            db.insert(WelfarePersistenceContact.WelfareEntry.TABLE_NAME, null, cv);
        }
        db.close();
    }

    @Override
    public void queryPage(String page, GetCallback callback) {

    }

    @Override
    public void queryRandom(GetCallback callback) {

    }
}
