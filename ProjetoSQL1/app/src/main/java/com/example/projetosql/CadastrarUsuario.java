package com.example.projetosql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarUsuario extends AppCompatActivity {


    private BancodeDados bd;
    private EditText loginUsuarioInp;
    private EditText senhaUsuarioInp;
    private Button cadastrarUsuarioTelaBt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        final Handler handler = new Handler();
        final Intent homeTela = new Intent(this, MainActivity.class);
        setContentView(R.layout.activity_cadastrar_usuario);

        this.bd = BancodeDados.Sharedinstance(this);

        loginUsuarioInp = (EditText) findViewById(R.id.nomeUsuarioInput);
        senhaUsuarioInp= (EditText) findViewById(R.id.senhaUsuarioInput);
        cadastrarUsuarioTelaBt = (Button) findViewById(R.id.cadastrarUsuarioTelaButton);

        cadastrarUsuarioTelaBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                Usuario usuario = new Usuario();

                usuario.setLogin(loginUsuarioInp.getText().toString());
                usuario.setSenha(senhaUsuarioInp.getText().toString());

                long resposta = bd.insertUsuario(usuario);
                if (resposta > 0) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Usuario cadastrado com sucesso" , Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, -345);
                    toast.show();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            /*startActivity(homeTela);*/
                            finish();
                        }
                    }, 3000);

                }else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Erro no cadastro do usuario", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, -345);
                    toast.show();

                }

           /* inserirUsuarioOnClick(v);*/
            }
        });


    }

    public void inserirUsuarioOnClick(View view){




    }



}
