package com.ellize.roomexample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    public UserDataBase userDataBase;
    public MutableLiveData<List<User>> listUsersLiveData;
    public MainViewModel(@NonNull Application application) {
        super(application);
        userDataBase = Room.databaseBuilder(application,UserDataBase.class,"user_db")
                .allowMainThreadQueries()
                .build();
        listUsersLiveData = new MutableLiveData<>();
        listUsersLiveData.setValue(new ArrayList<User>());
    }
    public void getAllUsers(){
        new AsyncTask<Void,Void,List<User>>(){

            @Override
            protected List<User> doInBackground(Void... voids) {

                return userDataBase.userDao().getAllUsers();
            }

            @Override
            protected void onPostExecute(List<User> users) {
                listUsersLiveData.setValue(users);
                super.onPostExecute(users);
            }
        }.execute();
    }
    public User getUser(int id){
        for(User u: listUsersLiveData.getValue()){
            if(u.uuid == id){
                return u;
            }
        }
        return null;
    }
}
