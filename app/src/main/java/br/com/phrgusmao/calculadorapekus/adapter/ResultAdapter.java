package br.com.phrgusmao.calculadorapekus.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.phrgusmao.calculadorapekus.R;
import br.com.phrgusmao.calculadorapekus.model.Result;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {
    private List<Result> resultList;
    private OnResultDeleteListener deleteListener;

    // Construtor atualizado para aceitar o listener
    public ResultAdapter(List<Result> resultList, OnResultDeleteListener deleteListener) {
        this.resultList = resultList;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_item, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        Result result = resultList.get(position);

        holder.lblId.setText(String.valueOf(result.getId()));
        holder.value01.setText(String.valueOf(result.getValue01()));
        holder.operator.setText(result.getOperation());
        holder.value02.setText(String.valueOf(result.getValue02()));
        holder.resultOperation.setText(String.valueOf(result.getResultOperation()));
        holder.lblCreatedIn.setText(result.getCreatedIn());

        // Configura o botão de deletar
        ImageButton btnDelete = holder.btnDelete;

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteListener.onDelete(result); // Chama o método do listener
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    // ViewHolder interno
    public static class ResultViewHolder extends RecyclerView.ViewHolder {
        TextView lblId, value01, operator, value02, resultOperation, lblCreatedIn;
        ImageButton btnDelete; // Adicione o ImageButton aqui

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            lblId = itemView.findViewById(R.id.lbl_id);
            value01 = itemView.findViewById(R.id.value01);
            operator = itemView.findViewById(R.id.operator);
            value02 = itemView.findViewById(R.id.value02);
            resultOperation = itemView.findViewById(R.id.operation_result);
            lblCreatedIn = itemView.findViewById(R.id.lbl_data);
            btnDelete = itemView.findViewById(R.id.btn_delete); // Inicialize o botão
        }
    }

    // Interface para deletar resultado
    public interface OnResultDeleteListener {
        void onDelete(Result result);
    }
}
