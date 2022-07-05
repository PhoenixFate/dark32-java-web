package com.phoenix.activiti;

import com.phoenix.activiti.event.CustomerProcessEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.impl.ActivitiEntityEventImpl;
import org.activiti.engine.delegate.event.impl.ActivitiEventImpl;
import org.activiti.engine.event.EventLogEntry;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigEventLogTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigEventLogTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti_event_listener.cfg.xml");

    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void test() {
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        activitiRule.getTaskService().complete(task.getId());

        //通过程序添加事件监听器
        activitiRule.getRuntimeService().addEventListener(new CustomerProcessEventListener());


        //手动自己发出一个事件
        activitiRule.getRuntimeService().dispatchEvent(new ActivitiEventImpl(ActivitiEventType.CUSTOM));

    }


}
