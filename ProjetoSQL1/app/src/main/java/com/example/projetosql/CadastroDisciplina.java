package com.example.projetosql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class CadastroDisciplina extends AppCompatActivity {

    private BancodeDados bd;
    private String codigo;
    private EditText disciplinaInp;
    private EditText cargaHorariaInp;
    private EditText codPeriodoInp;
    private Button cadastrarDisciplinaBt;
    private AutoCompleteTextView autoCodPeriodoDisciplinaInp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_disciplina);
       // final Date currentTime = Calendar.getInstance().getTime();
       // final String data =( new SimpleDateFormat( "dd-MM-yyyy' / 'HH:mm" ) ).format( Calendar.getInstance().getTime() );


        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy' / 'HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-3"));
        final String strDate = dateFormat.format(date);


        this.bd = BancodeDados.Sharedinstance(this);

        carregarCodPeriodo();

        disciplinaInp = (EditText) findViewById(R.id.disciplinaInput);
        cargaHorariaInp = (EditText) findViewById(R.id.cargaHorariaInput);
        cadastrarDisciplinaBt = (Button) findViewById(R.id.cadastrarDisciplinaBt);
        //codPeriodoInp = (EditText) findViewById(R.id.codPeriodoDisciplinaInput);


        cadastrarDisciplinaBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(500); //You can manage the blinking time with this parameter
                anim.setStartOffset(0);
               /* anim.setRepeatMode(Animation.REVERSE);
                anim.setRepeatCount(Animation.INFINITE);*/
                cadastrarDisciplinaBt.startAnimation(anim);
                /* logarUsuarioBt.clearAnimation();*/

                cadastrarDisciplina(v);
            }
        });

        autoCodPeriodoDisciplinaInp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!autoCodPeriodoDisciplinaInp.getText().toString().equals("")) {
                   codigo =  bd.buscarCodPeriodo(s.toString());
                   /* Toast toast = Toast.makeText(getApplicationContext(), strDate, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, -345);
                    toast.show();*/
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }



    public void carregarCodPeriodo() {
        List<Periodo> lista = bd.listarPeriodos();

        autoCodPeriodoDisciplinaInp =  findViewById(R.id.autoCompleteCodPeriodoDisciplinaInput);



        List<String> listaCod = new ArrayList<>();

        for (Periodo p : lista) {
            listaCod.add(p.getNome());

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, listaCod);
        autoCodPeriodoDisciplinaInp.setThreshold(1);
        autoCodPeriodoDisciplinaInp.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.right_menu, menu);
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


    public void cadastrarDisciplina(View view) {
        Disciplinas disciplina = new Disciplinas();

        // matricula.setCodMatricula(Integer.parseInt(codMatriculaInp.getText().toString()));
        //disciplina.setCodPeriodo(Integer.parseInt(autoCodPeriodoDisciplinaInp.getText().toString()));
        disciplina.setCodPeriodo(Integer.parseInt(codigo));
        disciplina.setEntNomeDisciplina(disciplinaInp.getText().toString());
        disciplina.setEntCargaHoraria(cargaHorariaInp.getText().toString());

        long resposta = bd.insertDisciplina(disciplina);
        if (resposta > 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "Disciplina cadastrada com sucesso", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, -345);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Erro no cadastro da disciplina", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, -345);
            toast.show();

        }

    }
}
