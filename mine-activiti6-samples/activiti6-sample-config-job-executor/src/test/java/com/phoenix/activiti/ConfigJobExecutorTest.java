package com.phoenix.activiti;

import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ConfigJobExecutorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigJobExecutorTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti_job_executor.cfg.xml");

    @Test
    @Deployment(resources = {"my-process_job.bpmn20.xml"})
    public void test() throws InterruptedException {
        //以定时任务的方式启动流程，所以不要代码来启动流程
        LOGGER.info("start");

        //查询当前定时任务
        List<Job> jobList = activitiRule.getManagementService().createTimerJobQuery().listPage(0, 100);
        for (Job job : jobList) {
            LOGGER.info("job {}, 默认重试次数 {}", job, job.getRetries());
        }
        LOGGER.info("定时任务数量: {}", jobList.size());
        Thread.sleep(100 * 1000);
        LOGGER.info("end");
    }


}
