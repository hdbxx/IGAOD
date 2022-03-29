package com.edu.iga.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "experiment")
public class Experiment {

    @TableId(type = IdType.AUTO)
    private Long id;

    private int selectionOperator;
    private int crossoverOperator;
    private int mutationOperator;
    private int code;
    private int size;
    private int generation;
    private double crossover;
    private double mutation;
    private int app;

    private String data;//Json

}
