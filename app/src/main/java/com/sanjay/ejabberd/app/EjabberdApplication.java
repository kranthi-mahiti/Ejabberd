package com.sanjay.ejabberd.app;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.amitshekhar.utils.DatabaseHelper;
import com.sanjay.ejabberd.database.EjabberdDatabaseHelper;

public class EjabberdApplication extends Application {

    private static EjabberdApplication instance;

    public EjabberdApplication() {
        instance = this;
    }

    public static synchronized EjabberdApplication getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        EjabberdDatabaseHelper mDbHelper = new EjabberdDatabaseHelper(this);
        SQLiteDatabase localdb = mDbHelper.getWritableDatabase();
        mDbHelper.onCreate(localdb);
    }
}
