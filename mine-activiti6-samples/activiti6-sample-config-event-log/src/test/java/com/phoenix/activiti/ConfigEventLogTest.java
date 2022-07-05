package com.phoenix.activiti;

import org.activiti.engine.event.EventLogEntry;
import org.activiti.engine.logging.LogMDC;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConfigEventLogTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigEventLogTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti_event_log.cfg.xml");

    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void test() {
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        System.out.println(task.getName());
        assertEquals("Activiti is awesome!", task.getName());

        //获取并遍历eventLog数据
        List<EventLogEntry> eventLogEntryList = activitiRule.getManagementService().getEventLogEntriesByProcessInstanceId(processInstance.getProcessInstanceId());
        for (EventLogEntry eventLogEntry : eventLogEntryList) {
            LOGGER.info("eventLog.Type = {}", eventLogEntry.getType());
            LOGGER.info("eventLog.Date = {}", new String(eventLogEntry.getData()));
        }
        LOGGER.info("eventLog size: {}", eventLogEntryList.size());

    }


}
