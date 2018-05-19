package com.meag.notas00002816;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Notas extends Fragment {
    private EditText carnet, nombre, nota;

    private Button btnbuscar, btnmodificar, btneliminar, btnlimpiar;

    public Notas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notas, container, false);

        inicializarControles(v);
        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alumno p = DBHelper.myDB.findUser(carnet.getText().toString());
                if (p == null) {
                    Toast.makeText(getContext(), "usuario no encontrado", Toast.LENGTH_SHORT);
                    limpiar();
                } else {
                    nombre.setText(p.getNombre());
                    nota.setText(p.getNota());
                }
            }
        });
        btnmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper.myDB.editUser(new Alumno(carnet.getText().toString(), nombre.getText().toString(), nota.getText().toString()));
            }
        });
        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper.myDB.deleteUser(carnet.getText().toString());
                limpiar();
            }
        });
        btnlimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });
        return v;
    }

    private void limpiar() {
        carnet.setText("");
        nombre.setText("");
        nota.setText("");
    }

    private void inicializarControles(View v) {
        btnbuscar = v.findViewById(R.id.btn_buscar);
        btnmodificar = v.findViewById(R.id.btn_modificar);
        btneliminar = v.findViewById(R.id.btn_eliminar);
        btnlimpiar = v.findViewById(R.id.btn_limpiar);
        carnet = v.findViewById(R.id.edit_text_carnet);
        nombre = v.findViewById(R.id.edit_text_name);
        nota = v.findViewById(R.id.edit_text_score);
    }
}
