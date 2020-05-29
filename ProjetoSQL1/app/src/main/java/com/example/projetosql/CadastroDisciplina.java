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

import java.util.ArrayList;
import java.util.List;

public class CadastroDisciplina extends AppCompatActivity {

    private BancodeDados bd;

    private EditText disciplinaInp;
    private EditText cargaHorariaInp;
    private EditText codPeriodoInp;
    private Button cadastrarDisciplinaBt;
    private AutoCompleteTextView autoCodPeriodoDisciplinaInp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_disciplina);

        this.bd = BancodeDados.Sharedinstance(this);


        disciplinaInp = (EditText) findViewById(R.id.disciplinaInput);
        cargaHorariaInp = (EditText) findViewById(R.id.cargaHorariaInput);
        cadastrarDisciplinaBt = (Button) findViewById(R.id.cadastrarDisciplinaBt);
        codPeriodoInp = (EditText) findViewById(R.id.codPeriodoDisciplinaInput);
        autoCodPeriodoDisciplinaInp = (AutoCompleteTextView) findViewById(R.id.autoCompleteCodPeriodoDisciplinaInput);




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
                carregarCodPeriodo();


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                carregarCodPeriodo();
            }

            @Override
            public void afterTextChanged(Editable s) {
                carregarCodPeriodo();
            }
        });


    }

    public void carregarCodPeriodo(){
        List<Periodo> periodos = bd.listarPeriodos();

        List<Integer> listaCod = new ArrayList<>();

        for (Periodo p : periodos){
        listaCod.add(p.getCodPeriodo());

        }
        List<String> listaNome = new ArrayList<>();

        for (Periodo p : periodos){
            listaNome.add(p.getNome());

        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaNome);
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


    public void cadastrarDisciplina(View view) {
        Disciplinas disciplina = new Disciplinas();

        // matricula.setCodMatricula(Integer.parseInt(codMatriculaInp.getText().toString()));
        disciplina.setCodPeriodo(Integer.parseInt(codPeriodoInp.getText().toString()));
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
