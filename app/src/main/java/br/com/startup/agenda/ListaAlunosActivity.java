package br.com.startup.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        String[] alunos = {"Rafael", "Felipe", "Sandra","Rafael", "Felipe", "Sandra","Rafael", "Felipe", "Sandra","Rafael", "Felipe", "Sandra"};
        ListView listaAlunos = (ListView) findViewById(R.id.lista_alunos);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alunos);
        listaAlunos.setAdapter(adapter);

        Button botaoNovo = (Button) findViewById(R.id.bt_novo);

        botaoNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToForm = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intentToForm);
            }
        });

    }
}
