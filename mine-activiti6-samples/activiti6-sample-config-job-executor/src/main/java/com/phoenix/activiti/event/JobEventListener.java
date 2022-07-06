package com.phoenix.activiti.event;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 监听自定义事件的事件监听
 */
public class JobEventListener implements ActivitiEventListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(JobEventListener.class);

    @Override
    public void onEvent(ActivitiEvent activitiEvent) {
        //获得eventType
        ActivitiEventType eventType = activitiEvent.getType();
        String name = eventType.name();

        if (name.startsWith("TIMER") || name.startsWith("JOB")) {
            LOGGER.info("监听到Job事件 {} \t processInstanceId:{}", eventType, activitiEvent.getProcessInstanceId());
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
