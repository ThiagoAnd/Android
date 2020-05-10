package com.example.projetofluxo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    protected static final String CATEGORIA = "Activity 1";

    private String getClassName(){
        String s = getClass().getName();
        return s.substring(s.lastIndexOf("."));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);


        Log.i(CATEGORIA, getClassName()+"Método .onCreate() chamado: ") ;

        Button b = new Button(this);
        b.setText("Abrir  segunda activity");
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
