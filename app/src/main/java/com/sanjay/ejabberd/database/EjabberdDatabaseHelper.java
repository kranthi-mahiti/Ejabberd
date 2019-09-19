package com.sanjay.ejabberd.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sanjay.ejabberd.app.Constants;
import com.sanjay.ejabberd.utils.Logger;


public class EjabberdDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "chat.db";
    public static final int DATABASE_VERSION = 1;
    String TAG = EjabberdDatabaseHelper.class.getSimpleName();
    public static SQLiteDatabase database;

    public EjabberdDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // ---- creating Contact Table
    private static String CREATE_CONTATCS_STATEMENT = "create table IF NOT EXISTS "
            + Constants.TABLENAME_CONTACTS + "(" + Constants.ACCOUNT + " TEXT, "
            + Constants.SERVERNAME + " TEXT, " + Constants.SYSTEMNAME + " TEXT,"
            + Constants.JID + " TEXT," + Constants.KEYS + " TEXT,"
            + Constants.PHOTOURI + " TEXT," + Constants.OPTIONS + " NUMBER,"
            + Constants.SYSTEMACCOUNT + " NUMBER, " + Constants.AVATAR + " TEXT, "
            + Constants.LAST_PRESENCE + " TEXT, " + Constants.LAST_TIME + " NUMBER, "
            + Constants.GROUPS + " TEXT )";


    private static String CREATE_MESSAGE_TABLE = "create table IF NOT EXISTS " + Constants.TABLE_NAME_MSG + "( " + Constants.UUID
            + " TEXT PRIMARY KEY, " + Constants.CONVERSATION + " TEXT, "
            + Constants.TIME_SENT + " NUMBER, " + Constants.COUNTERPART
            + " TEXT, " + Constants.TRUE_COUNTERPART + " TEXT,"
            + Constants.BODY + " TEXT, " + Constants.ENCRYPTION + " NUMBER, "
            + Constants.STATUS + " NUMBER," + Constants.TYPE + " NUMBER, "
            + Constants.RELATIVE_FILE_PATH + " TEXT, "
            + Constants.SERVER_MSG_ID + " TEXT, "
            + Constants.FINGERPRINT + " TEXT, "
            + Constants.CARBON + " INTEGER, "
            + Constants.EDITED + " TEXT, "
            + Constants.READ + " NUMBER DEFAULT 1, "
            + Constants.DELETED + " NUMBER DEFAULT 0, "
            + Constants.OOB + " INTEGER, "
            + Constants.ERROR_MESSAGE + " TEXT,"
            + Constants.READ_BY_MARKERS + " TEXT,"
            + Constants.MARKABLE + " NUMBER DEFAULT 0,"
            + Constants.FILE_DELETED + " NUMBER DEFAULT 0,"
            + Constants.REMOTE_MSG_ID + " TEXT)";


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_CONTATCS_STATEMENT);
        Logger.logD(TAG, "query" + CREATE_MESSAGE_TABLE);
        db.execSQL(CREATE_MESSAGE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + CREATE_CONTATCS_STATEMENT + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + CREATE_MESSAGE_TABLE + "'");

    }
    public SQLiteDatabase getWriteDb() {
        if (database != null && database.isOpen())
            return database;
        else
            return getWritableDatabase();
    }

}
