package br.com.phrgusmao.calculadorapekus.model.repository;

import android.content.Context;
import java.util.List;
import br.com.phrgusmao.calculadorapekus.model.Result;

public class ResultRepository {

    private DatabaseHelper dbHelper;

    public ResultRepository(Context context) {
        this.dbHelper = new DatabaseHelper(context);
    }

    // Adiciona um resultado ao banco de dados
    public void addResult(Result result) {
        dbHelper.addResult(result);
    }

    // Lista todos os resultados do banco de dados
    public List<Result> listResults() {
        return dbHelper.getAllResults();
    }

    // Exclui um resultado do banco de dados
    public void deleteResult(int id) {
        dbHelper.deleteResult(id);
    }
}
