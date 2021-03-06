package com.meag.notas00002816;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarAlumno extends Fragment {
    private EditText carnet;
    private EditText nombre;
    private Button btn_registrar;
    boolean flag = false;

    public AgregarAlumno() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_agregar_alumno, container, false);
        inicializarControles(v);
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = DBHelper.myDB.add(new Alumno(carnet.getText().toString(), nombre.getText().toString(), "0"));

                if (flag) {
                    Log.d("Edit", nombre.getText().toString());
                    limpiar();
                    Fragment fragment = new VerNotas();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, fragment).commit();
                }
            }
        });
        return v;
    }


    private void inicializarControles(View v) {
        btn_registrar = v.findViewById(R.id.btn_agregar);
        carnet = v.findViewById(R.id.edit_text_carnet);
        nombre = v.findViewById(R.id.edit_text_name);
    }

    private void limpiar() {
        carnet.setText("");
        nombre.setText("");
    }

}
