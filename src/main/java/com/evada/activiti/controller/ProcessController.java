package com.evada.activiti.controller;

import com.evada.activiti.model.LeaveModel;
import com.evada.activiti.service.BaseProcessService;
import com.evada.activiti.util.SessionUtil;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dingqin
 * @date 2018/1/8
 */
@Controller
@RequestMapping(value = "/", produces = {"application/json;", "text/html;charset=UTF-8;"})
public class ProcessController {

    @Autowired
    private BaseProcessService processService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "showAllProcessDefines", method = RequestMethod.GET)
    public String showAllProcessDefines(Model model) {
        String name = SessionUtil.getCurrentUser();
        List<ProcessDefinition> processDefinitions = processService.findAllProcessDefines();
        model.addAttribute("processDefinitions", processDefinitions);
        model.addAttribute("name", name);
        return "processDefineDetail";
    }

    @RequestMapping(value = "importProcess", method = RequestMethod.GET)
    public String addFile(Model model) {
        String name = SessionUtil.getCurrentUser();
        model.addAttribute("name", name);
        return "importBpmnFile";
    }

    @RequestMapping(value = "deployProcess", method = RequestMethod.POST)
    public String deployProcess(RedirectAttributes remodel, @RequestPart("bpmnFile") MultipartFile bpmnFile) {
        String message = processService.deployProcess(bpmnFile);

        if (message != null && message != "") {
            remodel.addFlashAttribute("message", message);
            return "redirect:importProcess";
        }
        return "redirect:showAllProcessDefines";
    }

    @RequestMapping(value = "applyFor/{key}", method = RequestMethod.GET)
    public String applyForLeave(ModelMap modelMap, @PathVariable("key") String key) {
        modelMap.put("key", key);
        String name = SessionUtil.getCurrentUser();
        modelMap.put("name", name);
        return "LeavePage";
    }

    @RequestMapping(value = "startProcess/{key}", method = RequestMethod.GET)
    public String startProcess(ModelMap modelMap, @PathVariable("key") String key) {
        Map<String, Object> variableMap = new HashMap<>();
        variableMap.put("name", SessionUtil.getCurrentUser());
        ProcessInstance processInstance = processService.startProcessInstances(key, variableMap);
        if (processInstance == null) {
            modelMap.put("message", "启动流程实例失败");
        }
        return "userPage";
    }

    @RequestMapping(value = "doApplayFor/{key}", method = RequestMethod.POST)
    public String doApplayFor(ModelMap modelMap, @PathVariable("key") String key, LeaveModel leaveModel) {
        //先启动
        Map<String, Object> varMap = new HashMap<>();
        varMap.put("name", SessionUtil.getCurrentUser());
        ProcessInstance processInstance = processService.startProcessInstances(key, varMap);
        if (processInstance == null) {
            modelMap.put("message", "启动流程实例失败");
            return "userPage";
        }
        leaveModel.setProcessDefineId(processInstance.getProcessDefinitionId());
        leaveModel.setProcessInstaneceId(processInstance.getProcessInstanceId());
        //再完成任务
        String name = SessionUtil.getCurrentUser();
        leaveModel.setName(name);
        varMap.put("leaveModel", leaveModel);
        String message = processService.completeTask(name, varMap);
        modelMap.put("message", message);
        return "redirect:/showHistoryDetail";
    }


}
