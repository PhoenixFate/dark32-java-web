package com.phoenix.activiti.dbentity;

import com.google.common.collect.Maps;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ByteArrayEntityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用数据库的一些测试
 */
public class DBHistoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBHistoryTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-mysql-history.cfg.xml");

    @Test
    public void testHistory() {
        activitiRule.getRepositoryService()
                .createDeployment().name("测试部署")
                .addClasspathResource("my-process.bpmn20.xml")
                .deploy();
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        Map<String, Object> variables = new HashMap<>();
        variables.put("key0", "value0");
        variables.put("key1", "value1");
        variables.put("key2", "value2");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process", variables);

        runtimeService.setVariable(processInstance.getId(), "key1", "value1_1");

        TaskService taskService = activitiRule.getTaskService();
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();

        taskService.setOwner(task.getId(), "user1");
        //添加附件
        taskService.createAttachment("url", task.getId(), processInstance.getId(), "attachmentName", "attachmentDescription", "/url/abc.doc");

        taskService.addComment(task.getId(), processInstance.getId(), "评论1");
        taskService.addComment(task.getId(), processInstance.getId(), "评论2");

        Map<String, String> properties = Maps.newHashMap();
        properties.put("key2", "value2_1");
        properties.put("key3", "value3_1");
        activitiRule.getFormService().submitTaskFormData(task.getId(), properties);

    }


}
