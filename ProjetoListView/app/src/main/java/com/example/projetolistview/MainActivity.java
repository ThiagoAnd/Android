package com.example.projetolistview;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] options = new String[]{"Thiago", "Bruno", "Adilson"};
        ArrayAdapter<String> arrayAdapter = new
                ArrayAdapter<>(this, android.R.layout.simple_list_item_1, options);
        setListAdapter(arrayAdapter);
    }
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position){
            case 0:
                startActivity(new Intent(this,ThiagoActivity.class));
                break;
            case 1:
                startActivity(new Intent(this,BrunoActivity.class));
                break;
            case 2:
                startActivity(new Intent(this,AdilsonActivity.class));
                break;
                default:
                    break;


        }

    }

/*
    protected void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);
        Object o = this.getListAdapter().getItem(position);
        String opcao = o.toString();
        Toast.makeText(this, "Voce selecionou " + opcao, Toast.LENGTH_SHORT).show();

    }*/
}
