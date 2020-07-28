package com.example.projetosql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.List;

public class ListagemNotas extends AppCompatActivity {

    private List<Nota> notaList;
    private String parametro;
    private BancodeDados bd;
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_notas);
        this.bd = BancodeDados.Sharedinstance(this);

        Bundle args = getIntent().getExtras();
        parametro = args.getString("Chave");
        ok = (Button) findViewById(R.id.btXmlListagemNotas);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AtribuicaoNotas.class);
                intent.putExtra("Chave", parametro);
                v.getContext().startActivity(intent);
            }
        });
        carregarLista();


    }

    public void carregarLista() {
        notaList = bd.listarNota(Integer.parseInt(parametro));

        for (Nota nt : notaList) {

            adicionarBlocoDeRegistro(nt);

        }

    }

    public void adicionarBlocoDeRegistro(final Nota nota) {

        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayoutNotasListagem);
        TableRow tr = new TableRow(this);
        TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(10, 10, 10, 10);
        //tr.setLayoutParams(lp);

        //TableRow.LayoutParams lPtr = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT);
        //lPtr.setMargins(10, 20, 10, 10);
        //lPtr.gravity=Gravity.CENTER;
        //tr.setLayoutParams(lPtr);

        /*TextView textView = new TextView(this);
        textView.setText(String.valueOf(curso.getCodCurso()));
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(10);
        //textView.setLayoutParams(lPtr);
        tr.addView(textView);



        TextView textView2 = new TextView(this);
        textView2.setText(curso.getNome());
        textView2.setGravity(Gravity.CENTER);
        textView2.setTextSize(10);
        //textView2.setLayoutParams(lPtr);
        tr.addView(textView2);

        TextView textView3 = new TextView(this);
        textView3.setText(curso.getTurno());
        textView3.setGravity(Gravity.CENTER);
        textView3.setTextSize(10);
        //textView3.setLayoutParams(lPtr);
        tr.addView(textView3);*/
        GradientDrawable gdB = new GradientDrawable();
        gdB.setColor(0xFFFFFFFF); // Changes this drawbale to use a single color instead of a gradient
        gdB.setCornerRadius(0);
        gdB.setStroke(20, 0xFFFFFFFF);

        Button editarBtA = new Button(this);
        String nomeDisciplina = bd.checkNomeDisciplina(nota.getCodDisciplina());
        //editarBtA.setText(String.valueOf(nota.getCodDisciplina()));
        editarBtA.setText(nomeDisciplina);
        editarBtA.setBackground(gdB);
        editarBtA.setTextSize(7);
        tr.addView(editarBtA);

        Button editarBtb = new Button(this);
        if (nota.getNota1() != 0.0) {
            editarBtb.setText(nota.getNota1().toString());
        }
        editarBtb.setBackground(gdB);
        editarBtb.setTextSize(7);
        tr.addView(editarBtb);

        Button editarBtC = new Button(this);
        if (nota.getNota2() != 0.0) {
            editarBtC.setText(nota.getNota2().toString());
        }
        editarBtC.setBackground(gdB);
        editarBtC.setTextSize(7);
        tr.addView(editarBtC);

        Button editarBtD = new Button(this);
        if ((nota.getNota1() != 0.0) && (nota.getNota2() != 0.0)) {
            double media = ((nota.getNota1() + nota.getNota2())/2);
            editarBtD.setText(String.format("%.1f", media));
        }
        editarBtD.setBackground(gdB);
        editarBtD.setTextSize(7);
        tr.addView(editarBtD);


        tableLayout.addView(tr);

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
