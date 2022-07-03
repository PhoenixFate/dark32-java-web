package com.phoenix.activiti.config;

import org.activiti.engine.logging.LogMDC;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConfigMDCPrintDetailTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigMDCPrintDetailTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-mdc.cfg.xml");

    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void testMDCPrintDetail() {
        //打开mdc (正常过程不会打印mdc，还需要自己制造一个异常才回打印mdc)
        //LogMDC.setMDCEnabled(true);
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
        assertNotNull(processInstance);
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        System.out.println(task.getName());
        assertEquals("Activiti is awesome!", task.getName());
        activitiRule.getTaskService().complete(task.getId());
    }


}
