package com.example.projetosql;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

public class AtribuicaoNotas extends AppCompatActivity {
    private BancodeDados bd;

    private TextView parametroCodM;
    private List<Disciplinas> disciplinaAlunoList;
    private Button btSalvarNotas;
    private EditText edtNota1;
    private TableLayout tblNota;

    private String parametro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atribuicao_notas);

        this.bd = BancodeDados.Sharedinstance(this);

        Bundle args = getIntent().getExtras();
        parametro = args.getString("Chave");

        tblNota = (TableLayout) findViewById(R.id.tableLayoutAtribuicaoNotaAlunoListagem);
        btSalvarNotas = (Button) findViewById(R.id.btXmlSalvarNotas);
        final Intent listagemAlunos = new Intent(this, ListarAlunos.class);


        carregarLista();
        btSalvarNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(listagemAlunos);

            }
        });

    }

    public void carregarLista() {


        final int codPeriodo = bd.verificarPeriodo(Integer.parseInt(parametro));


        disciplinaAlunoList = bd.listarDisciplinasPeriodo(codPeriodo);

        for (Disciplinas di : disciplinaAlunoList) {

            adicionarBlocoDeRegistro(di);

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void adicionarBlocoDeRegistro(final Disciplinas disciplina) {


        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayoutAtribuicaoNotaAlunoListagem);
        TableRow tr = new TableRow(this);
        TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(10, 10, 10, 10);
        tr.setLayoutParams(lp);

        TableRow.LayoutParams lPtr = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        lPtr.setMargins(-50, 0, 50, 0);

        TableRow.LayoutParams lPtr2 = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        lPtr2.setMargins(20, 0, -20, 0);

        TableRow.LayoutParams EdTextLP = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        EdTextLP.setMargins(0, 0, 0, 0);
        //lPtr.gravity=Gravity.CENTER;

        GradientDrawable gdB = new GradientDrawable();
        gdB.setColor(0xFFFFFFFF); // Changes this drawbale to use a single color instead of a gradient
        gdB.setCornerRadius(0);
        gdB.setStroke(20, 0xFFFFFFFF);

        Button editarBtA = new Button(this);
        editarBtA.setText(disciplina.getEntNomeDisciplina());
        editarBtA.setBackground(gdB);
        editarBtA.setTextSize(7);
        editarBtA.setLayoutParams(lPtr);
        tr.addView(editarBtA);

        int idDisc = disciplina.get_CodDisciplina();
        int idAl = Integer.parseInt(parametro);
        Nota nota = bd.verificarRegistroNotas(idDisc, idAl);

        final EditText editTextNota1 = new EditText(this);
        if (!Objects.isNull(nota)) {
            if (nota.getNota1() != 0.0) {
                editTextNota1.setText(nota.getNota1().toString());
            }
        }

        editTextNota1.setLayoutParams(lPtr);
        tr.addView(editTextNota1);


        final EditText editTextNota2 = new EditText(this);
        if (!Objects.isNull(nota)) {
            if (nota.getNota2() != 0.0) {
                editTextNota2.setText(nota.getNota2().toString());
            }
        }
        editTextNota2.setLayoutParams(lPtr2);
        tr.addView(editTextNota2);

        Button editarBtF = new Button(this);
        editarBtF.setText("Atribuir");
        editarBtF.setBackground(gdB);
        editarBtF.setTextSize(7);
        editarBtF.setLayoutParams(lPtr2);

        tr.addView(editarBtF);


        tableLayout.addView(tr);

        editarBtF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nota1 = editTextNota1.getText().toString();
                String nota2 = editTextNota2.getText().toString();
                int idDisciplina = disciplina.get_CodDisciplina();
                int idAluno = Integer.parseInt(parametro);

                bd.adicionarNota(nota1, nota2, idDisciplina, idAluno);


                Toast toast = Toast.makeText(getApplicationContext(), "Nota(s) atribuida(s)", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, -345);
                toast.show();
            }
        });


        editTextNota2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
//ele não volta ao normal depois que ficar falso
                    if (TextUtils.isEmpty(editTextNota1.getText().toString())) {
                        editTextNota2.setEnabled(false);
                    } else {
                        editTextNota2.setEnabled(true);
                    }

                    return false;
                }


                return false;
            }
        });
        editTextNota1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    editTextNota2.setEnabled(true);


                }

                return false;
            }
        });


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
