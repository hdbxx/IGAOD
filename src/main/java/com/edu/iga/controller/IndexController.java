package com.edu.iga.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.iga.domain.Experiment;
import com.edu.iga.service.IExperimentService;
import com.edu.iga.vo.ConfigVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private IExperimentService experimentService;

    private String prefix = "";

    @GetMapping("")
    public String index(ModelMap map) {
        return prefix + "index";
    }

    @GetMapping("vaseDesign")
    public String vaseVase(ModelMap map) {
        return prefix + "vaseDesign";
    }

    @GetMapping("dishDesign")
    public String dishDesign(ModelMap map) {
        return prefix + "dishDesign";
    }

    @GetMapping("chart")
    public String chart(ModelMap map) {
        return prefix + "chart";
    }

    @PostMapping("/save")
    @ResponseBody
    public Map<String, String> save(String configStr, String data) {
        ConfigVO config = JSONObject.parseObject(configStr, ConfigVO.class);
        Experiment experiment = new Experiment();
        experiment.setSelectionOperator(config.getSelectionOperator());
        experiment.setCrossoverOperator(config.getCrossoverOperator());
        experiment.setMutationOperator(config.getMutationOperator());
        experiment.setCrossover(config.getCrossover());
        experiment.setMutation(config.getMutation());
        experiment.setCode(config.getCode());
        experiment.setSize(config.getSize());
        experiment.setGeneration(config.getGeneration());
        experiment.setData(data);
        experiment.setApp(config.getApp());
        experimentService.save(experiment);
        Map<String, String> result = new HashMap<>();
        result.put("code", "1");
        return result;
    }

    @PostMapping("/newestData")
    @ResponseBody
    public String newestData(String configStr) {
        ConfigVO config = JSONObject.parseObject(configStr, ConfigVO.class);
        QueryWrapper<Experiment> query = new QueryWrapper();
        query.eq("selection_operator", config.getSelectionOperator());
        query.eq("crossover_operator", config.getCrossoverOperator());
        query.eq("mutation_operator", config.getMutationOperator());

        query.eq("code", config.getCode());
        query.eq("size", config.getSize());
        query.eq("generation", config.getGeneration());
        query.eq("crossover", config.getCrossover());
        query.eq("mutation", config.getMutation());
        query.eq("app", config.getApp());
        PageHelper.startPage(1, 1, "id desc");
        Experiment experiment = experimentService.getOne(query);
        if (experiment == null) {
            return "[]";
        }
        return experiment.getData();
    }

    @PostMapping("/historyData")
    @ResponseBody
    public List<String> historyData(String configStr) {
        ConfigVO config = JSONObject.parseObject(configStr, ConfigVO.class);
        QueryWrapper<Experiment> query = new QueryWrapper();
        query.eq("selection_operator", config.getSelectionOperator());
        query.eq("crossover_operator", config.getCrossoverOperator());
        query.eq("mutation_operator", config.getMutationOperator());

        query.eq("code", config.getCode());
        query.eq("size", config.getSize());
        query.eq("generation", config.getGeneration());
        query.eq("crossover", config.getCrossover());
        query.eq("mutation", config.getMutation());
        query.eq("app", config.getApp());

        List<Experiment> list = experimentService.list(query);
        List<String> result = new ArrayList<>();
        for (Experiment experiment : list) {
            result.add(experiment.getData());
        }
        return result;
    }


}
