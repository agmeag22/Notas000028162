package com.meag.notas00002816;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {
    public List<Alumno> alumnoList;

    public ScoreAdapter(List<Alumno> alumnoList) {
        this.alumnoList = alumnoList;
    }

    @NonNull
    @Override
    public ScoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.scorecardview, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreAdapter.ViewHolder holder, int position) {
       if(alumnoList.get(position)!=null) {
           if (alumnoList.get(position).getCarnet() != null) {
               holder.carnet.setText(alumnoList.get(position).getCarnet());
           }
           if (alumnoList.get(position).getNombre() != null) {
               holder.nombre.setText(alumnoList.get(position).getNombre());
           }
           if (alumnoList.get(position).getNota() != null) {
               holder.nota.setText(alumnoList.get(position).getNota());
           }
       }
    }

    @Override
    public int getItemCount() {
        return alumnoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView carnet;
        private TextView nombre;
        private TextView nota;

        public ViewHolder(View v) {
            super(v);
            carnet = (TextView) v.findViewById(R.id.textview_carnet);
            nombre = (TextView) v.findViewById(R.id.textview_nombre);
            nota = (TextView) v.findViewById(R.id.textView_nota);
        }
    }
}
