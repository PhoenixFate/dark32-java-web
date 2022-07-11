package com.phoenix.activiti;

import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    /**
     * 通过key启动流程引擎
     */
    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void testTaskService() {

    }



}
