package com.example.receitas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public CategoriaDAO (Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir (Categoria categoria){
        ContentValues values = new ContentValues();
        values.put("nomeCategoria", categoria.getNome());
        values.put("descricao", categoria.getDescricao());
        return banco.insert("categoria", null, values);
    }

    public List<Categoria> retornarTodasCategorias(){
        List<Categoria> categorias = new ArrayList<>();
        Cursor cursor = banco.query("categoria", new String[]{"id", "nomeCategoria", "descricao"},
                null, null, null, null, null, null);
        while (cursor.moveToNext()){
            Categoria p = new Categoria();
            p.setId(cursor.getInt(0));
            p.setNome(cursor.getString(1));
            p.setDescricao(cursor.getString(2));
            categorias.add(p);
        }

        return categorias;
    }

    public void excluirCategoria (Categoria cat){
        banco.delete("categoria", "id = ?", new String[]{cat.getId().toString()});
    }

    public void atualizarCat (Categoria categoria){
        ContentValues values = new ContentValues();
        values.put("nomeCategoria", categoria.getNome());
        values.put("descricao", categoria.getDescricao());
        banco.update("categoria", values,
                "where = ?", new String[]{categoria.getId().toString()});
    }
}
