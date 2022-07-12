package com.phoenix.activiti.dbentity;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBConfigTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-mysql.cfg.xml");

    @Test
    public void testDeploy() {
        activitiRule.getRepositoryService().createDeployment().name("二级审批流程").addClasspathResource("second_approve.bpmn20.xml").deploy();
    }

    @Test
    public void testSuspend() {
        RepositoryService repositoryService = activitiRule.getRepositoryService();
        //挂起
        repositoryService.suspendProcessDefinitionById("second_approve:2:7504");
        boolean processDefinitionSuspended = repositoryService.isProcessDefinitionSuspended("second_approve:2:7504");
        LOGGER.info("is suspended: {}", processDefinitionSuspended);

    }
}
