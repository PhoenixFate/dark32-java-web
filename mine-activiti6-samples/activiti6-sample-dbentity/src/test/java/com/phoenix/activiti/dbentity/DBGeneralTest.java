package com.phoenix.activiti.dbentity;

import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ByteArrayEntityImpl;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.test.ActivitiRule;
import org.assertj.core.util.Lists;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * 通用数据库的一些测试
 */
public class DBGeneralTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBGeneralTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-mysql.cfg.xml");

    @Test
    public void testByeArray() {
        activitiRule.getRepositoryService()
                .createDeployment().name("测试部署")
                .addClasspathResource("my-process.bpmn20.xml")
                .deploy();

    }

    @Test
    public void testByteArrayInsert() {
        ManagementService managementService = activitiRule.getManagementService();
        managementService.executeCommand(new Command<Object>() {
            @Override
            public Object execute(CommandContext commandContext) {
                ByteArrayEntityImpl entity = new ByteArrayEntityImpl();
                entity.setName("test");
                entity.setBytes("test message".getBytes());
                commandContext.getByteArrayEntityManager().insert(entity);
                return null;
            }
        });

    }


}
