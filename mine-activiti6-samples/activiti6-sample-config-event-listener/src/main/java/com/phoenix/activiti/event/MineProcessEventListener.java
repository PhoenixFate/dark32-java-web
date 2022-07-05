package com.phoenix.activiti.event;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 流程eventListener
 */
public class MineProcessEventListener implements ActivitiEventListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(MineProcessEventListener.class);

    @Override
    public void onEvent(ActivitiEvent activitiEvent) {
        //获得eventType
        ActivitiEventType eventType = activitiEvent.getType();
        if (ActivitiEventType.PROCESS_STARTED.equals(eventType)) {
            //如果是流程启动type
            LOGGER.info("流程启动 {} \t processInstanceId:{}", eventType, activitiEvent.getProcessInstanceId());
        } else if (ActivitiEventType.PROCESS_COMPLETED.equals(eventType)) {
            //流程结束
            LOGGER.info("流程结束 {} \t processInstanceId:{}", eventType, activitiEvent.getProcessInstanceId());
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
