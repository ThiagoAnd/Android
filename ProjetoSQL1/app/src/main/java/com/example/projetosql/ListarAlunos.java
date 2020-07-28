package com.example.projetosql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.List;

public class ListarAlunos extends AppCompatActivity {

    private List<Aluno> alunoList;

    private BancodeDados bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_alunos);


        this.bd = BancodeDados.Sharedinstance(this);

        carregarLista();
    }

    public void carregarLista() {
        alunoList = bd.listarAlunos();
        for (Aluno al : alunoList) {

            adicionarBlocoDeRegistro(al);

        }

    }

    public void adicionarBlocoDeRegistro(final Aluno aluno) {

        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayoutAlunoListagem);
        TableRow tr = new TableRow(this);
        TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(10, 10, 10, 10);
        tr.setLayoutParams(lp);

        TableRow.LayoutParams lPtr = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        lPtr.setMargins(0, 0, -10, 0);
        //lPtr.gravity=Gravity.CENTER;


        GradientDrawable gdB = new GradientDrawable();
        gdB.setColor(0xFFFFFFFF); // Changes this drawbale to use a single color instead of a gradient
        gdB.setCornerRadius(0);
        gdB.setStroke(20, 0xFFFFFFFF);

        Button editarBtA = new Button(this);
        editarBtA.setText(String.valueOf(aluno.getMatricula()));
        editarBtA.setBackground(gdB);
        editarBtA.setTextSize(7);
        editarBtA.setLayoutParams(lPtr);
        tr.addView(editarBtA);

        Button editarBtb = new Button(this);
        editarBtb.setText(aluno.getNome());
        editarBtb.setBackground(gdB);
        editarBtb.setTextSize(7);
        editarBtb.setLayoutParams(lPtr);
        tr.addView(editarBtb);

        Button editarBtC = new Button(this);
        editarBtC.setText(aluno.getEmail());
        editarBtC.setBackground(gdB);
        editarBtC.setTextSize(7);
        editarBtC.setLayoutParams(lPtr);
        tr.addView(editarBtC);

        Button editarBtF = new Button(this);
        editarBtF.setText(aluno.getCPF());
        editarBtF.setBackground(gdB);
        editarBtF.setTextSize(7);
        editarBtF.setLayoutParams(lPtr);
        tr.addView(editarBtF);


        Button editarBtD = new Button(this);
        editarBtD.setText(aluno.getEndereco());
        editarBtD.setBackground(gdB);
        editarBtD.setTextSize(7);
        editarBtD.setLayoutParams(lPtr);
        tr.addView(editarBtD);

        Button editarBtE = new Button(this);
        editarBtE.setText(aluno.getTelefone());
        editarBtE.setBackground(gdB);
        editarBtE.setTextSize(7);
        editarBtE.setLayoutParams(lPtr);
        tr.addView(editarBtE);

        Button editarBtG = new Button(this);
        String data = bd.verificarDataMatricula(aluno.getMatricula());
        if (data != null) {
            editarBtG.setText(data);
        } else {
            editarBtG.setText("Sem matricula");
        }

        editarBtG.setBackground(gdB);
        editarBtG.setTextSize(7);
        editarBtG.setLayoutParams(lPtr);
        tr.addView(editarBtG);

        GradientDrawable gd = new GradientDrawable();
        gd.setCornerRadius(0);
        gd.setStroke(30, 0xFFFFFFFF);

        Button editarBt = new Button(this);
        editarBt.setBackground(gd);
        editarBt.setTextSize(8);

        final int matriculado = bd.checarMatriculado(aluno.getMatricula());
        final int codPeriodo = bd.verificarPeriodo(aluno.getMatricula());
        final int qtdDisciplinas = bd.verificarQtdDisciplinas(codPeriodo);
        final int notasInseridas = bd.checarNota(aluno.getMatricula(), qtdDisciplinas);

        if (matriculado == 0) {
            gd.setColor(0xFF4F4F4F); // Changes this drawbale to use a single color instead of a gradient
            editarBt.setText("Sem matricula");


        } else {

            if ((notasInseridas == 0) || (notasInseridas == 2)) {
                gd.setColor(0xFFFF0000); // Changes this drawbale to use a single color instead of a gradient
                editarBt.setText("Atribuir");
            } else {
                gd.setColor(0xFF8B814C); // Changes this drawbale to use a single color instead of a gradient
                editarBt.setText("Editar");
            }

        }
        editarBt.setTextColor(0xFFFFFFFF);
        editarBtE.setLayoutParams(lPtr);
        editarBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriculado == 0) {
                    mostrarMensagem();
                } else {

                    if (notasInseridas == 0) {


                        Intent intent = new Intent(v.getContext(), AtribuicaoNotas.class);
                        intent.putExtra("Chave", String.valueOf(aluno.getMatricula()));
                        v.getContext().startActivity(intent);
                        //Ainda tem nota para inserir
                        //Mas o professor quer uma primeira tela para inserir a primeira nota
                    } else {

                        Intent intent = new Intent(v.getContext(), ListagemNotas.class);
                        intent.putExtra("Chave", String.valueOf(aluno.getMatricula()));
                        v.getContext().startActivity(intent);
                        // Intent intent = new Intent(v.getContext(), AtribuicaoNotas.class);
                        // intent.putExtra("Chave", String.valueOf(aluno.getMatricula()));
                        //v.getContext().startActivity(intent);

                    }

                }
            }
        });

        tr.addView(editarBt);


        tableLayout.addView(tr);

    }

    public void mostrarMensagem() {
        Toast toast = Toast.makeText(getApplicationContext(), "Aluno nao esta matriculado", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, -345);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.right_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
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
