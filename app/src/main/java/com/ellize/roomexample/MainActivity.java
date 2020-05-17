package com.ellize.roomexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MainViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainViewModel.class);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container,new UserListFragment())
                .addToBackStack("list")
                .commit();

    }

    public void onClick(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container,UserFragment.newInstance(-1))
                .commit();
    }
}
