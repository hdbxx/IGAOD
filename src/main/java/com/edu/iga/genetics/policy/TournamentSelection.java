package com.edu.iga.genetics.policy;

import com.edu.iga.genetics.Chromosome;
import com.edu.iga.genetics.GeneticAlgorithm;
import com.edu.iga.genetics.Population;
import com.edu.iga.genetics.Selection;

public class TournamentSelection extends Selection {

    private int entryNum;

    public TournamentSelection(int entryNum) {
        this.entryNum = entryNum;
    }

    @Override
    protected Chromosome select(Population population) {
        Chromosome bestChromosome = null;
        for (int i = 0; i < entryNum; i++) {
            int random = GeneticAlgorithm.randomGenerator.nextInt(population.size());
            if (i == 0) {
                bestChromosome = population.getChromosome(random);
                continue;
            }

            if (population.getChromosome(random).getFitness() > bestChromosome.getFitness()) {
                bestChromosome = population.getChromosome(random);
            }
        }
        return bestChromosome;
    }

}
