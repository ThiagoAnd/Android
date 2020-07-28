package com.example.projetosql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    private Context context;
    private ArrayList<Curso> lista;

    public Adapter(Context context, ArrayList<Curso> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Curso curso = lista.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.activity_cons, null);

        TextView nome = (TextView) layout.findViewById(R.id.texto1);
        nome.setText(curso.getNome());
        nome.setEnabled(false);

        TextView turno = (TextView) layout.findViewById(R.id.texto2);
        turno.setText(curso.getTurno());
        turno.setEnabled(false);

        TextView codigo = (TextView) layout.findViewById(R.id.texto3);
        codigo.setText(curso.getCodCurso());
        codigo.setEnabled(false);

        return layout;
    }
}
