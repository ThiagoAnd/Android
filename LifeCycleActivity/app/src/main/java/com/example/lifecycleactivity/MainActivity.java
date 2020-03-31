package com.example.lifecycleactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    protected static final String CATEGORIA = "CARRO";

    private String getClassName(){
        String s = getClass().getName();
        return s.substring(s.lastIndexOf("."));
    }

    public void onCreate(Bundle icicle){
        super.onCreate(icicle);
        Log.i(CATEGORIA, getClassName()+"Método .onCreate() chamado: ") ;
        TextView t = new TextView(this);
        t.setText("exemplo do ciclo de vida. \nConsulte os logs no logCat. utilizando filtro pela palavra chamado");
        setContentView(t);
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