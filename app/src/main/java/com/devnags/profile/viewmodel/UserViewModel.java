package com.devnags.profile.viewmodel;

import android.app.Application;
import android.util.Log;

import com.devnags.profile.models.User;
import com.devnags.profile.repositories.UserRepo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends AndroidViewModel {

     UserRepo userRepo = new UserRepo(getApplication());

    public UserViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<List<User>> getUser(){
            return userRepo.getUser();
        }

     public void insertUser(User user){
        userRepo.insertUser(user);
     }
     
     public void deleteUser(){
        userRepo.delete();
     }
}
