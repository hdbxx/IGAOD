package com.edu.iga.genetics;

import java.util.List;

public class Chromosome<T> {

    private final List<T> sequence;
    private final Integer fitness;


    public Chromosome(List<T> list, Integer fitness) {
        this.sequence = list;
        this.fitness = fitness;
    }

    public Chromosome(List<T> list) {
        this.sequence = list;
        this.fitness = 5;
    }

    public List<T> getSequence(){
        return this.sequence;
    }

    public Integer getFitness(){
        return fitness;
    }

}
