package br.com.startup.agenda.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.startup.agenda.ListaAlunosActivity;
import br.com.startup.agenda.modelo.Aluno;

/**
 * Created by rafael on 05/11/16.
 */

public class AlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos;
    private final Context context;

    public AlunosAdapter(Context context, List<Aluno> alunos) {
        this.context = context;
        this.alunos = alunos;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int i) {
        return alunos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return alunos.get(i).getId();
    }

    @Override
    public View getView(int i, View convetView, ViewGroup viewGroup) {
        TextView view = new TextView(context);
        Aluno aluno = alunos.get(i);
        view.setText(aluno.toString());
        return view;
    }

}
