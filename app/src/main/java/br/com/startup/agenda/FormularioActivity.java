package br.com.startup.agenda;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

import br.com.startup.agenda.dao.AlunoDAO;
import br.com.startup.agenda.modelo.Aluno;
import br.com.startup.agenda.negocio.FormularioHelper;

public class FormularioActivity extends AppCompatActivity {

    public static final int CODIGO_CAMERA = 567;
    FormularioHelper helper;
    private String caminhoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        final Intent intent = getIntent();
        final Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");

        if(aluno != null) {
            helper.preencheFormulario(aluno);
        }

        Button bt_foto = (Button) findViewById(R.id.bt_form_foto);
        bt_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_foto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalFilesDir(null)+"/"+System.currentTimeMillis()+".jpg";
                File arquivoFoto = new File(caminhoFoto);
                intent_foto.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));

                //
                startActivityForResult(intent_foto, CODIGO_CAMERA);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Chama esse metodo depois que a tira a foto

        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == CODIGO_CAMERA) {
                helper.carregarImagem(caminhoFoto);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_form_ok:
                Aluno aluno = helper.pegaAluno();
                AlunoDAO dao = new AlunoDAO(this);

                if(aluno.getId() != null) {
                    dao.alterar(aluno);
                }else {
                    dao.salvar(aluno);
                }
                dao.close();

                //Toast.makeText(FormularioActivity.this, "Salvando " + aluno.getNome(), Toast.LENGTH_SHORT).show();

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
