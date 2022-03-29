package com.edu.iga.genetics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

    public static Random randomGenerator = new Random();
    private final Selection selection;
    private final Crossover crossover;
    private final Mutation mutation;

    private final double crossoverRate;
    private final double mutationRate;

    public GeneticAlgorithm(Selection selection, Crossover crossover, Mutation mutation, double crossoverRate, double mutationRate) {
        this.selection = selection;
        this.mutation = mutation;
        this.crossover = crossover;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
    }

    public Population evolve(Population population) {
        List<Chromosome> nextGeneration = new ArrayList<>();
        while (nextGeneration.size() < population.size()) {
            Chromosome c1 = selection.select(population);
            Chromosome c2 = selection.select(population);
            ChromosomePair pair = new ChromosomePair(c1, c2);
            if (randomGenerator.nextDouble() < crossoverRate) {
                pair = this.crossover.crossover(c1, c2);
            }
            Chromosome first = pair.getFirst();
            Chromosome second = pair.getSecond();
            if (randomGenerator.nextDouble() < mutationRate) {
                first = mutation.mutate(first);
            }
            if (randomGenerator.nextDouble() < mutationRate) {
                second = mutation.mutate(second);
            }
            nextGeneration.add(first);
            if (nextGeneration.size() < population.size()) {
                nextGeneration.add(second);
            }
        }
        return new Population(nextGeneration);
    }


}
