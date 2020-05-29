package com.example.projetosql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroMatricula extends AppCompatActivity {



    private BancodeDados bd;
    private EditText codMatriculaInp;
    private EditText dataMatriculaInp;
    private EditText codCursoMatriculaInp;
    private EditText codPeriodoMatriculaInp;
    private Button cadastrarMatriculaBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_matricula);
        this.bd = BancodeDados.Sharedinstance(this);

        codCursoMatriculaInp = (EditText) findViewById(R.id.codCursoMatriculaInput);
        codMatriculaInp = (EditText) findViewById(R.id.codMatriculaInput);
        dataMatriculaInp = (EditText) findViewById(R.id.dataMatriculaInput);
        codPeriodoMatriculaInp = (EditText) findViewById(R.id.codPeriodoMatriculaInput);
        cadastrarMatriculaBt = (Button) findViewById(R.id.cadastrarMatriculaButton);

        cadastrarMatriculaBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirMatriculaOnClick(v);
            }
        });
    }

    public void inserirMatriculaOnClick(View view) {

        Matricula matricula = new Matricula();
        //  aluno.setMatricula(Integer.parseInt(entMatricula.getText().toString()));



        matricula.setCodMatricula(Integer.parseInt(codMatriculaInp.getText().toString()));
        matricula.setDataMatricula(dataMatriculaInp.getText().toString());
        matricula.setCodCurso(Integer.parseInt(codCursoMatriculaInp.getText().toString()));
        matricula.setCodPeriodo(Integer.parseInt(codPeriodoMatriculaInp.getText().toString()));

        /*String.valueOf(resposta)*/
        long resposta = bd.insertMatricula(matricula);
        if (resposta > 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "Matricula cadastrada com sucesso", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, -345);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Erro no cadastro da matricula", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, -345);
            toast.show();

        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.right_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.alunoCadastroTela:
                final Intent itAluno = new Intent(this, CadastroAluno.class);
                startActivity(itAluno);
                return true;
            case R.id.homeTela:
                final Intent itMain = new Intent(this, MainActivity.class);
                startActivity(itMain);
                return true;
            case R.id.cursoCadastroTela:
                final Intent itCurso = new Intent(this, CadastroCurso.class);
                startActivity(itCurso);
                return true;
            case R.id.periodoCadastroTela:
                final Intent itPeriodo = new Intent(this, CadastroPeriodo.class);
                startActivity(itPeriodo);
                return true;
            case R.id.disciplinaCadastroTela:
                final Intent itDisciplina = new Intent(this, CadastroDisciplina.class);
                startActivity(itDisciplina);
                return true;
            case R.id.matriculaCadastroTela:
                final Intent itMatricula = new Intent(this, CadastroMatricula.class);
                startActivity(itMatricula);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
