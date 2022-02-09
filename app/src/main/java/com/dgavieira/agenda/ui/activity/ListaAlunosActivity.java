package com.dgavieira.agenda.ui.activity;

import static com.dgavieira.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;
import static com.dgavieira.agenda.ui.activity.ConstantesActivities.TITULO_APPBAR;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dgavieira.agenda.R;
import com.dgavieira.agenda.dao.AlunoDAO;
import com.dgavieira.agenda.model.Aluno;
import com.dgavieira.agenda.ui.adapter.ListaAlunosAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaAlunosActivity extends AppCompatActivity {

    private final AlunoDAO dao = new AlunoDAO();
    private ListaAlunosAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configuraFabNovoAluno();
        configuraLista();
        dao.salva(new Aluno("Alex", "1122223333", "alex@gmail.com"));
        dao.salva(new Aluno("Fran", "1122223333", "fran@gmail.com"));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_alunos_menu_remover) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                    item.getMenuInfo();
            Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
            remove(alunoEscolhido);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAlunos();
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.
                activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(view -> abreFormularioAlunoModoInsereAluno());
    }

    private void abreFormularioAlunoModoInsereAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }


    private void atualizaAlunos() {
        adapter.atualiza(dao.todos());
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        configuraAdapter(listaDeAlunos);
        configuraListenerDeCliquePorItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }

    private void remove(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }

    private void configuraAdapter(ListView listadeAlunos) {
        adapter = new ListaAlunosAdapter(this);
        listadeAlunos.setAdapter(this.adapter);
    }

    private void configuraListenerDeCliquePorItem(ListView listadeAlunos) {
        listadeAlunos.setOnItemClickListener((adapterView, view, posicao, l) -> {
            Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
            abreFormularioModoEditaAluno(alunoEscolhido);
        });
    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this,
                FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }
}
