package com.meag.notas00002816;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class VerNotas extends Fragment {
    RecyclerView rv;
    ScoreAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    List<Alumno> alumnoList;
    public VerNotas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ver_notas, container, false);
        DBHelper dbHelper=new DBHelper(this.getContext());
        alumnoList=dbHelper.filllist();
        rv=v.findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(linearLayoutManager);
        adapter=new ScoreAdapter(alumnoList);
        rv.setAdapter(adapter);
        return v;
    }



}
