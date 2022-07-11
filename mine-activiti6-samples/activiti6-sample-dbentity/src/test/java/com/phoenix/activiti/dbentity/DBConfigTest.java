package com.phoenix.activiti.dbentity;

import com.phoenix.activiti.TaskServiceTest;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class DBConfigTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBConfigTest.class);

    @Test
    public void testDBConfig() {
        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti-mysql.cfg.xml")
                .buildProcessEngine();
        ManagementService managementService = processEngine.getManagementService();
        Map<String, Long> tableCount = managementService.getTableCount();
        ArrayList<String> tableNames = Lists.newArrayList(tableCount.keySet());
        Collections.sort(tableNames);
        for (String tableName : tableNames) {
            LOGGER.info("table name = {}", tableName);
        }

        LOGGER.info("table size = {}", tableNames.size());

    }

    @Test
    public void dropTable() {
        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti-mysql.cfg.xml")
                .buildProcessEngine();
        ManagementService managementService = processEngine.getManagementService();

        managementService.executeCommand(new Command<Object>() {
            @Override
            public Object execute(CommandContext commandContext) {
                commandContext.getDbSqlSession().dbSchemaDrop();
                LOGGER.info("删除表结构");
                return null;
            }
        });


    }


}
