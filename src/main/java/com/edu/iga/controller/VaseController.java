package com.edu.iga.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.edu.iga.Utils.NumberUtils;
import com.edu.iga.genetics.*;
import com.edu.iga.genetics.policy.*;
import com.edu.iga.vo.ConfigVO;
import com.edu.iga.vo.VaseVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/vase")
public class VaseController extends BaseController{

    @PostMapping("/evolution")
    @ResponseBody
    public List<VaseVO> evolution(String configStr, String populationStr) {
        List<VaseVO> list = JSONArray.parseArray(populationStr, VaseVO.class);
        ConfigVO config = JSONObject.parseObject(configStr, ConfigVO.class);

        List<Chromosome> chromosomes = new ArrayList<>();

        if (config.getCode() == 10) {
            //decimal encoding
            for (VaseVO vase : list) {
                List<Integer> sequence = new ArrayList<>();
                sequence.add(vase.getP1());
                sequence.add(vase.getP2());
                sequence.add(vase.getP3());
                sequence.add(vase.getP4());
                sequence.add(vase.getP5());
                sequence.add(vase.getP6());
                sequence.add(vase.getP7());
                sequence.add(vase.getP8());
                sequence.add(vase.getP9());
                chromosomes.add(new Chromosome<Integer>(sequence, vase.getFitness()));
            }
        } else {
            //binary encoding
            for (VaseVO vase : list) {
                List<Integer> sequence = new ArrayList<>();
                String binaryStr = NumberUtils.decimal2Binary(vase.getP1(), 3);
                binaryStr += NumberUtils.decimal2Binary(vase.getP2(), 3);
                binaryStr += NumberUtils.decimal2Binary(vase.getP3(), 3);
                binaryStr += NumberUtils.decimal2Binary(vase.getP4(), 3);
                binaryStr += NumberUtils.decimal2Binary(vase.getP5(), 3);
                binaryStr += NumberUtils.decimal2Binary(vase.getP6(), 3);
                binaryStr += NumberUtils.decimal2Binary(vase.getP7(), 3);
                binaryStr += NumberUtils.decimal2Binary(vase.getP8(), 3);
                binaryStr += NumberUtils.decimal2Binary(vase.getP9(), 6);
                char[] chars = binaryStr.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    sequence.add(Integer.valueOf(chars[i] + ""));
                }
                chromosomes.add(new Chromosome<Integer>(sequence, vase.getFitness()));
            }
        }

        Selection selection = getSelection(config);

        Crossover crossover = getCrossover(config);


        List<Integer> parameterRange=new ArrayList<>(Arrays.asList(8,8,8,8,8,8,8,8,64));
        Mutation mutation = getMutation(config,parameterRange);

        double crossoverRate = config.getCrossover();
        double mutationRate = config.getMutation();

        GeneticAlgorithm ga = new GeneticAlgorithm(selection, crossover, mutation, crossoverRate, mutationRate);
        Population newPopulation = ga.evolve(new Population(chromosomes));

        //decoding
        List<Chromosome> newChromosomes = newPopulation.getChromosomes();

        List<VaseVO> vases = new ArrayList<>();
        if (config.getCode() == 10) {
            for (Chromosome chromosome : newChromosomes) {
                List<Integer> sequence = chromosome.getSequence();
                VaseVO vaseVO = new VaseVO();
                vaseVO.setP1(sequence.get(0));
                vaseVO.setP2(sequence.get(1));
                vaseVO.setP3(sequence.get(2));
                vaseVO.setP4(sequence.get(3));
                vaseVO.setP5(sequence.get(4));
                vaseVO.setP6(sequence.get(5));
                vaseVO.setP7(sequence.get(6));
                vaseVO.setP8(sequence.get(7));
                vaseVO.setP9(sequence.get(8));
                vases.add(vaseVO);
            }
        } else {
            for (Chromosome chromosome : newChromosomes) {
                List<Integer> sequence = chromosome.getSequence();
                VaseVO vaseVO = new VaseVO();
                int p1 = Integer.valueOf(StringUtils.join(sequence.subList(0, 3), ""), 2);
                vaseVO.setP1(p1);
                int p2 = Integer.valueOf(StringUtils.join(sequence.subList(3, 6), ""), 2);
                vaseVO.setP2(p2);
                int p3 = Integer.valueOf(StringUtils.join(sequence.subList(6, 9), ""), 2);
                vaseVO.setP3(p3);
                int p4 = Integer.valueOf(StringUtils.join(sequence.subList(9, 12), ""), 2);
                vaseVO.setP4(p4);
                int p5 = Integer.valueOf(StringUtils.join(sequence.subList(12, 15), ""), 2);
                vaseVO.setP5(p5);
                int p6 = Integer.valueOf(StringUtils.join(sequence.subList(15, 18), ""), 2);
                vaseVO.setP6(p6);
                int p7 = Integer.valueOf(StringUtils.join(sequence.subList(18, 21), ""), 2);
                vaseVO.setP7(p7);
                int p8 = Integer.valueOf(StringUtils.join(sequence.subList(21, 24), ""), 2);
                vaseVO.setP8(p8);
                int p9 = Integer.valueOf(StringUtils.join(sequence.subList(24, 30), ""), 2);
                vaseVO.setP9(p9);
                vases.add(vaseVO);
            }
        }

        return vases;
    }

}
