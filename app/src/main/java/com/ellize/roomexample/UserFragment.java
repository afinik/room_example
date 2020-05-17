package com.ellize.roomexample;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class UserFragment extends Fragment {

    private static final String ARG_ID = "user id";


    private int mUserID;


    public UserFragment() {
        // Required empty public constructor
    }


    public static UserFragment newInstance(int id) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUserID = getArguments().getInt(ARG_ID,-1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_user, container, false);
        final MainViewModel viewModel = ((MainActivity)requireActivity()).viewModel;
        final EditText ed_name = v.findViewById(R.id.ed_name);
        final EditText ed_surname = v.findViewById(R.id.ed_surname);
        final EditText ed_age = v.findViewById(R.id.ed_age);
        Button btn_add = v.findViewById(R.id.btn_ok);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUserID == -1){
                    if(!ed_name.getText().toString().equals("") && !ed_age.getText().toString().equals("")) {
                        User user = new User(ed_name.getText().toString(),
                                ed_surname.getText().toString(),
                                Integer.parseInt(ed_age.getText().toString()),0);
                        viewModel.userDataBase.userDao().insert(user);
                        viewModel.getAllUsers();
                    }
                } else {
                    if(!ed_name.getText().toString().equals("") && !ed_age.getText().toString().equals("")) {
                        User user = new User(ed_name.getText().toString(),
                                ed_surname.getText().toString(),
                                Integer.parseInt(ed_age.getText().toString()),0);
                        user.uuid = mUserID;
                        viewModel.userDataBase.userDao().update(user);
                        viewModel.getAllUsers();
                    }
                }
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_container,new UserListFragment())
                        .commit();
            }
        });
        if(mUserID == -1){
            btn_add.setText("add");
        } else {

            User user = viewModel.getUser(mUserID);
            if(user != null){
                btn_add.setText("edit");
                ed_age.setText(""+user.age);
                ed_name.setText(user.name);
                ed_surname.setText(user.surname);
            }
        }
        return v;
    }
}
