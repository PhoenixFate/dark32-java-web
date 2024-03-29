package com.phoenix.activiti;

import com.google.common.collect.Maps;
import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormServiceTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    /**
     * 单次部署
     */
    @Test
    @Deployment(resources = {"my-process-form.bpmn20.xml"})
    public void testIdentityService() {
        FormService formService = activitiRule.getFormService();
        ProcessDefinition processDefinition = activitiRule.getRepositoryService().createProcessDefinitionQuery().singleResult();

        String startFormKey = formService.getStartFormKey(processDefinition.getId());
        LOGGER.info("startFormKey = {}", startFormKey);

        StartFormData startFormData = formService.getStartFormData(processDefinition.getId());
        List<FormProperty> formProperties = startFormData.getFormProperties();
        for (FormProperty formProperty : formProperties) {
            LOGGER.info("start form property = {}", ToStringBuilder.reflectionToString(formProperty, ToStringStyle.JSON_STYLE));
        }

        Map<String, String> properties = Maps.newHashMap();
        properties.put("message", "my test message");
        //通过submitStartFormData启动流程
        ProcessInstance processInstance = formService.submitStartFormData(processDefinition.getId(), properties);
        //获取task任务节点
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        //获取task 表单
        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
        List<FormProperty> formProperties1 = taskFormData.getFormProperties();
        for (FormProperty formProperty : formProperties1) {
            LOGGER.info("property = {}", ToStringBuilder.reflectionToString(formProperty, ToStringStyle.JSON_STYLE));
        }
        HashMap<String, String> taskFormDataSubmit = Maps.newHashMap();
        taskFormDataSubmit.put("yesOrNo", "yes");
        formService.submitTaskFormData(task.getId(), taskFormDataSubmit);


        Task task1 = activitiRule.getTaskService().createTaskQuery().taskId(task.getId()).singleResult();
        LOGGER.info("task1 = {}", task1);

    }


}
