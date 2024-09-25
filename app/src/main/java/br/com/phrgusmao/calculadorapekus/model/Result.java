package br.com.phrgusmao.calculadorapekus.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Result {
    private int id;
    private String createdIn;
    private double value01;
    private double value02;
    private String operation;
    private double resultOperation;

    public Result(String createdIn, double value01, double value02, String operation, double resultOperation) {
        this.createdIn = createdIn;
        this.value01 = value01;
        this.value02 = value02;
        this.operation = operation;
        this.resultOperation = round(resultOperation, 2); // Arredondando o resultado
    }

    public Result(int id, String createdIn, double value01, double value02, String operation, double resultOperation) {
        this.id = id;
        this.createdIn = createdIn;
        this.value01 = value01;
        this.value02 = value02;
        this.operation = operation;
        this.resultOperation = round(resultOperation, 2); // Arredondando o resultado
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedIn() {
        return createdIn;
    }

    public void setCreatedIn(String createdIn) {
        this.createdIn = createdIn;
    }

    public double getValue01() {
        return value01;
    }

    public void setValue01(double value01) {
        this.value01 = value01;
    }

    public double getValue02() {
        return value02;
    }

    public void setValue02(double value02) {
        this.value02 = value02;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getResultOperation() {
        return resultOperation;
    }

    public void setResultOperation(double result) {
        this.resultOperation = result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id + // Incluindo o ID no toString
                ", date='" + createdIn + '\'' +
                ", value1=" + value01 +
                ", value2=" + value02 +
                ", operation='" + operation + '\'' +
                ", result=" + resultOperation +
                '}';
    }

}
