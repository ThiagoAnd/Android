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
import android.view.WindowManager;
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

public class CadastroMatricula extends AppCompatActivity {



    private BancodeDados bd;
    private String codigoPeriodo;
    private String codigoCurso;
    private String codigoAluno;
   // private EditText codMatriculaInp;
   // private EditText dataMatriculaInp;
   // private EditText codCursoMatriculaInp;
   // private EditText codPeriodoMatriculaInp;
    private Button cadastrarMatriculaBt;
    private AutoCompleteTextView autoCodPeriodoMatriculaInp;
    private AutoCompleteTextView autoCodCursoMatriculaInp;
    private AutoCompleteTextView autoCodAlunoMatriculaInp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_matricula);
        this.bd = BancodeDados.Sharedinstance(this);

        carregarPeriodo();
        carregarCurso();
        carregarAluno();




        //codCursoMatriculaInp = (EditText) findViewById(R.id.codCursoMatriculaInput);
        //codMatriculaInp = (EditText) findViewById(R.id.codMatriculaInput);
       // dataMatriculaInp = (EditText) findViewById(R.id.dataMatriculaInput);
        //codPeriodoMatriculaInp = (EditText) findViewById(R.id.codPeriodoMatriculaInput);
        cadastrarMatriculaBt = (Button) findViewById(R.id.cadastrarMatriculaButton);



        cadastrarMatriculaBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(500); //You can manage the blinking time with this parameter
                anim.setStartOffset(0);
               /* anim.setRepeatMode(Animation.REVERSE);
                anim.setRepeatCount(Animation.INFINITE);*/
                cadastrarMatriculaBt.startAnimation(anim);
                /* logarUsuarioBt.clearAnimation();*/

                inserirMatriculaOnClick(v);
            }
        });
        autoCodAlunoMatriculaInp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if(!autoCodAlunoMatriculaInp.getText().toString().equals("")) {
                    codigoAluno =  bd.buscarCodAluno(s.toString());
                    /*Toast toast = Toast.makeText(getApplicationContext(), codigoAluno, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, -345);
                    toast.show();*/
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        autoCodCursoMatriculaInp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!autoCodCursoMatriculaInp.getText().toString().equals("")) {
                    codigoCurso =  bd.buscarCodCurso(s.toString());
                   /* Toast toast = Toast.makeText(getApplicationContext(), codigoCurso, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, -345);
                    toast.show();*/
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        autoCodPeriodoMatriculaInp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!autoCodPeriodoMatriculaInp.getText().toString().equals("")) {
                    codigoPeriodo =  bd.buscarCodPeriodo(s.toString());
                    /*Toast toast = Toast.makeText(getApplicationContext(), codigoPeriodo, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, -345);
                    toast.show();*/
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });






    }
    public void carregarAluno (){
        List<Aluno> lista = bd.listarAlunos();

        autoCodAlunoMatriculaInp = findViewById(R.id.autoCompleteCodAlunoMatriculaInput);

        List<String> listaNome = new ArrayList<>();

        for (Aluno p : lista) {
            listaNome.add(p.getNome());

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, listaNome);
        autoCodAlunoMatriculaInp.setThreshold(1);
        autoCodAlunoMatriculaInp.setAdapter(adapter);


    }
    public void carregarPeriodo() {
        List<Periodo> lista = bd.listarPeriodos();

        autoCodPeriodoMatriculaInp =  findViewById(R.id.autoCompleteNomePeriodoMatriculaInput);



        List<String> listaCod = new ArrayList<>();

        for (Periodo p : lista) {
            listaCod.add(p.getNome());

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, listaCod);
        autoCodPeriodoMatriculaInp.setThreshold(1);
        autoCodPeriodoMatriculaInp.setAdapter(adapter);

    }
    public void carregarCurso() {
        List<Curso> lista = bd.listarCursos();

        autoCodCursoMatriculaInp =  findViewById(R.id.autoCompleteNomeCursoMatriculaInput);



        List<String> listaCod = new ArrayList<>();

        for (Curso c : lista) {
            listaCod.add(c.getNome());

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, listaCod);
        autoCodCursoMatriculaInp.setThreshold(1);
        autoCodCursoMatriculaInp.setAdapter(adapter);

    }


    public void inserirMatriculaOnClick(View view) {

        Matricula matricula = new Matricula();
        //  aluno.setMatricula(Integer.parseInt(entMatricula.getText().toString()));
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy' / 'HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-3"));
        String strDate = dateFormat.format(date);


       // matricula.setCodMatricula(Integer.parseInt(codMatriculaInp.getText().toString()));
        matricula.setCodMatricula(Integer.parseInt(codigoAluno));
       // matricula.setDataMatricula(dataMatriculaInp.getText().toString());
        matricula.setDataMatricula(strDate);
        //matricula.setCodCurso(Integer.parseInt(codCursoMatriculaInp.getText().toString()));
       // matricula.setCodPeriodo(Integer.parseInt(codPeriodoMatriculaInp.getText().toString()));
        matricula.setCodCurso(Integer.parseInt(codigoCurso));
        matricula.setCodPeriodo(Integer.parseInt(codigoPeriodo));

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
