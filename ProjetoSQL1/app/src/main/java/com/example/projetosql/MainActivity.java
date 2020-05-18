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

        final Intent it = new Intent(this, CadastroAluno.class);

        btTelaAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(it);

            }

        });





    }

}
