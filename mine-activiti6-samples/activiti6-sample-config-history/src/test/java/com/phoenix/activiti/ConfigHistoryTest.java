package com.phoenix.activiti;

import com.google.common.collect.Maps;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.Execution;
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

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConfigHistoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigHistoryTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti_history.cfg.xml");

    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void test() {
        //启动流程
        //定义一个参数
        startProcessInstance();
        //修改变量
        changeVariable();
        //提交表单
        submitForm();
        //输出历史活动内容
        showHistoryActivity();
        //输出历史变量
        showHistroyVariable();
        //输出历史表单任务（先获得历史任务）
        showHistoryTask();
        showHistoryForm();
        //输出历史详情
        showHistoryFormDetail();
    }

    private void showHistoryFormDetail() {
        List<HistoricDetail> historicDetails = activitiRule.getHistoryService()
                .createHistoricDetailQuery()
                .listPage(0, 100);
        for (HistoricDetail historicDetail : historicDetails) {
            LOGGER.info("historicDetail={}", historyDetailToString(historicDetail));
        }
        LOGGER.info("AAA historicDetails.size={}", historicDetails.size());
    }

    private void showHistoryForm() {
        List<HistoricDetail> historicDetailForms = activitiRule.getHistoryService()
                .createHistoricDetailQuery()
                .formProperties()
                .listPage(0, 100);
        for (HistoricDetail historicDetail : historicDetailForms) {
            LOGGER.info("AAA historicDetailForm = {}", historyDetailToString(historicDetail));
        }
        LOGGER.info("AAA historicDetailForms.size={}", historicDetailForms.size());
    }

    private void showHistoryTask() {
        List<HistoricTaskInstance> historicTaskInstances = activitiRule.getHistoryService().createHistoricTaskInstanceQuery().listPage(0, 100);
        for (HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
            LOGGER.info("AAA historicTaskInstance = {}", historicTaskInstance);
        }
        LOGGER.info("AAA historicTaskInstances.size = {}", historicTaskInstances.size());
    }

    private void showHistroyVariable() {
        List<HistoricVariableInstance> historicVariableInstances = activitiRule.getHistoryService().createHistoricVariableInstanceQuery().listPage(0, 100);
        for (HistoricVariableInstance historicTaskInstances : historicVariableInstances) {
            LOGGER.info("AAA historicTaskInstance = {} ", historicTaskInstances);
        }
        LOGGER.info("AAA historicTaskInstances.size={}", historicVariableInstances);
    }

    private void showHistoryActivity() {
        List<HistoricActivityInstance> historicActivityInstances = activitiRule.getHistoryService().createHistoricActivityInstanceQuery().listPage(0, 100);
        for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
            LOGGER.info("AAA historyActivityInstance = {}", historicActivityInstance);
        }
        LOGGER.info("AAA historyActivityInstance.size = {}", historicActivityInstances.size());
    }

    private void submitForm() {
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        //通过提交表单来完成任务
        Map<String, String> properties = Maps.newHashMap();
        properties.put("formKey1", "value_f1");
        properties.put("formKey2", "value_f2");
        //提交表单 task (流程完成)
        activitiRule.getFormService().submitTaskFormData(task.getId(), properties);
    }

    private void changeVariable() {
        List<Execution> executions = activitiRule.getRuntimeService().createExecutionQuery().listPage(0, 100);
        for (Execution execution : executions) {
            LOGGER.info("AAA execution = {}", execution);
        }
        LOGGER.info("AAA executions.size = {}", executions.size());
        //取出第一条记录
        String id = executions.iterator().next().getId();
        //修改变量
        activitiRule.getRuntimeService().setVariable(id, "keyStart1", "value1_after_update");
    }

    /**
     * 启动流程
     */
    private void startProcessInstance() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("keyStart1", "value1");
        params.put("keyStart2", "value2");
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process", params);
        assertNotNull(processInstance);
    }

    private String historyDetailToString(HistoricDetail historicDetail) {
        return ToStringBuilder.reflectionToString(historicDetail, ToStringStyle.SHORT_PREFIX_STYLE);
    }


}
