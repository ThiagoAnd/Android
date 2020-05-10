import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancodeDados  extends SQLiteOpenHelper {

    private static BancodeDados instance = null;

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "BancoInteracao.sqlite";

    private Context context;

    public BancodeDados(Context context){

        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    public static BancodeDados Sharedinstance(Context context){
      if (instance == null){
          instance = new BancodeDados(context);
      }
      return instance;

    };

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query1;
        query1 = "CREATE TABLE IF NOT EXISTS TbAluno (_Matricula INTEGER PRIMARY KEY,NomeAluno TEXT NOT NULL, Email TEXT, Telefone TEXT)";
        db.execSQL(query1);

        query1 = "CREATE TABLE IF NOT EXISTS TbDisciplinas (_CodDisciplina INTEGER PRIMARY KEY,entNomeDisciplina TEXT NOT NULL, entCargaHoraria TEXT)";
        db.execSQL(query1);

        query1 = "CREATE TABLE IF NOT EXISTS TbAlunoDisciplina (_CodAlunoDisciplina INTEGER PRIMARY KEY,_CodDisciplina INTEGER NOT NULL,_Matricula INTEGER NOT NULL )";
        db.execSQL(query1);

    }

    public void insertAluno (Aluno aluno){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("_Matricula",aluno.get_Matricula());
        values.put("entNomeAluno", aluno.getEntNomeAluno());
        values.put("entEmail", aluno.getEntEmail());
        values.put("entTelefone", aluno.getEntTelefone());

        long insert = db.insert("TbAluno", "", values);
        db.close();

    }

    public void insertDisciplina (Disciplinas disciplina){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("_CodDisciplina",disciplina.get_CodDisciplina());
        values.put("entNomeDisciplina", disciplina.getEntNomeDisciplina());
        values.put("entCargaHoraria", disciplina.getEntCargaHoraria());

        long insert = db.insert("TbAluno", "", values);
        db.close();

    };

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }
}
