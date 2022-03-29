package com.edu.iga.genetics.policy;

import com.edu.iga.genetics.Chromosome;
import com.edu.iga.genetics.GeneticAlgorithm;
import com.edu.iga.genetics.Mutation;

import java.util.ArrayList;
import java.util.List;

public class UniformMutation extends Mutation {

    private List<Integer> mutationRange;
    private Integer code;
    private Double probability;

    public UniformMutation(List<Integer> mutationRange, Integer code, Double probability) {
        this.mutationRange = mutationRange;
        this.code = code;
        this.probability = probability;
    }

    @Override
    protected Chromosome mutate(Chromosome chromosome) {
        List<Integer> newSequence = new ArrayList<Integer>(chromosome.getSequence());
        for (int i = 0; i < newSequence.size(); i++) {
            double random = GeneticAlgorithm.randomGenerator.nextDouble();
            if (random < probability) {
                if (this.code == 2) {
                    newSequence.set(i, newSequence.get(i) == 0 ? 1 : 0);
                } else {
                    newSequence.set(i, GeneticAlgorithm.randomGenerator.nextInt(mutationRange.get(i)));
                }
            }
        }
        return new Chromosome(newSequence);
    }

}
