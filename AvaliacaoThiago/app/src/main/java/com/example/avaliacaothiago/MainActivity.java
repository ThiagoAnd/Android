package com.example.avaliacaothiago;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    View view;
    TextView emailTv;
    TextView senhaTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Intent thiagoView = new Intent(this, thiagoActivity.class);
        final Intent brunoView = new Intent(this, brunoActivity.class);
        final Intent adilsonView = new Intent(this, adilsonActivity.class);

        final Handler handler = new Handler();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //view = findViewById(R.id.viewLogin);
        //view.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));


        Button btResponder = findViewById(R.id.btResponder);

        btResponder.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                emailTv = findViewById(R.id.loginEmail);
                String emailS = emailTv.getText().toString();

                senhaTv = findViewById(R.id.loginSenha);
                String senhaS = senhaTv.getText().toString();

                if (emailS.equalsIgnoreCase("thiago@gmail.com") && (senhaS.equals("123"))) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Usuario encontrado, logando...", Toast.LENGTH_SHORT);
                    toast.show();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(thiagoView);
                        }
                    }, 2000);

                } else if (emailS.equalsIgnoreCase("bruno@gmail.com") && (senhaS.equals("123"))) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Usuario encontrado, logando...", Toast.LENGTH_SHORT);
                    toast.show();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(brunoView);
                        }
                    }, 2000);

                } else if (emailS.equalsIgnoreCase("adilson@gmail.com") && (senhaS.equals("123"))) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Usuario encontrado, logando...", Toast.LENGTH_SHORT);
                    toast.show();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(adilsonView);
                        }
                    }, 2000);

                } else if (emailS.isEmpty() || senhaS.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Todos os dados devem ser inseridos", Toast.LENGTH_SHORT);
                    toast.show();

                } else {

                    Toast toast = Toast.makeText(getApplicationContext(), "Email e/ou senha incorreto(s)", Toast.LENGTH_SHORT);
                    toast.show();
                }


            }
        });
    }
}
