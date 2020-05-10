package com.example.testeactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Tela2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        Intent it = getIntent();
        if(it != null){
            Bundle params = it.getExtras();
            if (params != null){
                String msg = params.getString("msg");
                TextView view = new TextView(this);
                view.setText(msg);
                setContentView(view);

            }

        }
        

    }
}
