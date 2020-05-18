package com.example.projetosql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroDisciplina extends AppCompatActivity {

    private BancodeDados bd;

    private EditText disciplinaInp;
    private EditText cargaHorariaInp;
    private Button cadastrarDisciplinaBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_disciplina);

        this.bd = BancodeDados.Sharedinstance(this);


        disciplinaInp = (EditText) findViewById(R.id.disciplinaInput);
        cargaHorariaInp = (EditText) findViewById(R.id.cargaHorariaInput);
        cadastrarDisciplinaBt = (Button) findViewById(R.id.cadastrarDisciplinaBt);


        cadastrarDisciplinaBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarDisciplina(v);
            }
        });


    }


    public void cadastrarDisciplina(View view){
        Disciplinas disciplina = new Disciplinas();

        disciplina.setEntNomeDisciplina(disciplinaInp.getText().toString());
        disciplina.setEntCargaHoraria(cargaHorariaInp.getText().toString());

        bd.insertDisciplina(disciplina);

    }
}
