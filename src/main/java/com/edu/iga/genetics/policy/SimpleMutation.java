package com.edu.iga.genetics.policy;

import com.edu.iga.genetics.Chromosome;
import com.edu.iga.genetics.GeneticAlgorithm;
import com.edu.iga.genetics.Mutation;

import java.util.ArrayList;
import java.util.List;

public class SimpleMutation extends Mutation {

    private List<Integer> mutationRange;
    private Integer code;

    public SimpleMutation(List<Integer> mutationRange, Integer code) {
        this.mutationRange = mutationRange;
        this.code = code;
    }

    @Override
    protected Chromosome mutate(Chromosome chromosome) {
        List<Integer> newSequence = new ArrayList<Integer>(chromosome.getSequence());
        int index = GeneticAlgorithm.randomGenerator.nextInt(newSequence.size());
        if (this.code == 2) {
            newSequence.set(index, newSequence.get(index) == 0 ? 1 : 0);
        } else {
            newSequence.set(index, GeneticAlgorithm.randomGenerator.nextInt(mutationRange.get(index)));
        }
        return new Chromosome(newSequence);
    }

}
