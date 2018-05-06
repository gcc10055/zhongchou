package com.jsplay.hello;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloActivitiApplicationTests {

    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;

    /*@Test
    public void contextLoa{
        System.out.print("流程的核心对象" + processEngine);

    }*/

    /**
     * 当流程框架部署成功时, 会将数据库中表的数据进行更新
     * act_ge_bytearray, 通用二进制字节数组表  保存了当前部署的流程定义图形和文件
     * act_re_deployment,持久化部署信息表   保存了部署时间的信息
     * act_re_procdef, 持久化流程定义数据表  保存了流程定义的信息
     * 如果多个流程进行部署，就会有多个版本号
     */
    @Test
    public void bpmn2db(){
        //将流程定义图加载到流程框架的数据库中，将这个操作称之为部署
        //使用持久化服务对象
        //RepositoryService repositoryService = processEngine.getRepositoryService();
        //System.out.print(repositoryService);
        //创建部署,将流程定义图形加载进来
        Deployment deployment = repositoryService
                .createDeployment()
                .addClasspathResource("Myprocess.bpmn").deploy();

    }

    @Test
    public void loadProdefData(){
        //读取流程数据
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        /*List<ProcessDefinition> procDefs = processDefinitionQuery.list();/获取所有数据
        for (ProcessDefinition procDef : procDefs){
            System.out.print(procDef.getName() + "\t" + procDef.getKey() + "\t" + procDef.getVersion());
        }*/
        //获取单一的流程定义
        ProcessDefinition procDef = processDefinitionQuery.processDefinitionVersion(2).singleResult();
        processDefinitionQuery.orderByProcessDefinitionVersion().desc().list().get(0);//获取最新的版本号的流程定义  麻烦
        processDefinitionQuery.latestVersion().singleResult();//获取最新的版本号的流程定义  简单
    }
    //开始流程
    @Test
    public void startProcessInstance(){
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        ProcessDefinition pd = processDefinitionQuery.latestVersion().singleResult();
        //让流程定义的数据真正的运行起来，启动流程实例
        //流程实例其实就是流程定义的具体应用
        /*启动流程实例后，数据库的表会发生变化
        act_hi_actinst(2)：历史节点表
        act_hi_procinst(1)：历史流程实例表
        act_hi_taskinst(1):历史任务实例表
        act_ru_execution(1)：运行时流程执行实例表  运行时的步骤
        act_ru_task(1):运行时任务数据表  步骤的信息
         */
        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
        System.out.print("pi = " + pi);
    }
//找到流程完成任务
    @Test
    public void findAndFinishTask(){
        //流程运行后，执行某一个任务，如果想让流程继续执行，那必须完成相应的任务才可以

        //查询任务
        TaskQuery query = taskService.createTaskQuery();
        List<Task> tasks = query.list();

        //完成任务
        for (Task task:tasks){
            System.out.print("taskName = "+task.getName());
            taskService.complete(task.getId());
            System.out.print("完成了任务 = " + task.getName());
        }
    }
    //查询历史
    @Test
    public void findHistory(){
        //查询曾经的历史记录
        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
        HistoricProcessInstance hpi = query.processInstanceId("2501").finished().singleResult();
        System.out.print("流程是否结束 = " + (hpi !=null));
    }

    //整个流程
    @Test
    public void allProcessSteps(){
        //部署
        repositoryService.createDeployment()
                .addClasspathResource("Myprocess1.bpmn")
                .deploy();
        //查询流程定义对象
        ProcessDefinitionQuery query = repositoryService
                .createProcessDefinitionQuery();
        //找到对应流程并取得流程定义
        ProcessDefinition pd = query.processDefinitionKey("myProcess_1").latestVersion().singleResult();
        //启动流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
        //完成任务
        TaskQuery taskQuery = taskService.createTaskQuery();
        //查询只能查询到正在运行的任务信息
        List<Task> tasks = taskQuery.processInstanceId(pi.getId()).list();
        for (Task task : tasks){
            System.out.print("task name = " + task.getName());
            taskService.complete(task.getId());
        }
        //第二遍查询
        tasks = taskQuery.processInstanceId(pi.getId()).list();
        for (Task task : tasks){
            System.out.print("task name = " + task.getName());
            taskService.complete(task.getId());
        }
        //结束流程
        HistoricProcessInstanceQuery hpiquery = historyService.createHistoricProcessInstanceQuery();
        HistoricProcessInstance hpi = hpiquery.processInstanceId(pi.getId()).finished().singleResult();
        System.out.print("流程是否结束 = " + (hpi != null));
    }
    //流程变量
    @Test
    public void processVar(){
        //部署
        repositoryService.createDeployment()
                .addClasspathResource("Myprocess1.bpmn")
                .deploy();
        //查询流程定义对象
        ProcessDefinitionQuery query = repositoryService
                .createProcessDefinitionQuery();
        //找到对应流程并取得流程定义
        ProcessDefinition pd = query.processDefinitionKey("myProcess_1").latestVersion().singleResult();
        //启动流程实例
        //如果流程定义中包含了流程变量，那么可以在启动流程时，传递变量值
        //ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
        Map<String,Object> varMap = new HashMap<String,Object>();
        varMap.put("TL","zhaoliu");
        varMap.put("PM","tianqi");
        runtimeService.startProcessInstanceById(pd.getId(),varMap);

        //完成任务
        TaskQuery taskQuery = taskService.createTaskQuery();
        //查询只能查询到正在运行的任务信息
        List<Task> tasks = taskQuery.taskAssignee("zhaoliu").list();
        for (Task task : tasks){
            System.out.print("task name = " + task.getName());
            taskService.complete(task.getId());
        }

        tasks = taskQuery.taskAssignee("tianqi").list();
        for (Task task : tasks){
            System.out.print("task name = " + task.getName());
            taskService.complete(task.getId());
        }
    }

    /**
     * 小组任务
     */
    @Test
    public void groupTask(){
        //先将任务分配给小组，小组在进行分配(领取任务)
        //部署
        repositoryService.createDeployment()
                .addClasspathResource("Myprocess2.bpmn")
                .deploy();
        //查询流程定义对象
        ProcessDefinitionQuery query = repositoryService
                .createProcessDefinitionQuery();
        //找到对应流程并取得流程定义
        ProcessDefinition pd = query.processDefinitionKey("myProcess_1").latestVersion().singleResult();
        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
        //完成任务
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> tasks = taskQuery.taskCandidateGroup("manager-team").list();
        for (Task task : tasks){
            //将任务分配给王五，王五领取了任务
            taskService.claim(task.getId(),"wangwu");

        }
        taskQuery = taskService.createTaskQuery();
        System.out.print("wangwu de 任务数量" + taskQuery.taskAssignee("wangwu").count());

    }

    /**
     * 网关：流程当中的逻辑分支判断
     */
    @Test
    public void gateWay1(){
        //画流程图时，菱形中是个叉，表示排他网关(有我没你，有你没我)，就是决策
        //部署
        repositoryService.createDeployment()
                .addClasspathResource("Myprocess3.bpmn")
                .deploy();
        //查询流程定义对象
        ProcessDefinitionQuery query = repositoryService
                .createProcessDefinitionQuery();
        //找到对应流程并取得流程定义
        ProcessDefinition pd = query.processDefinitionKey("myProcess_1").latestVersion().singleResult();
        //启动流程实例
        //如果流程定义中包含了流程变量，那么可以在启动流程时，传递变量值
        Map<String,Object> varMap = new HashMap<String,Object>();
        //varMap.put("days",1);
        varMap.put("days",5);
        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId(),varMap);
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("zhangsan").list();
        System.out.print("zhangsan 的任务数量" + tasks.size());
        for (Task task : tasks){
            System.out.print("zhangsan 完成了任务 " + task.getName());
            taskService.complete(task.getId());
        }
        //结束流程
        HistoricProcessInstanceQuery hpiquery = historyService.createHistoricProcessInstanceQuery();
        HistoricProcessInstance hpi = hpiquery.processInstanceId(pi.getId()).finished().singleResult();
        System.out.print("流程是否结束 = " + (hpi != null));
    }

    /**
     * 网关：流程当中的逻辑分支判断
     */
    @Test
    public void gateWay2() {
        //画流程图时，菱形中是个加号，表示并行网关,就是会签
        //部署
        repositoryService.createDeployment()
                .addClasspathResource("Myprocess4.bpmn")
                .deploy();
        //查询流程定义对象
        ProcessDefinitionQuery query = repositoryService
                .createProcessDefinitionQuery();
        //找到对应流程并取得流程定义
        ProcessDefinition pd = query.processDefinitionKey("myProcess_1").latestVersion().singleResult();
        //启动流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
        List<Task> tasks1 = taskService.createTaskQuery().taskAssignee("zhangsan").list();
        System.out.print("zhangsan 的任务数量" + tasks1.size());
        List<Task> tasks2 = taskService.createTaskQuery().taskAssignee("lisi").list();
        System.out.print("lisi 的任务数量" + tasks2.size());

        for (Task task : tasks1){
            System.out.print("zhangsan 完成了任务 是 =" + task.getName());
            taskService.complete(task.getId());
        }

        //结束流程
        HistoricProcessInstanceQuery hpiquery1 = historyService.createHistoricProcessInstanceQuery();
        HistoricProcessInstance hpi1 = hpiquery1.processInstanceId(pi.getId()).finished().singleResult();
        System.out.print("流程是否结束 = " + (hpi1 != null));

        for (Task task : tasks2){
            System.out.print("lisi 完成了任务 是 =" + task.getName());
            taskService.complete(task.getId());
        }

        //结束流程
        HistoricProcessInstanceQuery hpiquery2 = historyService.createHistoricProcessInstanceQuery();
        HistoricProcessInstance hpi2 = hpiquery2.processInstanceId(pi.getId()).finished().singleResult();
        System.out.print("流程是否结束 = " + (hpi2 != null));
    }

    /**
     * 网关：流程当中的逻辑分支判断
     */
    @Test
    public void gateWay3() {
        //画流程图时，菱形中是个圈，表示包含网关，等同于排他网关+并行网关
        //部署
        repositoryService.createDeployment()
                .addClasspathResource("Myprocess5.bpmn")
                .deploy();
        //查询流程定义对象
        ProcessDefinitionQuery query = repositoryService
                .createProcessDefinitionQuery();
        //找到对应流程并取得流程定义
        ProcessDefinition pd = query.processDefinitionKey("myProcess_1").latestVersion().singleResult();
        //启动流程实例
        //如果流程定义中包含了流程变量，那么可以在启动流程时，传递变量值
        Map<String,Object> varMap = new HashMap<String,Object>();
        varMap.put("cost",2000);
        varMap.put("days",5);
        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId(),varMap);

        List<Task> tasks1 = taskService.createTaskQuery().taskAssignee("zhangsan").list();
        System.out.print("zhangsan 的任务数量" + tasks1.size());
        List<Task> tasks2 = taskService.createTaskQuery().taskAssignee("lisi").list();
        System.out.print("lisi 的任务数量" + tasks2.size());

        for (Task task : tasks1){
            System.out.print("zhangsan 完成了任务 是 =" + task.getName());
            taskService.complete(task.getId());
        }

        //结束流程
        HistoricProcessInstanceQuery hpiquery1 = historyService.createHistoricProcessInstanceQuery();
        HistoricProcessInstance hpi1 = hpiquery1.processInstanceId(pi.getId()).finished().singleResult();
        System.out.print("流程是否结束 = " + (hpi1 != null));

    }

    /**
     * 网关：流程当中的逻辑分支判断
     */
    @Test
    public void gateWay4() {
        //部署
        repositoryService.createDeployment()
                .addClasspathResource("Myprocess6.bpmn")
                .deploy();
        //查询流程定义对象
        ProcessDefinitionQuery query = repositoryService
                .createProcessDefinitionQuery();
        //找到对应流程并取得流程定义
        ProcessDefinition pd = query.processDefinitionKey("myProcess_1").latestVersion().singleResult();

        //启动流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());

        List<Task> tasks1 = taskService.createTaskQuery().taskAssignee("zhangsan").list();
        System.out.print("zhangsan 的任务数量" + tasks1.size());
        for (Task task : tasks1){
            System.out.print("zhangsan 完成了任务 是 =" + task.getName());
            taskService.complete(task.getId());
        }
        tasks1 = taskService.createTaskQuery().taskAssignee("lisi").list();
        System.out.print("lisi 的任务数量" + tasks1.size());
        for (Task task : tasks1){
            System.out.print("lisi 完成了任务 是 =" + task.getName());
            Map<String,Object> varMap = new HashMap<String,Object>();
            varMap.put("flg","b");
            taskService.complete(task.getId(),varMap);
        }

        //结束流程
        HistoricProcessInstanceQuery hpiquery1 = historyService.createHistoricProcessInstanceQuery();
        HistoricProcessInstance hpi1 = hpiquery1.processInstanceId(pi.getId()).finished().singleResult();
        System.out.print("流程是否结束 = " + (hpi1 != null));

    }

    /**
     * 测试邮件
     */
    @Test
    public void sendMail() {
        //部署
        repositoryService.createDeployment()
                .addClasspathResource("Myprocess7.bpmn")
                .deploy();
        //查询流程定义对象
        ProcessDefinitionQuery query = repositoryService
                .createProcessDefinitionQuery();
        //找到对应流程并取得流程定义
        ProcessDefinition pd = query.processDefinitionKey("myProcess_1").latestVersion().singleResult();
        //启动流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId());
        //结束流程
        HistoricProcessInstanceQuery hpiquery1 = historyService.createHistoricProcessInstanceQuery();
        HistoricProcessInstance hpi1 = hpiquery1.processInstanceId(pi.getId()).finished().singleResult();
        System.out.print("流程是否结束 = " + (hpi1 != null));
    }
}
