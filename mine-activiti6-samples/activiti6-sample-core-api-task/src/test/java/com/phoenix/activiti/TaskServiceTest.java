package com.phoenix.activiti;

import com.google.common.collect.Maps;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.*;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    /**
     * 通过key启动流程引擎
     */
    @Test
    @Deployment(resources = {"my-process-task.bpmn20.xml"})
    public void testTaskService() {
        //启动流程
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("message", "my task message");
        activitiRule.getRuntimeService().startProcessInstanceByKey("my-process", variables);
        TaskService taskService = activitiRule.getTaskService();
        Task task = taskService.createTaskQuery().singleResult();
        LOGGER.info("task = {}", ToStringBuilder.reflectionToString(task, ToStringStyle.JSON_STYLE));
        LOGGER.info("task.description = {}", task.getDescription());

        //taskService设置变量
        taskService.setVariable(task.getId(), "key1", "value1");
        taskService.setVariableLocal(task.getId(), "localKey1", "localValue1");

        //获取变量
        Map<String, Object> taskServiceVariables = taskService.getVariables(task.getId());
        Map<String, Object> taskServiceVariablesLocal = taskService.getVariablesLocal(task.getId());

        //根据流程获取变量
        Map<String, Object> processVariables = activitiRule.getRuntimeService().getVariables(task.getExecutionId());
        LOGGER.info("taskServiceVariables = {}", taskServiceVariables);
        LOGGER.info("taskServiceVariablesLocal = {}", taskServiceVariablesLocal);
        LOGGER.info("processVariables = {}", processVariables);


        Map<String, Object> completeVariables = Maps.newConcurrentMap();
        completeVariables.put("cKey1", "cValue1");
        taskService.complete(task.getId(), completeVariables);

        Task task1 = taskService.createTaskQuery().taskId(task.getId()).singleResult();
        LOGGER.info("task1 = {}", task1);


    }

    /**
     * 设置用户、权限相关
     */
    @Test
    @Deployment(resources = {"my-process-task.bpmn20.xml"})
    public void testTaskServiceUser() {
        //启动流程
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("message", "my task message");
        activitiRule.getRuntimeService().startProcessInstanceByKey("my-process", variables);
        TaskService taskService = activitiRule.getTaskService();
        Task task = taskService.createTaskQuery().singleResult();
        LOGGER.info("task = {}", ToStringBuilder.reflectionToString(task, ToStringStyle.JSON_STYLE));
        LOGGER.info("task.description = {}", task.getDescription());


        //设置拥有者 （流程发起人）
        taskService.setOwner(task.getId(), "user1");
        //如果直接通过setAssignee，来设置办理人，则会替换掉原来掉办理人，（原来掉代办方会发现这个任务不是自己代办了）
        //taskService.setAssignee(task.getId(), "jimmy");

        //通过claim设置办理人
        //筛选候选人中有jimmy，但没有指定代办人的任务
        List<Task> taskList = taskService.createTaskQuery().taskCandidateUser("jimmy").taskUnassigned().listPage(0, 100);
        for (Task task1 : taskList) {
            try {
                //如果task已经指定了代办人了，会抛出异常
                //将任务的代办人指定为jimmy
                taskService.claim(task1.getId(), "jimmy");
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        //查询task的所有候选人、拥有者
        List<IdentityLink> identityLinksForTask = taskService.getIdentityLinksForTask(task.getId());
        for (IdentityLink identityLink : identityLinksForTask) {
            LOGGER.info("identityLink = {}", identityLink);
        }

        //查询jimmy为代办人的所有任务
        List<Task> jimmyList = taskService.createTaskQuery().taskAssignee("jimmy").listPage(0, 100);
        for (Task jimmy : jimmyList) {
            Map<String, Object> vars = Maps.newHashMap();
            vars.put("ckey1", "cvalue1");
            //完成jimmy为代办人的任务
            taskService.complete(jimmy.getId(), vars);
        }

        List<Task> jimmys = taskService.createTaskQuery().taskAssignee("jimmy").listPage(0, 100);
        LOGGER.info("是否存在 {}", CollectionUtils.isEmpty(jimmys));

    }

    /**
     * 添加附件
     */
    @Test
    @Deployment(resources = {"my-process-task.bpmn20.xml"})
    public void testTaskAttachment() {
        //启动流程
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("message", "my task message");
        activitiRule.getRuntimeService().startProcessInstanceByKey("my-process", variables);
        TaskService taskService = activitiRule.getTaskService();
        Task task = taskService.createTaskQuery().singleResult();
        LOGGER.info("task = {}", ToStringBuilder.reflectionToString(task, ToStringStyle.JSON_STYLE));
        LOGGER.info("task.description = {}", task.getDescription());

        taskService.createAttachment("url", task.getId(),
                task.getProcessInstanceId(),
                "attachmentName", "attachmentDescription",
                "/url/test.png");

        List<Attachment> taskAttachments = taskService.getTaskAttachments(task.getId());
        for (Attachment taskAttachment : taskAttachments) {
            LOGGER.info("taskAttachment = {}", ToStringBuilder.reflectionToString(taskAttachment, ToStringStyle.JSON_STYLE));
        }

    }


    /**
     * 添加评论
     */
    @Test
    @Deployment(resources = {"my-process-task.bpmn20.xml"})
    public void testTaskComment() {
        //启动流程
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("message", "my task message");
        activitiRule.getRuntimeService().startProcessInstanceByKey("my-process", variables);
        TaskService taskService = activitiRule.getTaskService();
        Task task = taskService.createTaskQuery().singleResult();
        LOGGER.info("task = {}", ToStringBuilder.reflectionToString(task, ToStringStyle.JSON_STYLE));
        LOGGER.info("task.description = {}", task.getDescription());

        taskService.setOwner(task.getId(), "user1");
        taskService.setAssignee(task.getId(), "jimmy");

        taskService.addComment(task.getId(), task.getProcessInstanceId(), "record comment message one");
        taskService.addComment(task.getId(), task.getProcessInstanceId(), "record comment message two");

        List<Comment> taskComments = taskService.getTaskComments(task.getId());
        for (Comment taskComment : taskComments) {
            LOGGER.info("taskComment = {}", ToStringBuilder.reflectionToString(taskComment, ToStringStyle.JSON_STYLE));
        }

        List<Event> taskEvents = taskService.getTaskEvents(task.getId());
        for (Event taskEvent : taskEvents) {
            LOGGER.info("taskEvent = {}", ToStringBuilder.reflectionToString(taskEvent, ToStringStyle.JSON_STYLE));
        }


    }

}
