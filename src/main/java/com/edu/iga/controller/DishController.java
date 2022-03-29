package com.edu.iga.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.edu.iga.Utils.NumberUtils;
import com.edu.iga.genetics.*;
import com.edu.iga.genetics.policy.*;
import com.edu.iga.vo.ConfigVO;
import com.edu.iga.vo.DishVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/dish")
public class DishController {

    @PostMapping("/evolution")
    @ResponseBody
    public List<DishVO> evolution(String configStr,String populationStr){
        List<DishVO> list = JSONArray.parseArray(populationStr, DishVO.class);
        ConfigVO config = JSONObject.parseObject(configStr, ConfigVO.class);

        List<Chromosome> chromosomes = new ArrayList<>();

        if(config.getCode()==10){
            //decimal encoding
            for(DishVO dish:list){
                List<Integer> sequence = new ArrayList<>();
                sequence.add(dish.getP1());
                sequence.add(dish.getP2());
                chromosomes.add(new Chromosome<Integer>(sequence,dish.getFitness()));
            }
        }else{
            //binary encoding
            for(DishVO dish:list){
                List<Integer> sequence = new ArrayList<>();
                String binaryStr = NumberUtils.decimal2Binary(dish.getP1(), 1);
                binaryStr += NumberUtils.decimal2Binary(dish.getP2(), 4);
                char[] chars = binaryStr.toCharArray();
                for(int i=0;i<chars.length;i++){
                    sequence.add(Integer.valueOf(chars[i]+""));
                }
                chromosomes.add(new Chromosome<Integer>(sequence,dish.getFitness()));
            }
        }

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
                crossover = new OnePointCrossover();
                break;
        }

        Mutation mutation = null;
        List<Integer> parameterRange=new ArrayList<>(Arrays.asList(2,16));
        switch (config.getMutationOperator()) {
            case 1:
                mutation = new SimpleMutation(parameterRange,config.getCode());
                break;
            case 2:
                mutation = new UniformMutation(parameterRange,config.getCode(),0.1);
                break;
        }

        GeneticAlgorithm ga = new GeneticAlgorithm(selection, crossover, mutation, config.getCrossover(), config.getMutation());
        Population newPopulation = ga.evolve(new Population(chromosomes));

        //decoding
        List<Chromosome> newChromosomes = newPopulation.getChromosomes();

        List<DishVO> dishes = new ArrayList<>();
        if(config.getCode()==10){
            for(Chromosome chromosome:newChromosomes){
                List<Integer> sequence = chromosome.getSequence();
                DishVO dishVO = new DishVO();
                dishVO.setP1(sequence.get(0));
                dishVO.setP2(sequence.get(1));
                dishes.add(dishVO);
            }
        }else{
            for(Chromosome chromosome:newChromosomes){
                List<Integer> sequence = chromosome.getSequence();
                DishVO dishVO = new DishVO();
                int p1=Integer.valueOf(StringUtils.join(sequence.subList(0,1), ""),2);
                dishVO.setP1(p1);
                int p2=Integer.valueOf(StringUtils.join(sequence.subList(1,5), ""),2);
                dishVO.setP2(p2);
                dishes.add(dishVO);
            }
        }

        return dishes;
    }

}
