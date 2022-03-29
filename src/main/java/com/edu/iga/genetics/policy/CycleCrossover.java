package com.edu.iga.genetics.policy;

import com.edu.iga.Utils.NumberUtils;
import com.edu.iga.genetics.Chromosome;
import com.edu.iga.genetics.ChromosomePair;
import com.edu.iga.genetics.Crossover;
import com.edu.iga.genetics.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CycleCrossover<T> extends Crossover {

    @Override
    public ChromosomePair crossover(Chromosome c1, Chromosome c2) {
        List<T> sequence1 = c1.getSequence();
        List<T> sequence2 = c2.getSequence();

        List<T> child1 = new ArrayList<>(c2.getSequence());
        List<T> child2 = new ArrayList<>(c1.getSequence());

        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < sequence1.size(); i++) {
            list1.add(i);
        }
        List<Integer> list2 = NumberUtils.sample(list1, sequence1.size());

        List<Integer> cxList = new ArrayList<>();
        int d = GeneticAlgorithm.randomGenerator.nextInt(list2.size());
        cxList.add(d);
        for (int i = 0; i < list2.size(); i++) {
            int v = list2.get(d);
            if (cxList.contains(v)) {
                break;
            }
            cxList.add(v);
            d = v;
        }

        cxList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                } else if (o1 < o2) {
                    return -1;
                }
                return 0;
            }
        });

        for (int i = 0; i < cxList.size(); i++) {
            int ind = cxList.get(0);
            child1.set(ind, sequence1.get(ind));
            child2.set(ind, sequence2.get(ind));
        }

        return new ChromosomePair(new Chromosome(child1), new Chromosome(child2));
    }


}
