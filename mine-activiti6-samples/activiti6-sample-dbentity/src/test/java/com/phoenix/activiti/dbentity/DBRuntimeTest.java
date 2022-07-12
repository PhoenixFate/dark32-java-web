package com.phoenix.activiti.dbentity;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class DBRuntimeTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBConfigTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-mysql.cfg.xml");

    @Test
    public void testRuntime() {
        activitiRule.getRepositoryService().createDeployment().name("二级审批流程")
                .addClasspathResource("second_approve.bpmn20.xml")
                .deploy();

        Map<String, Object> variables = new HashMap<>();
        variables.put("key1", "value1");
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("second_approve", variables);
    }

    @Test
    public void testSetOwner() {
        TaskService taskService = activitiRule.getTaskService();
        Task task = taskService.createTaskQuery().processDefinitionKey("second_approve").singleResult();
        taskService.setOwner(task.getId(), "user1");
    }

    /**
     * 每次执行需要清理数据库，因为my-process只能有一份
     */
    @Test
    public void testMessage() {
        activitiRule.getRepositoryService().createDeployment()
                .addClasspathResource("my-process-message.bpmn20.xml").deploy();
    }

    /**
     * 每次执行需要清理数据库，因为my-process只能有一份
     */
    @Test
    public void testMessageReceived() {
        activitiRule.getRepositoryService().createDeployment()
                .addClasspathResource("my-process-message-receive.bpmn20.xml").deploy();
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
    }

    /**
     * 每次执行需要清理数据库，因为my-process只能有一份
     */
    @Test
    public void testJob() throws InterruptedException {
        activitiRule.getRepositoryService().createDeployment()
                .addClasspathResource("my-process-job.bpmn20.xml").deploy();
        Thread.sleep(1000 * 30L);
    }


}
