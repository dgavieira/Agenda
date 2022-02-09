package com.dgavieira.agenda;

import android.app.Application;

import com.dgavieira.agenda.dao.AlunoDAO;
import com.dgavieira.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosDeTeste();
    }

    private void criaAlunosDeTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Alex", "1122223333", "alex@gmail.com"));
        dao.salva(new Aluno("Fran", "1122223333", "fran@gmail.com"));
    }
}
