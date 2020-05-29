package com.example.projetosql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    private BancodeDados bd;
    private EditText loginUsuarioInp;
    private EditText senhaUsuarioInp;
    private Button cadastrarUsuarioBt;
    private Button logarUsuarioBt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final Intent itCadastroUsuario = new Intent(this, CadastrarUsuario.class);
        final Handler handler = new Handler();
        final Intent homeTela = new Intent(this, MainActivity.class);

        this.bd = BancodeDados.Sharedinstance(this);

        loginUsuarioInp =(EditText) findViewById(R.id.usuarioLoginInput);
        senhaUsuarioInp = (EditText) findViewById(R.id.senhaLoginInput);
        cadastrarUsuarioBt = (Button) findViewById(R.id.cadastrarUsuarioButton);
        logarUsuarioBt = (Button) findViewById(R.id.logarUsuarioButton);

        logarUsuarioBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(500); //You can manage the blinking time with this parameter
                anim.setStartOffset(0);
               /* anim.setRepeatMode(Animation.REVERSE);
                anim.setRepeatCount(Animation.INFINITE);*/
                logarUsuarioBt.startAnimation(anim);
               /* logarUsuarioBt.clearAnimation();*/

                if(bd.checarLogin(loginUsuarioInp.getText().toString(), senhaUsuarioInp.getText().toString())==1) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Usuario encontrado,redirecionando...", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, -345);
                    toast.show();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(homeTela);
                        }
                    }, 3000);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Usuario e/ou senha incorreto(s)", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, -345);
                    toast.show();

                }
            }
        });

        cadastrarUsuarioBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(500); //You can manage the blinking time with this parameter
                anim.setStartOffset(0);
               /* anim.setRepeatMode(Animation.REVERSE);
                anim.setRepeatCount(Animation.INFINITE);*/
                cadastrarUsuarioBt.startAnimation(anim);
                /* logarUsuarioBt.clearAnimation();*/
                startActivity(itCadastroUsuario);

            }
        });

    }








}
