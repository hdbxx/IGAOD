package com.edu.iga.genetics.policy;

import com.edu.iga.genetics.Chromosome;
import com.edu.iga.genetics.GeneticAlgorithm;
import com.edu.iga.genetics.Population;
import com.edu.iga.genetics.Selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LinearRankingSelection extends Selection {
    @Override
    protected Chromosome select(Population population) {
        List<Chromosome> chromosomes = population.getChromosomes();
        int sum=0;
        for(int i=0;i<chromosomes.size();i++){
            sum += chromosomes.get(i).getFitness();
        }

        List<Chromosome> sortList = new ArrayList<>(chromosomes);

        Collections.sort(sortList,new Comparator<Chromosome>(){
            @Override
            public int compare(Chromosome c1, Chromosome c2) {
                int diff=c1.getFitness()-c2.getFitness();
                if(diff>0){
                    return 1;
                }
                else if(diff<0){
                    return -1;
                }
                return 0;
            }
        });

        List<Double> probability = new ArrayList<>(chromosomes.size());
        double min= (double)sortList.get(0).getFitness()/(double)sum;
        double max= (double)sortList.get(sortList.size()-1).getFitness()/(double)sum;

        for(int i=0;i<sortList.size();i++){
            double p=min+(max-min)*((double)i / (double)(sortList.size() - 1));
            probability.add(p);
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
