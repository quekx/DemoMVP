package com.example.qkx.demomvp.data.source.local;

import android.provider.BaseColumns;

/**
 * Created by qkx on 16/5/4.
 */
public final class WelfarePersistenceContact {

    public WelfarePersistenceContact() {};

    public static abstract class WelfareEntry implements BaseColumns{
        public static final String TABLE_NAME = "welfare";
        public static final String COLUMN_NAME_PIC_URL = "url";
        public static final String COLIMN_NAME_USERNAME = "username";
    }
}
