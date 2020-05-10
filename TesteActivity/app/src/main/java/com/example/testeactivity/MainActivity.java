package com.example.testeactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);

        Button b = new Button(this);
        b.setText("Clique para abrir a tela");
        b.setOnClickListener(this);

        setContentView(b);
    }

    public void onClick(View v){
        Intent it = new Intent(this,Tela2.class);
        Bundle params = new Bundle();
        params.putString("msg", "Thiago!");
        it.putExtras(params);
        startActivity(it);
    }
}
