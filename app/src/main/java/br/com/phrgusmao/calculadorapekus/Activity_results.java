package br.com.phrgusmao.calculadorapekus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.phrgusmao.calculadorapekus.adapter.ResultAdapter;
import br.com.phrgusmao.calculadorapekus.model.Result;
import br.com.phrgusmao.calculadorapekus.model.repository.ResultRepository;

public class Activity_results extends AppCompatActivity implements ResultAdapter.OnResultDeleteListener {

    private RecyclerView recyclerView;
    private ResultAdapter resultAdapter;
    private ResultRepository resultRepository;
    private List<Result> resultList; // Mantenha a lista acessível
    private boolean isAscending = true; // Variável para controlar a ordem de classificação

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_results);

        ImageButton btn_add_more = findViewById(R.id.img_btn_add_more);
        btn_add_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_results.this, MainActivity.class);
                startActivity(intent);
            }
        });


        resultRepository = new ResultRepository(this);
        resultList = resultRepository.listResults(); // Recupera os resultados

        recyclerView = findViewById(R.id.recyler_view_results);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Cria o adapter com a interface de delete
        resultAdapter = new ResultAdapter(resultList, this);
        recyclerView.setAdapter(resultAdapter);

        // Botão para alternar a ordem
        ImageButton btnOrderToggle = findViewById(R.id.btn_order_toggle);
        btnOrderToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleOrder();
            }
        });
    }

    @Override
    public void onDelete(Result result) {
        // Remove do banco de dados
        resultRepository.deleteResult(result.getId()); // Certifique-se de que esse método exista
        // Remove da lista e notifica o adapter
        resultList.remove(result);
        resultAdapter.notifyDataSetChanged(); // Atualiza o RecyclerView

        Toast.makeText(Activity_results.this, "Item deletado", Toast.LENGTH_SHORT).show();
    }

    // Método para alternar a ordem
    private void toggleOrder() {
        ImageButton btn_order_toggle = findViewById(R.id.btn_order_toggle);

        if (isAscending) {
            Collections.sort(resultList, new Comparator<Result>() {
                @Override
                public int compare(Result r1, Result r2) {
                    return Long.compare(r2.getId(), r1.getId()); // Ordem decrescente
                }
            });
            Toast.makeText(this, "Ordenado em ordem decrescente", Toast.LENGTH_SHORT).show();
            // Muda a imagem para ordem decrescente
            btn_order_toggle.setImageResource(R.drawable.baseline_arrow_drop_down_24);
        } else {
            Collections.sort(resultList, new Comparator<Result>() {
                @Override
                public int compare(Result r1, Result r2) {
                    return Long.compare(r1.getId(), r2.getId()); // Ordem crescente
                }
            });
            Toast.makeText(this, "Ordenado em ordem crescente", Toast.LENGTH_SHORT).show();
            // Muda a imagem para ordem crescente
            btn_order_toggle.setImageResource(R.drawable.baseline_arrow_drop_up_24);
        }

        resultAdapter.notifyDataSetChanged(); // Atualiza o RecyclerView
        isAscending = !isAscending; // Alterna o estado
    }
}
