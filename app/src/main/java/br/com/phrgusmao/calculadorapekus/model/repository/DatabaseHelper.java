package br.com.phrgusmao.calculadorapekus.model.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.phrgusmao.calculadorapekus.model.Result;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "calculadora.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_RESULTS = "results";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_VALUE1 = "value1";
    private static final String COLUMN_VALUE2 = "value2";
    private static final String COLUMN_OPERATION = "operation";
    private static final String COLUMN_RESULT = "result";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação da tabela
        String createTable = "CREATE TABLE " + TABLE_RESULTS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_VALUE1 + " REAL, " +
                COLUMN_VALUE2 + " REAL, " +
                COLUMN_OPERATION + " TEXT, " +
                COLUMN_RESULT + " REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESULTS);
        onCreate(db);
    }

    // Método para adicionar resultado
    public void addResult(Result result) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction(); // Início da transação
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_DATE, result.getCreatedIn());
            values.put(COLUMN_VALUE1, result.getValue01());
            values.put(COLUMN_VALUE2, result.getValue02());
            values.put(COLUMN_OPERATION, result.getOperation());
            values.put(COLUMN_RESULT, result.getResultOperation());

            long newId = db.insert(TABLE_RESULTS, null, values);
            if (newId == -1) {
                Log.e("DatabaseHelper", "Erro ao inserir resultado");
            } else {
                Log.d("DatabaseHelper", "Resultado inserido com ID: " + newId);
            }
            db.setTransactionSuccessful(); // Marca a transação como bem-sucedida
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Erro ao adicionar resultado: " + e.getMessage());
        } finally {
            db.endTransaction(); // Finaliza a transação
            db.close();
        }
    }


    // Método para listar resultados
    public List<Result> getAllResults() {
        List<Result> resultList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_RESULTS, null);

        if (cursor.moveToFirst()) {
            do {
                Result result = new Result(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_VALUE1)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_VALUE2)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OPERATION)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_RESULT))
                );
                Log.d("DatabaseHelper", "Resultado recuperado: " + result);
                resultList.add(result);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return resultList;
    }

    // Método para excluir um resultado pelo ID
    public void deleteResult(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction(); // Início da transação
        try {
            int rowsDeleted = db.delete(TABLE_RESULTS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
            if (rowsDeleted > 0) {
                Log.d("DatabaseHelper", "Resultado com ID " + id + " excluído com sucesso.");
            } else {
                Log.e("DatabaseHelper", "Nenhum resultado encontrado com o ID " + id);
            }
            db.setTransactionSuccessful(); // Marca a transação como bem-sucedida
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Erro ao excluir resultado: " + e.getMessage());
        } finally {
            db.endTransaction(); // Finaliza a transação
            db.close();
        }
    }

}
