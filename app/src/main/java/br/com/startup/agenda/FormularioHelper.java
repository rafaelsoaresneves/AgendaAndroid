package br.com.startup.agenda;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.startup.agenda.modelo.Aluno;

/**
 * Created by rafael on 26/10/16.
 */

public class FormularioHelper {

    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final EditText campoSite;
    private final RatingBar campoNota;


    public FormularioHelper(FormularioActivity activity) {
         campoNome = (EditText) activity.findViewById(R.id.form_nome);
         campoEndereco = (EditText) activity.findViewById(R.id.form_nome);
         campoTelefone = (EditText) activity.findViewById(R.id.form_nome);
         campoSite = (EditText) activity.findViewById(R.id.form_nome);
         campoNota = (RatingBar) activity.findViewById(R.id.form_nota);

    }

    public Aluno pegaAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setSite(campoSite.getText().toString());
        aluno.setNota(Double.valueOf(campoNota.getProgress()));

        return aluno;
    }

}
