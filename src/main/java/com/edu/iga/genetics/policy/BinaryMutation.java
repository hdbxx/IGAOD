package com.edu.iga.genetics.policy;

import com.edu.iga.genetics.Chromosome;
import com.edu.iga.genetics.GeneticAlgorithm;
import com.edu.iga.genetics.Mutation;

import java.util.ArrayList;
import java.util.List;

public class BinaryMutation extends Mutation {


    @Override
    protected Chromosome mutate(Chromosome chromosome) {
        List<Integer> newSequence = new ArrayList<Integer>(chromosome.getSequence());
        int index = GeneticAlgorithm.randomGenerator.nextInt(newSequence.size());
        newSequence.set(index, newSequence.get(index)== 0 ? 1 : 0);
        return new Chromosome(newSequence);
    }

}
