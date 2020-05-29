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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroCurso extends AppCompatActivity {

    private BancodeDados bd;
    private EditText nomeCursoInp;
    private EditText turnoCursoInp;
    private Button cadastrarCursoBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_curso);
        this.bd = BancodeDados.Sharedinstance(this);

        nomeCursoInp = (EditText) findViewById(R.id.nomeCursoInput);
        turnoCursoInp = (EditText) findViewById(R.id.turnoCursoInput);
        cadastrarCursoBt = (Button) findViewById(R.id.cadastrarCursoButton);

        cadastrarCursoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(500); //You can manage the blinking time with this parameter
                anim.setStartOffset(0);
               /* anim.setRepeatMode(Animation.REVERSE);
                anim.setRepeatCount(Animation.INFINITE);*/
                cadastrarCursoBt.startAnimation(anim);
                /* logarUsuarioBt.clearAnimation();*/

                inserirCursoOnClick(v);
            }
        });
    }

    public void inserirCursoOnClick(View view){


        Curso curso = new Curso();

        curso.setNome(nomeCursoInp.getText().toString());
        curso.setTurno(turnoCursoInp.getText().toString());



        /*String.valueOf(resposta)*/
        long resposta = bd.insertCurso(curso);
        if (resposta > 0) {
            Toast toast = Toast.makeText(getApplicationContext(),"Curso cadastrado com sucesso" , Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, -345);
            toast.show();
        }else {
            Toast toast = Toast.makeText(getApplicationContext(), "Erro no cadastro do curso", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, -345);
            toast.show();

        }


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
