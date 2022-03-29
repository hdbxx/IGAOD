package com.edu.iga.genetics.policy;

import com.edu.iga.genetics.Chromosome;
import com.edu.iga.genetics.ChromosomePair;
import com.edu.iga.genetics.Crossover;
import com.edu.iga.genetics.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class TwoPointCrossover<T> extends Crossover {

    @Override
    public ChromosomePair crossover(Chromosome c1, Chromosome c2) {

        List<T> sequence1 = c1.getSequence();
        List<T> sequence2 = c2.getSequence();
        List<T> child1 = new ArrayList(sequence1.size());
        List<T> child2 = new ArrayList(sequence2.size());
        int length = sequence1.size();
        int crossoverIndex1=0;
        int crossoverIndex2=0;
        if(length>2){
            crossoverIndex1 = GeneticAlgorithm.randomGenerator.nextInt(length);
            do {
                crossoverIndex2 = GeneticAlgorithm.randomGenerator.nextInt(length);
            } while(crossoverIndex1 == crossoverIndex2);
        }

        if(crossoverIndex1>crossoverIndex2){
            int temp=crossoverIndex2;
            crossoverIndex2=crossoverIndex1;
            crossoverIndex1=temp;
        }

        int i;
        for(i = 0; i < crossoverIndex1; i++) {
            child1.add(sequence1.get(i));
            child2.add(sequence2.get(i));
        }

        for(i = crossoverIndex1; i < crossoverIndex2; i++) {
            child1.add(sequence2.get(i));
            child2.add(sequence1.get(i));
        }

        for(i = crossoverIndex2; i < length; i++) {
            child1.add(sequence1.get(i));
            child2.add(sequence2.get(i));
        }

        return new ChromosomePair(new Chromosome(child1), new Chromosome(child2));
    }

}
