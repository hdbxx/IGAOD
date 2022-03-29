package com.edu.iga.genetics.policy;

import com.edu.iga.genetics.Chromosome;
import com.edu.iga.genetics.ChromosomePair;
import com.edu.iga.genetics.Crossover;
import com.edu.iga.genetics.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UniformCrossover<T> extends Crossover {

    private final double ratio;//[0,1]

    public UniformCrossover(double ratio) {
        this.ratio = ratio;
    }

    @Override
    public ChromosomePair crossover(Chromosome c1, Chromosome c2) {
        List<T> sequence1 = c1.getSequence();
        List<T> sequence2 = c2.getSequence();
        List<T> child1 = new ArrayList(sequence1.size());
        List<T> child2 = new ArrayList(sequence2.size());
        int length = sequence1.size();
        Random random = GeneticAlgorithm.randomGenerator;

        for(int index = 0; index < length; ++index) {
            if (random.nextDouble() < this.ratio) {
                child1.add(sequence2.get(index));
                child2.add(sequence1.get(index));
            } else {
                child1.add(sequence1.get(index));
                child2.add(sequence2.get(index));
            }
        }

        return new ChromosomePair(new Chromosome(child1), new Chromosome(child2));
    }

}
