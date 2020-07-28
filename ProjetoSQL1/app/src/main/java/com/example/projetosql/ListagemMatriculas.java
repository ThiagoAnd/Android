package com.example.projetosql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.List;

public class ListagemMatriculas extends AppCompatActivity {

    private List<Matricula> matriculaList;

    private BancodeDados bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_matriculas);

        this.bd = BancodeDados.Sharedinstance(this);

        carregarLista();
    }

    public void carregarLista (){
        matriculaList = bd.listarMatriculas();

        for (Matricula ma : matriculaList){

            adicionarBlocoDeRegistro(ma);

        }

    }

    public void adicionarBlocoDeRegistro(final Matricula matricula){

        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayoutMatriculaListagem);
        TableRow tr = new TableRow(this);
        TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(10,10,10,10);
        tr.setLayoutParams(lp);

        TableRow.LayoutParams lPtr = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT);
        lPtr.setMargins(-10, 0, -10, 0);
        //lPtr.gravity=Gravity.CENTER;



        GradientDrawable gdB = new GradientDrawable();
        gdB.setColor(0xFFFFFFFF); // Changes this drawbale to use a single color instead of a gradient
        gdB.setCornerRadius(0);
        gdB.setStroke(20, 0xFFFFFFFF);

        Button editarBtA = new Button(this);
        editarBtA.setText(String.valueOf(matricula.getIdMatricula()));
        editarBtA.setBackground(gdB);
        editarBtA.setTextSize(7);
        editarBtA.setLayoutParams(lPtr);
        tr.addView(editarBtA);

        Button editarBtb = new Button(this);
        editarBtb.setText(matricula.getDataMatricula());
        editarBtb.setBackground(gdB);
        editarBtb.setTextSize(7);
        editarBtb.setLayoutParams(lPtr);
        tr.addView(editarBtb);

        Button editarBtC = new Button(this);
        editarBtC.setText(String.valueOf(matricula.getCodMatricula()));
        editarBtC.setBackground(gdB);
        editarBtC.setTextSize(7);
        editarBtC.setLayoutParams(lPtr);
        tr.addView(editarBtC);

        Button editarBtD = new Button(this);
        editarBtD.setText(String.valueOf(matricula.getCodCurso()));
        editarBtD.setBackground(gdB);
        editarBtD.setTextSize(7);
        editarBtD.setLayoutParams(lPtr);
        tr.addView(editarBtD);

        Button editarBtE = new Button(this);
        editarBtE.setText(String.valueOf(matricula.getCodPeriodo()));
        editarBtE.setBackground(gdB);
        editarBtE.setTextSize(7);
        editarBtE.setLayoutParams(lPtr);
        tr.addView(editarBtE);

        GradientDrawable gd = new GradientDrawable();
        gd.setColor(0xFF8B814C); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius(0);
        gd.setStroke(30, 0xFFFFFFFF);
        Button editarBt = new Button(this);

        editarBt.setText("Editar");
        editarBt.setBackground(gd);
        editarBt.setTextSize(8);
        // editarBtE.setLayoutParams(lPtr);
        tr.addView(editarBt);


        tableLayout.addView(tr);

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
                itAluno.putExtra("Chave", "PassagemParametro");
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
            case R.id.periodoListagemTela:
                final Intent itListagemPeriodo = new Intent(this, ListagemPeriodo.class);
                startActivity(itListagemPeriodo);
                return true;
            case R.id.alunoListagemTela:
                final Intent itListagemAluno = new Intent(this, ListarAlunos.class);
                startActivity(itListagemAluno);
                return true;
            case R.id.cursoListagemTela:
                final Intent itLogin = new Intent(this, ListagemCursos.class);
                startActivity(itLogin);
                return true;
            case R.id.disciplinaListagemTela:
                final Intent itListagemDisciplina = new Intent(this, ListagemDisciplinas.class);
                startActivity(itListagemDisciplina);
                return true;
            case R.id.matriculaListagemTela:
                final Intent itListagemMatricula = new Intent(this, ListagemMatriculas.class);
                startActivity(itListagemMatricula);
                return true;
            case R.id.matriculaCadastroTela:
                final Intent itMatricula = new Intent(this, CadastroMatricula.class);
                startActivity(itMatricula);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
