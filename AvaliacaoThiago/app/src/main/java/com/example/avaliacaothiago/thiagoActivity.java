package com.example.avaliacaothiago;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class thiagoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Intent mainView = new Intent(this, MainActivity.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thiago);


        Button btVoltar = findViewById(R.id.btVoltar);

        btVoltar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                startActivity(mainView);

            }


        });


    }


}
