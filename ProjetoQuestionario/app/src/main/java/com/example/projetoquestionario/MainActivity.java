package com.example.projetoquestionario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.TypefaceProvider;

public class MainActivity extends AppCompatActivity {

    final RadioGroup groupA = (RadioGroup) findViewById(R.id.groupa);
    final RadioGroup groupB = (RadioGroup) findViewById(R.id.groupb);
    final RadioGroup groupC = (RadioGroup) findViewById(R.id.groupc);
    final RadioGroup groupD = (RadioGroup) findViewById(R.id.groupd);
    final RadioGroup groupE = (RadioGroup) findViewById(R.id.groupe);
    final RadioGroup groupF = (RadioGroup) findViewById(R.id.groupf);
    final RadioGroup groupG = (RadioGroup) findViewById(R.id.groupg);

    Button btn = findViewById(R.id.responderButton);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TypefaceProvider.registerDefaultIconSets();
        setContentView(R.layout.activity_main);



        btn.setOnClickListener(new View.OnClickListener() {
                                   public void onClick(View v) {
                                       String texto = "Entrei na main";


                                       Toast.makeText(getApplicationContext(),texto,Toast.LENGTH_SHORT).show();
                                   }


                               }


        );
    }
}
