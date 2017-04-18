package com.matheusbatista.modelo;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class Lista extends AppCompatActivity implements IAtualizaLista {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private PessoaBanco bdPessoa;
    private ListaPessoas listaPessoas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.FragmentManager fm =  getFragmentManager();
                Cadastro cadastrar = Cadastro.newInstance("Some Title");
                cadastrar.show(fm, "activity_cadastro_pessoa");
            }
        });


    }

    @Override
    public void onResume(){
        super.onResume();
        AtualizarLista();
    }

    private void AtualizarLista()
    {
        bdPessoa = new PessoaBanco(Lista.this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lista);

        List<Pessoa> pessoas = bdPessoa.carregaDados();

        listaPessoas = new ListaPessoas(pessoas, this);
        listaPessoas.setMyViewHolderClickListener(new ListaPessoasViewHolder.MyViewHolderClickListener() {
            @Override
            public void onTextViewNameClick(String nome, String cpf, String telefone) {
                android.app.FragmentManager fm =  getFragmentManager();

                Cadastro editNameDialogFragment = Cadastro.newInstance(nome, cpf, telefone);

                editNameDialogFragment.show(fm, "activity_cadastro_pessoa");

            }

            @Override
            public void onTextViewRollClick(View view, int position) {
                Log.i("AEEEEEE", "AEEEEEE");
            }
        });

        recyclerView.setAdapter(listaPessoas);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onAtualizarLista()
    {
        AtualizarLista();
    }
}