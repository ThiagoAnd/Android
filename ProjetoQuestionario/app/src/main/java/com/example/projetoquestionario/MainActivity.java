package com.example.projetoquestionario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.TypefaceProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TypefaceProvider.registerDefaultIconSets();
        setContentView(R.layout.activity_main);

        final RadioGroup group1 = findViewById(R.id.group1);
        final RadioGroup group2 = findViewById(R.id.group2);
        final RadioGroup group3 = findViewById(R.id.group3);
        final RadioGroup group4 = findViewById(R.id.group4);
        final RadioGroup group5 = findViewById(R.id.group5);
        final RadioGroup group6 = findViewById(R.id.group6);
        final RadioGroup group7 = findViewById(R.id.group7);

        Button btResponder = findViewById(R.id.btResponder);

        btResponder.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if ((group1.getCheckedRadioButtonId() == -1) ||
                        (group2.getCheckedRadioButtonId() == -1) ||
                        (group3.getCheckedRadioButtonId() == -1) ||
                        (group4.getCheckedRadioButtonId() == -1) ||
                        (group5.getCheckedRadioButtonId() == -1) ||
                        (group6.getCheckedRadioButtonId() == -1) ||
                        (group7.getCheckedRadioButtonId() == -1)) {

                    Toast toast = Toast.makeText(getApplicationContext(), "Todas as perguntas devem ser respondidas!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();

                } else {

                    String g1 = ((RadioButton) findViewById(group1.getCheckedRadioButtonId())).getText().toString();
                    String g2 = ((RadioButton) findViewById(group2.getCheckedRadioButtonId())).getText().toString();
                    String g3 = ((RadioButton) findViewById(group3.getCheckedRadioButtonId())).getText().toString();
                    String g4 = ((RadioButton) findViewById(group4.getCheckedRadioButtonId())).getText().toString();
                    String g5 = ((RadioButton) findViewById(group5.getCheckedRadioButtonId())).getText().toString();
                    String g6 = ((RadioButton) findViewById(group6.getCheckedRadioButtonId())).getText().toString();
                    String g7 = ((RadioButton) findViewById(group7.getCheckedRadioButtonId())).getText().toString();


                    List<String> grupos = new ArrayList<>();
                    grupos.add(g1);
                    grupos.add(g2);
                    grupos.add(g3);
                    grupos.add(g4);
                    grupos.add(g5);
                    grupos.add(g6);
                    grupos.add(g7);

                    int resultado = 0;
                    for (int i = 0; i <= 6; i++) {
                        switch (grupos.get(i).toString()) {
                            case "Não tem dificuldade":
                                resultado += 10;
                                break;
                            case "Tem pequena dificuldade":
                                resultado += 8;
                                break;
                            case "Tem grande dificuldade":
                                resultado += 6;
                                break;
                            case "Não consegue":
                                resultado += 4;
                                break;
                            default:
                                break;
                        }

                    }

                    /*  String b = String.valueOf(resultado);*/
                    String texto = "";
                    if (resultado == 70) {
                        texto = "Voce deve ser o super homem não?!!";
                    }else if ((resultado > 60) && (resultado < 70)) {
                        texto = "Voce é uma pessoa super saudavel!";
                    } else if ((resultado < 61) && (resultado > 48)) {
                        texto = "Voce é uma pessoa em boa forma!";
                    } else if ((resultado < 49) && (resultado > 34)) {
                        texto = "Voce é uma pessoa que precisa de exercicios!";
                    } else if ((resultado < 35) && (resultado > 27)) {
                        texto = "Voce é uma pessoa muito sedentária!";
                    }


                    Toast toast = Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();

                }

            }
        });


    }
}
