package com.ellize.roomexample;

import android.os.Bundle;

import androidx.core.content.res.TypedArrayUtils;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UserListFragment extends Fragment {

    public UserListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_list, container, false);
        ListView listView = v.findViewById(R.id.usersListView);
        //SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),new HashMap<String,String>(),1,)
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1);
        listView.setAdapter(arrayAdapter);
        final MainViewModel viewModel = ((MainActivity)requireActivity()).viewModel;
        viewModel.listUsersLiveData.observe(requireActivity(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                arrayAdapter.clear();
                for(User user : users){
                    arrayAdapter.add(user.name+"\n"+user.surname+" "+user.age+"\n"+user.uuid);
                }
                arrayAdapter.notifyDataSetChanged();
            }
        });
        viewModel.getAllUsers();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int uid = viewModel.listUsersLiveData.getValue().get(position).uuid;
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_container,UserFragment.newInstance(uid))
                        .addToBackStack("user")
                        .commit();

            }
        });
        return v;
    }
}
