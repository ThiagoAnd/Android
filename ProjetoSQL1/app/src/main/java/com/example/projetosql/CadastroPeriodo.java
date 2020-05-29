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

public class CadastroPeriodo extends AppCompatActivity {

    private BancodeDados bd;
    private EditText nomePeriodoInp;
    private EditText dataInicioPeriodoInp;
    private EditText dataFimPeriodoInp;
    private Button cadastrarPeriodoBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_periodo);

        this.bd = BancodeDados.Sharedinstance(this);

        nomePeriodoInp = (EditText) findViewById(R.id.nomePeriodoInput);
        dataInicioPeriodoInp = (EditText) findViewById(R.id.inicioPeriodoInput);
        dataFimPeriodoInp = (EditText) findViewById(R.id.fimPeriodoInput);
        cadastrarPeriodoBt = (Button) findViewById(R.id.cadastrarPeriodoButton);

        cadastrarPeriodoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(500); //You can manage the blinking time with this parameter
                anim.setStartOffset(0);
               /* anim.setRepeatMode(Animation.REVERSE);
                anim.setRepeatCount(Animation.INFINITE);*/
                cadastrarPeriodoBt.startAnimation(anim);
                /* logarUsuarioBt.clearAnimation();*/

                inserirPeriodoOnClick(v);
            }
        });
    }

    public void inserirPeriodoOnClick(View view) {

        Periodo periodo = new Periodo();

        periodo.setNome(nomePeriodoInp.getText().toString());
        periodo.setDataInicio(dataInicioPeriodoInp.getText().toString());
        periodo.setDataFim(dataFimPeriodoInp.getText().toString());

        /*String.valueOf(resposta)*/
        long resposta = bd.inserirPeriodo(periodo);
        if (resposta > 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "Periodo cadastrado com sucesso", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, -345);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Erro no cadastro do periodo", Toast.LENGTH_SHORT);
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
