package com.example.projetosql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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







    }

}
