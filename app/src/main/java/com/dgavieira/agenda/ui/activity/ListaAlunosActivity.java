package com.dgavieira.agenda.ui.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dgavieira.agenda.R;
import com.dgavieira.agenda.dao.AlunoDAO;

public class ListaAlunosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        AlunoDAO dao = new AlunoDAO();
        setTitle("Lista de Alunos");

        ListView listadeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        listadeAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dao.todos()));

    }
}
