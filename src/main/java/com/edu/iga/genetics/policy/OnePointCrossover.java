package com.edu.iga.genetics.policy;

import com.edu.iga.genetics.Chromosome;
import com.edu.iga.genetics.ChromosomePair;
import com.edu.iga.genetics.Crossover;
import com.edu.iga.genetics.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class OnePointCrossover<T> extends Crossover {

    @Override
    public ChromosomePair crossover(Chromosome c1, Chromosome c2) {

        List<T> sequence1 = c1.getSequence();
        List<T> sequence2 = c2.getSequence();
        List<T> child1 = new ArrayList(sequence1.size());
        List<T> child2 = new ArrayList(sequence2.size());
        int length = sequence1.size();
        int crossoverIndex ;
        if(length<=2){
            crossoverIndex= GeneticAlgorithm.randomGenerator.nextInt(length);
        }else{
            crossoverIndex= 1 + GeneticAlgorithm.randomGenerator.nextInt(length-2);
        }

        int i;
        for(i = 0; i < crossoverIndex; ++i) {
            child1.add(sequence1.get(i));
            child2.add(sequence2.get(i));
        }

        for(i = crossoverIndex; i < length; ++i) {
            child1.add(sequence2.get(i));
            child2.add(sequence1.get(i));
        }
        return new ChromosomePair(new Chromosome(child1), new Chromosome(child2));
    }

}
