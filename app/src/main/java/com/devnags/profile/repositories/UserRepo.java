package com.devnags.profile.repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.devnags.profile.db.AppDatabase;
import com.devnags.profile.db.UserDao;
import com.devnags.profile.models.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class UserRepo {

    private static final String TAG = "UserRepo";
    UserDao userDao;

    public UserRepo(Application application){
        userDao = AppDatabase.getInstance(application).userDao();

    }

    public LiveData<List<User>> getUser(){
           return userDao.getUser();

    }

    public void insertUser(User user){
       new MyAsyncTask().execute(user);
    }

    public void delete(){
        new MyAsyncTaskDelete().execute();
    }

    public void updateUser(User user){
        new MyAsyncTaskUpdate().execute(user);
    }

    public class MyAsyncTaskDelete extends AsyncTask<User, Void, Void>
    {


        @Override
        protected Void doInBackground(User... users) {
            userDao.delete();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }


    public class MyAsyncTask extends AsyncTask<User, Void, Void>
    {


        @Override
        protected Void doInBackground(User... users) {
            userDao.insertUser(users[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }



    public class MyAsyncTaskUpdate extends AsyncTask<User, Void, Void>
    {


        @Override
        protected Void doInBackground(User... users) {
            userDao.updateUser(users[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}
