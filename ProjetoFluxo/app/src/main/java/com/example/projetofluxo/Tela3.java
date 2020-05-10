package com.example.projetofluxo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class Tela3 extends AppCompatActivity {

    protected static final String CATEGORIA = "Activity 3";

    private String getClassName(){
        String s = getClass().getName();
        return s.substring(s.lastIndexOf("."));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(CATEGORIA, getClassName()+"Método .onCreate() chamado: ") ;

        setContentView(R.layout.activity_tela3);
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


}
