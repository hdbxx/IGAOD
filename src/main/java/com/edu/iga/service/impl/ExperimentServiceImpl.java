package com.edu.iga.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.iga.domain.Experiment;
import com.edu.iga.mapper.ExperimentMapper;
import com.edu.iga.service.IExperimentService;
import org.springframework.stereotype.Service;

@Service
public class ExperimentServiceImpl extends ServiceImpl<ExperimentMapper, Experiment> implements IExperimentService {
}
