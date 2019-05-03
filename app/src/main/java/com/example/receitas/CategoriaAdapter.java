package com.example.receitas;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CategoriaAdapter extends BaseAdapter {
    private List<Categoria> categorias;
    private Activity activity;

    public CategoriaAdapter(Activity activity, List<Categoria> categorias) {
        this.activity = activity;
        this.categorias = categorias;
    }

    @Override
    public int getCount() {
        return categorias.size();
    }

    @Override
    public Object getItem(int i) {
        return categorias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return categorias.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View view = activity.getLayoutInflater().inflate(R.layout.item_categoria, parent, false);
        TextView nome = view.findViewById(R.id.txtNomeCat);
        TextView descricao = view.findViewById(R.id.txtDescricao);

        Categoria p = categorias.get(i);

        nome.setText(p.getNome());
        descricao.setText(p.getDescricao());

        return view;
    }
}
