package com.example.projetoquestionario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.TypefaceProvider;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TypefaceProvider.registerDefaultIconSets();
        setContentView(R.layout.activity_main);

        final RadioGroup groupA = (RadioGroup) findViewById(R.id.groupa);
        final RadioGroup groupB = (RadioGroup) findViewById(R.id.groupb);
        final RadioGroup groupC = (RadioGroup) findViewById(R.id.groupc);
        final RadioGroup groupD = (RadioGroup) findViewById(R.id.groupd);
        final RadioGroup groupE = (RadioGroup) findViewById(R.id.groupe);
        final RadioGroup groupF = (RadioGroup) findViewById(R.id.groupf);
        final RadioGroup groupG = (RadioGroup) findViewById(R.id.groupg);

        Button btResponder = findViewById(R.id.btResponder);

        btResponder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((groupA.getCheckedRadioButtonId() == -1) ||
                        (groupB.getCheckedRadioButtonId() == -1) ||
                        (groupC.getCheckedRadioButtonId() == -1) ||
                        (groupD.getCheckedRadioButtonId() == -1) ||
                        (groupE.getCheckedRadioButtonId() == -1) ||
                        (groupF.getCheckedRadioButtonId() == -1) ||
                        (groupG.getCheckedRadioButtonId() == -1)) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Todas as perguntas tem que ser respondidas!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                } else {
                    int checked;
                    RadioButton radio;
                    int soma = 0;
                    for (int i = 1; i < 8; i++) {
                        checked = group.getCheckedRadioButtonId();
                        radio = findViewById(checked);
                        if (radio.getText().toString() == "Não tem dificuldade") {
                            soma += 10;
                        }

                    }




                    Toast toast = Toast.makeText(getApplicationContext(), String.valueOf(soma), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }


            }
        });


    }
}
