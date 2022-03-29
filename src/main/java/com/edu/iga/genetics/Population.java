package com.edu.iga.genetics;

import java.util.Collections;
import java.util.List;

public class Population {

    private List<Chromosome> chromosomes;

    public Population(List<Chromosome> chromosomes) {
        this.chromosomes = chromosomes;
    }

    public int size(){
        return chromosomes.size();
    }

    public Chromosome getChromosome(int index){
        return chromosomes.get(index);
    }

    public List<Chromosome> getChromosomes(){
        return Collections.unmodifiableList(this.chromosomes);
    }



}
