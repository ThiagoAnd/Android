package com.example.projetofluxo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Tela2 extends AppCompatActivity {

    protected static final String CATEGORIA = "Activity 2";

    private String getClassName(){
        String s = getClass().getName();
        return s.substring(s.lastIndexOf("."));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(CATEGORIA, getClassName()+"Método .onCreate() chamado: ") ;
        setContentView(R.layout.activity_tela2);

        Button btResponder = findViewById(R.id.btResponder);
        final Intent it = new Intent(this, Tela3.class);

        btResponder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(it);
                /*alert("OLA ");*/
            }

        });

    }


    protected void onDestroy(){
        super.onDestroy();
        Log.i(CATEGORIA, getClassName()+"Método .onDestroy chamado");
    }

    protected void onStop(){
        super.onStop();
        Log.i(CATEGORIA, getClassName()+ "Método .onStop() chamado");
    }

    protected void onResume(){
        super.onResume();
        Log.i(CATEGORIA, getClassName()+".Método .onResume() chamado");
    }


    protected void onStart(){
        super.onStart();
        Log.i(CATEGORIA, getClassName()+".Método .onStart() chamado");
    }


    protected void onRestart(){
        super.onRestart();
        Log.i(CATEGORIA, getClassName()+"Método .onRestart() chamado");
    }

    protected void onPause(){
        super.onPause();
        Log.i(CATEGORIA, getClassName()+"Método .onPause() chamado");
    }


    /*private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }*/
}
