package com.edu.iga.controller;

import com.edu.iga.genetics.Crossover;
import com.edu.iga.genetics.Mutation;
import com.edu.iga.genetics.Selection;
import com.edu.iga.genetics.policy.*;
import com.edu.iga.vo.ConfigVO;

import java.util.List;

public class BaseController {

    protected Selection getSelection(ConfigVO config){
        Selection selection = null;
        switch (config.getSelectionOperator()) {
            case 1:
                selection = new RouletteSelection();
                break;
            case 2:
                selection = new StochasticUniversalSelection();
                break;
            case 3:
                selection = new LinearRankingSelection();
                break;
            case 4:
                selection = new TournamentSelection(3);
                break;
        }
        return selection;
    }

    protected Crossover getCrossover(ConfigVO config){
        Crossover crossover = null;
        switch (config.getCrossoverOperator()) {
            case 1:
                crossover = new OnePointCrossover();
                break;
            case 2:
                crossover = new TwoPointCrossover<>();
                break;
            case 3:
                crossover = new UniformCrossover(0.5);
                break;
            case 4:
                crossover = new CycleCrossover();
                break;
        }
        return crossover;
    }

    protected Mutation getMutation(ConfigVO config, List<Integer> parameterRange){
        Mutation mutation = null;
        switch (config.getMutationOperator()) {
            case 1:
                mutation = new SimpleMutation(parameterRange,config.getCode());
                break;
            case 2:
                mutation = new UniformMutation(parameterRange,config.getCode(),0.1);
                break;
        }
        return mutation;
    }


}
