package com.devnags.profile.db;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import com.devnags.profile.models.User;
import com.devnags.profile.repositories.UserRepo;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = User.class, exportSchema = false, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static final String TAG = "AppDatabase";
    private static final String DB_Name = "user_db";
    private static AppDatabase instance;
    public abstract UserDao userDao();

    public static synchronized AppDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_Name)
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
            Log.d(TAG, "getInstance: database created");
        }
        return instance;

    }

    static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new MyAsynvTask(instance).execute();
        }

    };

    private static class MyAsynvTask extends AsyncTask<Void,Void,Void>{

        UserDao userDao;
        MyAsynvTask(AppDatabase database){
            userDao = database.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.delete();
            return null;
        }
    }

}
