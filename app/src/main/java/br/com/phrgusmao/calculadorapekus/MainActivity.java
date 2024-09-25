package br.com.phrgusmao.calculadorapekus;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import br.com.phrgusmao.calculadorapekus.model.Result;
import br.com.phrgusmao.calculadorapekus.model.repository.ResultRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ResultRepository resultRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        resultRepository = new ResultRepository(this);

        // Salvando cálculo no banco de dados
        Button btn_add = findViewById(R.id.btn_op_add);
        Button btn_mult = findViewById(R.id.btn_op_mult);
        Button btn_subt = findViewById(R.id.btn_op_subt);
        Button btn_div = findViewById(R.id.btn_op_div);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextValue1 = findViewById(R.id.text_value01);
                EditText editTextValue2 = findViewById(R.id.text_value02);

                String strValue1 = editTextValue1.getText().toString();
                String strValue2 = editTextValue2.getText().toString();

                calculate(1, strValue1, strValue2);
            }
        });

        btn_mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextValue1 = findViewById(R.id.text_value01);
                EditText editTextValue2 = findViewById(R.id.text_value02);

                String strValue1 = editTextValue1.getText().toString();
                String strValue2 = editTextValue2.getText().toString();

                calculate(2, strValue1, strValue2);
            }
        });

        btn_subt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextValue1 = findViewById(R.id.text_value01);
                EditText editTextValue2 = findViewById(R.id.text_value02);

                String strValue1 = editTextValue1.getText().toString();
                String strValue2 = editTextValue2.getText().toString();

                calculate(3, strValue1, strValue2);
            }
        });

        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextValue1 = findViewById(R.id.text_value01);
                EditText editTextValue2 = findViewById(R.id.text_value02);

                String strValue1 = editTextValue1.getText().toString();
                String strValue2 = editTextValue2.getText().toString();

                calculate(4, strValue1, strValue2);
            }
        });

        Button btn_historic = findViewById(R.id.btn_historic);
        btn_historic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_results.class);
                startActivity(intent);
            }
        });
    }

    public void clearAll() {
        // Referência aos EditText
        EditText editTextValue01 = findViewById(R.id.text_value01);
        EditText editTextValue02 = findViewById(R.id.text_value02);

        // Limpa o conteúdo dos EditText
        if (editTextValue01 != null) {
            editTextValue01.setText(""); // Limpa o valor do primeiro campo
        }

        if (editTextValue02 != null) {
            editTextValue02.setText(""); // Limpa o valor do segundo campo
        }
    }

    public void calculate(int type, String strValue1, String strValue2){
        if (!strValue1.isEmpty() && !strValue2.isEmpty()){

            double value1 = Double.parseDouble(strValue1);
            double value2 = Double.parseDouble(strValue2);

            // Verifique se ambos os valores são zero
            if (value1 == 0 && value2 == 0) {
                Toast.makeText(MainActivity.this, "Ambos os valores não podem ser zero.", Toast.LENGTH_SHORT).show();
                return; // Não armazena se ambos os valores forem zero
            }

            String operation = "";
            double result = 0;

            switch (type){
                case 1:
                    operation = "+";
                    result = value1 + value2;
                    break;

                case 2:
                    operation = "*";
                    result = value1 * value2;
                    break;

                case 3:
                    operation = "-";
                    result = value1 - value2;
                    break;

                case 4:
                    operation = "/";
                    result = value1 / value2;
                    break;

                default:
                    operation = "";
                    result = 0;
                    break;
            }

            String currentDate = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(new Date());

            Result calculationResult = new Result(currentDate, value1, value2, operation, result);
            resultRepository.addResult(calculationResult);

            Toast.makeText(MainActivity.this, "Cálculo armazenado!", Toast.LENGTH_SHORT).show();

            clearAll();
        }else{
            Toast.makeText(MainActivity.this, "O campo não pode estar vazio", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
