package com.example.projetosql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_cadastro_aluno);
/*
        Button btTelaAluno = (Button) findViewById(R.id.botaoAluno) ;
        Button btTelaDisciplina = (Button) findViewById(R.id.botaoDisciplina);
        Button btTelaAlunoDisciplina = (Button) findViewById(R.id.botaoAlunoDisciplina);

        final Intent itAluno = new Intent(this, CadastroAluno.class);
        final Intent itDisciplina = new Intent(this,CadastroDisciplina.class);
        final Intent itAlunoDisciplina = new Intent (this,CadastroAlunoDisciplina.class);

        btTelaAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(itAluno);

            }

        });

        btTelaDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(itDisciplina);
            }
        });

        btTelaAlunoDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(itAlunoDisciplina);
            }
        });

*/





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.right_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.alunoCadastroTela:
                final Intent itAluno = new Intent(this, CadastroAluno.class);
                itAluno.putExtra("Chave", "PassagemParametro");
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
            case R.id.periodoListagemTela:
                final Intent itListagemPeriodo = new Intent(this, ListagemPeriodo.class);
                startActivity(itListagemPeriodo);
                return true;
            case R.id.alunoListagemTela:
                final Intent itListagemAluno = new Intent(this, ListarAlunos.class);
                startActivity(itListagemAluno);
                return true;
            case R.id.cursoListagemTela:
                final Intent itLogin = new Intent(this, ListagemCursos.class);
                startActivity(itLogin);
                return true;
            case R.id.disciplinaListagemTela:
                final Intent itListagemDisciplina = new Intent(this, ListagemDisciplinas.class);
                startActivity(itListagemDisciplina);
                return true;
            case R.id.matriculaListagemTela:
                final Intent itListagemMatricula = new Intent(this, ListagemMatriculas.class);
                startActivity(itListagemMatricula);
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
