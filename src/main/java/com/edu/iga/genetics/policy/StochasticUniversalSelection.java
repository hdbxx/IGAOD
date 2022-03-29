package com.edu.iga.genetics.policy;

import com.edu.iga.genetics.Chromosome;
import com.edu.iga.genetics.GeneticAlgorithm;
import com.edu.iga.genetics.Population;
import com.edu.iga.genetics.Selection;

public class StochasticUniversalSelection extends Selection {

    @Override
    protected Chromosome select(Population population) {
        int random=GeneticAlgorithm.randomGenerator.nextInt(population.size());
        return population.getChromosome(random);
    }

}
