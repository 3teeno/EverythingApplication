package com.example.everythingapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cart, container, false);
        Dbtools dbtools=new Dbtools(getContext());

        ArrayList<HashMap<String,String>> array= dbtools.getAllContacts();
        Log.d("Data ka data",array.toString());
        Log.d("Data ka data",""+array.size());

        ListView listView=view.findViewById(R.id.products_listview);
        ListViewAdapter listViewAdapter=new ListViewAdapter(getActivity(),array);
        listView.setAdapter(listViewAdapter);

        return view;

    }
}