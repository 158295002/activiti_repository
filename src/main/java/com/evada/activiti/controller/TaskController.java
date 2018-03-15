package com.evada.activiti.controller;

import com.evada.activiti.model.LeaveModel;
import com.evada.activiti.service.BaseProcessService;
import com.evada.activiti.util.SessionUtil;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dingqin
 * @date 2018/1/22
 */
@RequestMapping("/")
@Controller
public class TaskController {

    @Autowired
    private BaseProcessService processService;

    @RequestMapping(value = "doComplete/{taskId}", method = RequestMethod.GET)
    public String toComplete(Model model, @PathVariable("taskId") String taskId) {
       LeaveModel leaveModel = (LeaveModel) processService.findAllVariables(taskId,"leaveModel");
       model.addAttribute("leaveModel",leaveModel);
       return "completeTaskPage";
    }

    @RequestMapping(value = "completeTask", method = RequestMethod.GET)
    public String completeTaskLeave(ModelMap modelMap, @RequestParam("reason") String reason) {
        String assginee = SessionUtil.getCurrentUser();
        Map<String, Object> variableMap = new HashMap<>();
        variableMap.put(assginee + "_reason", reason);
        String message = processService.completeTask(assginee, variableMap);
        modelMap.put("message", message);
        modelMap.put("name",assginee);
        return "userPage";
    }

    @RequestMapping(value = "showTaskDetail",method = RequestMethod.GET)
    public String showTaskDetail(Model model) {
        String name = SessionUtil.getCurrentUser();
        model.addAttribute("name",name);
        List<Task> tasks = processService.findAllTask(name);
        model.addAttribute("tasks", tasks);
        return "taskDetail";
    }
}
