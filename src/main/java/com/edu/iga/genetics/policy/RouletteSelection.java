package com.edu.iga.genetics.policy;

import com.edu.iga.genetics.Chromosome;
import com.edu.iga.genetics.GeneticAlgorithm;
import com.edu.iga.genetics.Population;
import com.edu.iga.genetics.Selection;
import java.util.ArrayList;
import java.util.List;

public class RouletteSelection extends Selection {

    @Override
    protected Chromosome select(Population population) {
        List<Chromosome> chromosomes = population.getChromosomes();
        int sum=0;
        for(int i=0;i<chromosomes.size();i++){
            sum += chromosomes.get(i).getFitness();
        }
        List<Double> probability = new ArrayList<>(chromosomes.size());
        for(int i=0;i<chromosomes.size();i++){
            Integer fitness = chromosomes.get(i).getFitness();
            probability.add( (double)fitness/(double)sum);
        }

        double random = GeneticAlgorithm.randomGenerator.nextDouble();
        double m=0;
        for(int i=0;i<probability.size();i++){
            m += probability.get(i);
            if(random<m){
                return chromosomes.get(i);
            }
        }
        return null;
    }

}
