package com.phoenix.activiti;

import com.google.common.collect.Maps;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ExecutionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceBuilder;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class RuntimeServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RuntimeServiceTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    /**
     * 通过key启动流程引擎
     */
    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void testStartProcessByKey() {
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("key1", "value");
        //使用key来启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process", variables);
        LOGGER.info("processInstance = {}", processInstance);
    }

    /**
     * 通过id启动流程引擎
     */
    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void testStartProcessById() {
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        Map<String, Object> variables = Maps.newHashMap();
        //获取流程定义对象
        ProcessDefinition processDefinition = activitiRule.getRepositoryService().createProcessDefinitionQuery().singleResult();

        variables.put("key1", "value");
        //使用id来启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(), variables);
        LOGGER.info("processInstance = {}", processInstance);
    }


    /**
     * 通过ProcessInstanceBuilder来启动流程
     */
    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void testProcessInstanceBuilder() {
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("key1", "value");

        ProcessInstanceBuilder processInstanceBuilder = runtimeService.createProcessInstanceBuilder();
        ProcessInstance processInstance = processInstanceBuilder.businessKey("businessKey001")
                .processDefinitionKey("my-process")
                .variables(variables).start();

        LOGGER.info("processInstance = {}", processInstance);
    }

    /**
     * 查找和修改变量
     */
    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void testVariables() {
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        Map<String, Object> variables = Maps.newHashMap();
        //获取流程定义对象
        ProcessDefinition processDefinition = activitiRule.getRepositoryService().createProcessDefinitionQuery().singleResult();

        variables.put("key1", "value1");
        variables.put("key2", "value2");

        //使用id来启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(), variables);
        LOGGER.info("processInstance = {}", processInstance);

        //设置参数
        runtimeService.setVariable(processInstance.getId(), "key3", "value3");
        runtimeService.setVariable(processInstance.getId(), "key2", "abc");

        //获取参数
        Map<String, Object> variables1 = runtimeService.getVariables(processInstance.getId());
        LOGGER.info("variable1 = {}", variables1);

    }

    /**
     * 查询流程实例
     */
    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void testProcessInstanceQuery() {
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        Map<String, Object> variables = Maps.newHashMap();

        //使用id来启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceById("my-process", variables);
        LOGGER.info("processInstance = {}", processInstance);

        ProcessInstance processInstance1 = runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
        //processInstance1和processInstance是同一个
        LOGGER.info("processInstance = {}", processInstance1);

    }

    /**
     * 执行对象
     */
    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void testProcessExecuteQuery() {
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        Map<String, Object> variables = Maps.newHashMap();

        //使用id来启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceById("my-process", variables);
        LOGGER.info("processInstance = {}", processInstance);

        List<Execution> executionList = runtimeService.createExecutionQuery().listPage(0, 100);
        for (Execution execution : executionList) {
            LOGGER.info("execution = {}", execution);
        }

    }

    @Test
    @Deployment(resources = {"my-process-trigger.bpmn20.xml"})
    public void testTrigger() {
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process");

        Execution execution = runtimeService.createExecutionQuery().activityId("someTask").singleResult();
        LOGGER.info("execution = {}", execution);
        runtimeService.trigger(execution.getId());
        execution = runtimeService.createExecutionQuery().activityId("someTask").singleResult();
        LOGGER.info("execution = {}", execution);


    }

    /**
     * 接受信号
     */
    @Test
    @Deployment(resources = {"my-process-signal-receive.bpmn20.xml"})
    public void testSignalEventReceived() {
        RuntimeService runtimeService = activitiRule.getRuntimeService();

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process");
        Execution execution = runtimeService.createExecutionQuery().signalEventSubscriptionName("my-signal").singleResult();

        LOGGER.info("execution ={}", execution);

        runtimeService.signalEventReceived("my-signal");
        execution = runtimeService.createExecutionQuery().signalEventSubscriptionName("my-signal").singleResult();
        LOGGER.info("execution ={}", execution);

    }

    /**
     * 接受消息
     */
    @Test
    @Deployment(resources = {"my-process-message-receive.bpmn20.xml"})
    public void testMessageEventReceived() {
        RuntimeService runtimeService = activitiRule.getRuntimeService();

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process");
        Execution execution = runtimeService.createExecutionQuery().messageEventSubscriptionName("my-message").singleResult();

        LOGGER.info("execution ={}", execution);

        runtimeService.messageEventReceived("my-message", execution.getId());
        execution = runtimeService.createExecutionQuery().messageEventSubscriptionName("my-message").singleResult();
        LOGGER.info("execution ={}", execution);

    }


    /**
     * 接受消息
     */
    @Test
    @Deployment(resources = {"my-process-message.bpmn20.xml"})
    public void testMessageStart() {
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByMessage("my-message");

        LOGGER.info("processInstance = {}", processInstance);

    }

}
