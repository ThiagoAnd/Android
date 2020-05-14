package com.example.projetosql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private BancodeDados bd;
    private EditText entMatricula;
    private EditText entNomeAluno;
    private EditText entEmail;
    private EditText entTelefone;
    private Button btInserirAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*forçar */

        this.bd = BancodeDados.Sharedinstance(this);

        entMatricula = (EditText) findViewById(R.id.editText4);
        entNomeAluno = (EditText) findViewById(R.id.editText2);
        entEmail = (EditText) findViewById(R.id.editText);
        entTelefone = (EditText) findViewById(R.id.editText3);
        btInserirAluno = (Button) findViewById(R.id.button);

    btInserirAluno.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            btInserirAlunoOnClick(v);
        }
    });

    }



    public void btInserirAlunoOnClick(View view){

       /* Toast toast = Toast.makeText(getApplicationContext(), "entrei no toast", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();*/

        Aluno aluno = new Aluno();

        aluno.setMatricula(Integer.parseInt(entMatricula.getText().toString()));
        aluno.setNome(entNomeAluno.getText().toString());
        aluno.setEmail(entEmail.getText().toString());
        aluno.setTelefone(entTelefone.getText().toString());

        bd.insertAluno(aluno);


    }
}
