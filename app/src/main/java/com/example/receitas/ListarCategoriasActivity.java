package com.example.receitas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class ListarCategoriasActivity extends AppCompatActivity {

    private ListView listView;
    private  CategoriaDAO categoriaDAO;
    private List<Categoria> categorias;
    private List<Categoria> categoriasFitradas= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_categorias);

        listView = findViewById(R.id.lista_categorias);
        categoriaDAO = new CategoriaDAO(this);
        categorias = categoriaDAO.retornarTodasCategorias();
        categoriasFitradas.addAll(categorias);

        //ArrayAdapter<Pessoa> adapter = new ArrayAdapter<Pessoa>(this, android.R.layout.simple_list_item_1, pessoasFiltradas);
        CategoriaAdapter adapter = new CategoriaAdapter(this, categoriasFitradas);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);
    }

    public void onCreateContextMenu (ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);

    }

    public void procuraCategoria (String nome){
        categoriasFitradas.clear();
        for (Categoria cat : categorias){
            if(cat.getNome().toLowerCase().contains(nome.toLowerCase())){
                categoriasFitradas.add(cat);
            }
        }
        listView.invalidateViews();
    }
    public void excluirCategoria(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Categoria categoriaExcluir = categoriasFitradas.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção!")
                .setMessage("Deseja realmente excluir esse cadastro?")
                .setNegativeButton("NÂO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        categoriasFitradas.remove(categoriaExcluir);
                        categorias.remove(categoriaExcluir);
                        categoriaDAO.excluirCategoria(categoriaExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void atualizarCategoria(MenuItem menuItem){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

        final Categoria categoriaAtualizar = categoriasFitradas.get(menuInfo.position);
        Intent it = new Intent(this, CadastroCategoriaActivity.class);
        it.putExtra("categoria", categoriaAtualizar);
        startActivity(it);
    }

    @Override
    public void onResume(){
        super.onResume();
        categorias=categoriaDAO.retornarTodasCategorias();
        categoriasFitradas.clear();
        categoriasFitradas.addAll(categorias);
        listView.invalidateViews();
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
