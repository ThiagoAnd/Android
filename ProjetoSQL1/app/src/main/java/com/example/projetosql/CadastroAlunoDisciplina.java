package com.example.projetosql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroAlunoDisciplina extends AppCompatActivity {

    private BancodeDados bd;

    private EditText codDisciplinaInp;
    private EditText codAlunoInp;
    private Button cadastrarAlunoDisciplinaBut

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno_disciplina);

        this.bd = BancodeDados.Sharedinstance(this);

        codDisciplinaInp = (EditText) findViewById(R.id.codDisciplinaInput);
        codAlunoInp = (EditText) findViewById(R.id.codAlunoInput);
        cadastrarAlunoDisciplinaBut = (Button) findViewById(R.id.cadastrarAlunoDisciplinaButton);

        cadastrarAlunoDisciplinaBut.setOnClickListener(View );
    }
}
