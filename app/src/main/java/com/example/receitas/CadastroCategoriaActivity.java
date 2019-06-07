package com.example.receitas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
//Test
public class CadastroCategoriaActivity extends AppCompatActivity {

    private EditText nome;
    private EditText descricao;
    private CategoriaDAO categoriaDAO;
    private Categoria categoria = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_categorias);

        nome = findViewById(R.id.editTextNome);
        descricao  = findViewById(R.id.editTextDescricao);
        categoriaDAO = new CategoriaDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("categoria")){
            categoria = (Categoria) it.getSerializableExtra("categoria");
            nome.setText(categoria.getNome());
            descricao.setText(categoria.getDescricao());
        }
    }

    public void salvar (View view){


        if (categoria == null) {
            categoria = new Categoria();
            categoria.setNome(nome.getText().toString());
            categoria.setDescricao(descricao.getText().toString());
            long id = categoriaDAO.inserir(categoria);
            Toast.makeText(this, "Cadastro Realizado com Sucesso!" + id, Toast.LENGTH_SHORT).show();
        }else {

            categoria.setNome(nome.getText().toString());
            categoria.setDescricao(descricao.getText().toString());
            categoriaDAO.atualizarCat(categoria);
            Toast.makeText(this, "Cadastro Atualizado com Sucesso!", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        return true;
    }

    public void cadastrarCategoria (MenuItem item){
        Intent it = new Intent (this, CadastroCategoriaActivity.class);
        startActivity(it);
    }

    public void listarCategoria (MenuItem item){
        Intent it = new Intent (this, ListarCategoriasActivity.class);
        startActivity(it);
    }

    public void cadastrarReceita (MenuItem item){
        Intent it = new Intent (this, CadastroCategoriaActivity.class);
        startActivity(it);
    }

    public void listarReceita (MenuItem item){
        Intent it = new Intent (this, CadastroCategoriaActivity.class);
        startActivity(it);
    }
}
