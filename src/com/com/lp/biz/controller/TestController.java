package com.lp.biz.controller;

import com.lp.utils.SpringContextHolder;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by CPR161 on 2017-03-10.
 * 项目测试类
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/index")
    public String chagePassList(HttpServletRequest request, HttpServletResponse response){
        String forward = "test/index";

        runtimeService.startProcessInstanceByKey("process");
        //获取accountancy组可能要操作的任务
        TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateGroup("marketing");
        List<Task> taskList = taskQuery.list();
        for(Task task:taskList){
            System.out.println("fozzie的任务: " + task.getName()+",taskId:"+task.getId());
            //设置fozzie代办 claim it
            taskService.claim(task.getId(), "fozzie");
            // 完成
            this.taskService.complete(task.getId());
        }

        System.out.println("fozzie剩余任务数: " + taskService.createTaskQuery().taskAssignee("fozzie").count());

        //管理者审核报告并让kermit代办
        taskList = taskService.createTaskQuery().taskCandidateGroup("management").list();
        for (Task task : taskList) {
            System.out.println("管理组任务: " + task.getName()+",taskId:"+task.getId());
            taskService.claim(task.getId(), "kermit");
        }

        System.out.println("kermit剩余任务数: " + taskService.createTaskQuery().taskAssignee("kermit").count());

        //完成报告
        for (Task task : taskList) {
            System.out.println("taskId:"+task.getId()+"完成");
            taskService.complete(task.getId());
        }

        System.out.println("流程结束！");

        return forward;
    }
}
