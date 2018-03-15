package com.evada.activiti.service.impl;

import com.evada.activiti.service.BaseProcessService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * @author dingqin
 * @date 2018/1/8
 */
@Service
public class BaseProcessServiceImpl implements BaseProcessService {

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    @Autowired
    private HttpServletRequest request;

    public String deployProcess(MultipartFile bpmnFile) {
        String fileName = bpmnFile.getOriginalFilename();
        int index = fileName.indexOf(".");
        if (index != -1) {
            fileName = fileName.substring(0,index);
        }
        try {
            InputStream inputStream = new ByteArrayInputStream(bpmnFile.getBytes());
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);

            processEngine.getRepositoryService().createDeployment()
                    .name(fileName)
                    .addZipInputStream(zipInputStream).deploy();
        } catch (Exception e) {
            e.printStackTrace();
            return "发布失败";
        }
        return "";
    }

    protected String formatDate(Deployment deployment) {
        Date date = deployment.getDeploymentTime();
        DateFormat format = new SimpleDateFormat("yyyy年mm月dd日:hh:mm:ss");
        String sdate = format.format(date);
        return sdate;
    }

    public ProcessInstance startProcessInstances(String key,Map<String,Object> varMap) {
        try {
            ProcessInstance instance = processEngine.getRuntimeService().startProcessInstanceByKey(key,varMap);
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String completeTask(String assignee, Map<String, Object> variableMap) {
        List<Task> tasks = findAllTask(assignee);
        if (tasks == null || tasks.size() == 0) {
            return "没查找到相关任务";
        }
        String taskId = tasks.get(0).getId();
        processEngine.getTaskService().complete(taskId, variableMap);
        return "任务完成";
    }

    @Override
    public List<Task> findAllTask(String assignee) {
        return processEngine.getTaskService().createTaskQuery().taskAssignee(assignee).list();
    }

    @Override
    public Object findAllVariables(String id, String variableName) {
        Object variable = processEngine.getTaskService().getVariable(id, variableName);
        return variable;
    }

    @Override
    public Map<String, Object> findVariable(String taskId) {
        return processEngine.getTaskService().getVariables(taskId);
    }

    @Override
    public List<HistoricTaskInstance> findAllHistoryTasks(String assignee) {
        return processEngine.getHistoryService().createHistoricTaskInstanceQuery().taskAssignee(assignee).list();
    }

    @Override
    public Map<String, Object> findHistoryVariable(String executionId) {
        List<HistoricVariableInstance> historicTaskInstanceQueries = processEngine.getHistoryService().createHistoricVariableInstanceQuery().executionId(executionId).list();
        Map<String, Object> map = new HashMap<>();
        for (HistoricVariableInstance historicVariableInstance : historicTaskInstanceQueries) {
            map.put(historicVariableInstance.getVariableName(), historicVariableInstance.getValue());
            map.put("开始时间_" + historicVariableInstance.getVariableName(), historicVariableInstance.getCreateTime());
            map.put("结束时间_" + historicVariableInstance.getVariableName(), historicVariableInstance.getLastUpdatedTime());
        }
        return map;
    }

    @Override
    public List<ProcessDefinition> findAllProcessDefines() {
        List<ProcessDefinition> processDefinitions = processEngine.getRepositoryService().createProcessDefinitionQuery().latestVersion().list();
        return processDefinitions;
    }

    @Override
    public ProcessDefinition findProcessDefinition(String proc_def_id) {
        return processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionId(proc_def_id).singleResult();
    }

    @Override
    public Task findCurrentTaskByInstanceId(String proc_ins_id) {
        return processEngine.getTaskService().createTaskQuery().processInstanceId(proc_ins_id).singleResult();
    }
}
