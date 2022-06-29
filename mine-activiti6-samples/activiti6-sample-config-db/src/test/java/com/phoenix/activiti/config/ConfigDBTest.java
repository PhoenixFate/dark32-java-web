package com.phoenix.activiti.config;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库配置
 */
public class ConfigDBTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDBTest.class);

    @Test
    public void testConfigDB() {
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault();
        LOGGER.info("configuration = {}", configuration);
        ProcessEngine processEngine = configuration.buildProcessEngine();
        LOGGER.info("获取的流程引擎 {}", processEngine.getName());
        processEngine.close();
    }

    @Test
    public void testConfigDB2() {
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti-druid.cfg.xml");
        LOGGER.info("configuration = {}", configuration);
        ProcessEngine processEngine = configuration.buildProcessEngine();
        LOGGER.info("获取的流程引擎 {}", processEngine.getName());
        processEngine.close();
    }

}
