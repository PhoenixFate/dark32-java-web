package com.phoenix.activiti;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class ConfigRepositoryServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigRepositoryServiceTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();


    /**
     * 单次部署
     */
    @Test
    public void test() {
        RepositoryService repositoryService = activitiRule.getRepositoryService();
        //部署构建对象
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        deploymentBuilder.name("测试部署资源1")
                .addClasspathResource("my-process.bpmn20.xml")
                .addClasspathResource("second_approve.bpmn20.xml");
        //部署操作
        Deployment deploy = deploymentBuilder.deploy();

        LOGGER.info("deploy={}", deploy);
        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
        //获得某次部署
        Deployment deployment = deploymentQuery.deploymentId(deploy.getId()).singleResult();

        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId()).listPage(0, 100);
        for (ProcessDefinition processDefinition : processDefinitionList) {
            LOGGER.info("processDefinition = {},version={}, key={}, id={}", processDefinition,
                    processDefinition.getVersion(),
                    processDefinition.getKey(),
                    processDefinition.getId());
        }

    }

    /**
     * 多次部署
     */
    @Test
    public void test2() {
        RepositoryService repositoryService = activitiRule.getRepositoryService();
        //部署构建对象
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        deploymentBuilder.name("测试部署资源1")
                .addClasspathResource("my-process.bpmn20.xml")
                .addClasspathResource("second_approve.bpmn20.xml");
        //部署操作
        Deployment deploy = deploymentBuilder.deploy();
        LOGGER.info("deploy={}", deploy);

        //多次部署
        DeploymentBuilder deploymentBuilder2 = repositoryService.createDeployment();
        deploymentBuilder2.name("测试部署资源2")
                .addClasspathResource("my-process.bpmn20.xml")
                .addClasspathResource("second_approve.bpmn20.xml");
        Deployment deploy2 = deploymentBuilder2.deploy();
        LOGGER.info("deploy2={}", deploy2);

        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
        //获得多次部署
        List<Deployment> deployments = deploymentQuery.orderByDeploymenTime().asc().listPage(0, 100);
        for (Deployment deployment : deployments) {
            LOGGER.info("deployment = {}", deployment);
        }
        LOGGER.info("deployment.size = {}", deployments.size());

        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery()
                .orderByProcessDefinitionKey().asc()
                .listPage(0, 100);
        for (ProcessDefinition processDefinition : processDefinitionList) {
            LOGGER.info("processDefinition = {},version={}, key={}, id={}", processDefinition,
                    processDefinition.getVersion(),
                    processDefinition.getKey(),
                    processDefinition.getId());
        }
    }

    /**
     * 测试流程暂停
     */
    @Test
    @org.activiti.engine.test.Deployment(resources = {"my-process.bpmn20.xml"})
    public void testSuspend() {
        RepositoryService repositoryService = activitiRule.getRepositoryService();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();
        LOGGER.info("processDefinition.getId: {}", processDefinition.getId());
        //挂起流程定义
        repositoryService.suspendProcessDefinitionById(processDefinition.getId());


        //启动流程定义（因为已经挂起了，所以启动失败）
        try {
            LOGGER.info("开始启动");
            activitiRule.getRuntimeService().startProcessInstanceById(processDefinition.getId());
            LOGGER.info("启动成功");
        } catch (Exception e) {
            LOGGER.info("启动失败");
            LOGGER.info(e.getMessage(), e);
        }

    }

    /**
     * 指定用户组、用户
     */
    @Test
    @org.activiti.engine.test.Deployment(resources = {"my-process.bpmn20.xml"})
    public void testCandidateStarter() {
        RepositoryService repositoryService = activitiRule.getRepositoryService();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();
        LOGGER.info("processDefinition.getId: {}", processDefinition.getId());

        //添加用户
        repositoryService.addCandidateStarterUser(processDefinition.getId(), "userWang");
        //添加用户组
        repositoryService.addCandidateStarterGroup(processDefinition.getId(), "groupManagement");

        //获取刚刚定义的关系
        List<IdentityLink> identityLinksForProcessDefinition = repositoryService.getIdentityLinksForProcessDefinition(processDefinition.getId());
        for (IdentityLink identityLink : identityLinksForProcessDefinition) {
            LOGGER.info("identityLink = {}", identityLink);
        }

        //删除用户用户组
        repositoryService.deleteCandidateStarterGroup(processDefinition.getId(), "groupManagement");
        repositoryService.deleteCandidateStarterUser(processDefinition.getId(), "userWang");


    }

}
