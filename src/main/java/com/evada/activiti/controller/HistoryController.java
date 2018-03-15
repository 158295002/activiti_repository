package com.evada.activiti.controller;

import com.evada.activiti.model.HistoricTaskInstanceDTO;
import com.evada.activiti.service.BaseProcessService;
import com.evada.activiti.util.SessionUtil;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dingqin
 * @date 2018/1/22
 */
@RequestMapping("/")
@Controller
public class HistoryController {


    @Autowired
    private BaseProcessService processService;

    @RequestMapping(value = "showHistoryDetail", method = RequestMethod.GET)
    public String showHistoryTaskDetail(Model model) {
        String name = SessionUtil.getCurrentUser();
        model.addAttribute("name",name);
        List<HistoricTaskInstance> taskInstances = processService.findAllHistoryTasks(name);
        if (taskInstances == null || taskInstances.size() == 0) {
            return "showHistoryTaskDetail";
        }
        List<HistoricTaskInstanceDTO> taskDTOS = new ArrayList<>();
        for (HistoricTaskInstance historicTaskInstance:taskInstances){
            HistoricTaskInstanceDTO taskDTO = new HistoricTaskInstanceDTO();
            taskDTO.setHistoricTaskInstance(historicTaskInstance);
            String proc_def_name = processService.findProcessDefinition(historicTaskInstance.getProcessDefinitionId()).getName();
            taskDTO.setProc_def_name(proc_def_name);

            Task currentTask = processService.findCurrentTaskByInstanceId(historicTaskInstance.getProcessInstanceId());
            taskDTO.setCurrentTask(currentTask);
            taskDTOS.add(taskDTO);
        }
        model.addAttribute("taskDTOS",taskDTOS);
        return "showHistoryTaskDetail";
    }
}
