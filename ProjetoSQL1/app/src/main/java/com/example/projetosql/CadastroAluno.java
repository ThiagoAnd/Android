package com.example.projetosql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroAluno extends AppCompatActivity {

    private BancodeDados bd;

    private EditText entNomeAluno;
    private EditText entEmail;
    private EditText entCPF;
    private EditText entEndereco;
    private EditText entTelefone;
    private Button btInserirAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        /*forçar */

        this.bd = BancodeDados.Sharedinstance(this);


        entNomeAluno = (EditText) findViewById(R.id.editText2);
        entEmail = (EditText) findViewById(R.id.editText);
        entTelefone = (EditText) findViewById(R.id.editText5);
        entEndereco = (EditText) findViewById(R.id.editText4);
        entCPF = (EditText) findViewById(R.id.editText3);
        btInserirAluno = (Button) findViewById(R.id.button);


        btInserirAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(500); //You can manage the blinking time with this parameter
                anim.setStartOffset(0);
               /* anim.setRepeatMode(Animation.REVERSE);
                anim.setRepeatCount(Animation.INFINITE);*/
                btInserirAluno.startAnimation(anim);
                /* logarUsuarioBt.clearAnimation();*/

              btInserirAlunoOnClick(v);
               // finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.right_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.alunoCadastroTela:
                final Intent itAluno = new Intent(this, CadastroAluno.class);
                startActivity(itAluno);
                return true;
            case R.id.homeTela:
                final Intent itMain = new Intent(this, MainActivity.class);
                startActivity(itMain);
                return true;
            case R.id.cursoCadastroTela:
                final Intent itCurso = new Intent(this, CadastroCurso.class);
                startActivity(itCurso);
                return true;
            case R.id.periodoCadastroTela:
                final Intent itPeriodo = new Intent(this, CadastroPeriodo.class);
                startActivity(itPeriodo);
                return true;
            case R.id.disciplinaCadastroTela:
                final Intent itDisciplina = new Intent(this, CadastroDisciplina.class);
                startActivity(itDisciplina);
                return true;
            case R.id.matriculaCadastroTela:
                final Intent itMatricula = new Intent(this, CadastroMatricula.class);
                startActivity(itMatricula);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void btInserirAlunoOnClick(View view){

       /* Toast toast = Toast.makeText(getApplicationContext(), "entrei no toast", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();*/

        Aluno aluno = new Aluno();

      //  aluno.setMatricula(Integer.parseInt(entMatricula.getText().toString()));
        aluno.setNome(entNomeAluno.getText().toString());
        aluno.setEmail(entEmail.getText().toString());
        aluno.setEndereco(entEndereco.getText().toString());
        aluno.setCPF(entCPF.getText().toString());
        aluno.setTelefone(entTelefone.getText().toString());
        /*String.valueOf(resposta)*/
       long resposta = bd.insertAluno(aluno);
       if (resposta > 0) {
           Toast toast = Toast.makeText(getApplicationContext(),"Aluno cadastrado com sucesso" , Toast.LENGTH_SHORT);
           toast.setGravity(Gravity.CENTER_VERTICAL, 0, -345);
           toast.show();
       }else {
           Toast toast = Toast.makeText(getApplicationContext(), "Erro no cadastro do aluno", Toast.LENGTH_SHORT);
           toast.setGravity(Gravity.CENTER_VERTICAL, 0, -345);
           toast.show();

       }


    }
}
