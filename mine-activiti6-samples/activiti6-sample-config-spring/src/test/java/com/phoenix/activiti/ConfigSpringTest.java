package com.phoenix.activiti;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
//配置spring默认加载配置文件路径
@ContextConfiguration(locations = {"classpath:activiti-context.xml"})
public class ConfigSpringTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigSpringTest.class);

    @Rule
    @Autowired
    public ActivitiRule activitiRule;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void test() throws InterruptedException {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process");
        assertNotNull(processInstance);
        Task task = taskService.createTaskQuery().singleResult();
        taskService.complete(task.getId());
    }


}
