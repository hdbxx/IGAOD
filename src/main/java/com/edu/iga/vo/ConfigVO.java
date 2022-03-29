package com.edu.iga.vo;

import lombok.Data;

@Data
public class ConfigVO {

    private int selectionOperator;
    private int crossoverOperator;
    private int mutationOperator;
    private int code;
    private int size;
    private int generation;
    private double crossover;
    private double mutation;
    private int app;

    public ConfigVO() {
    }
}
