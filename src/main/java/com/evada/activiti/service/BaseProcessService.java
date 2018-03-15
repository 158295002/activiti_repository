package com.evada.activiti.service;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author dingqin
 * @date 2018/1/8
 */
public interface BaseProcessService {

    public String deployProcess(MultipartFile bpmnFile);

    public ProcessInstance startProcessInstances(String key, Map<String, Object> variableMap);

    public String completeTask(String assignee,Map<String, Object> variableMap);

    public List<Task> findAllTask(String assignee);

    public Object findAllVariables(String taskId,String variableName);

    public Map<String,Object> findVariable(String taskId);

    public List<HistoricTaskInstance> findAllHistoryTasks(String assignee);

    public Map<String,Object> findHistoryVariable(String executionId);

    public List<ProcessDefinition> findAllProcessDefines();

    public ProcessDefinition findProcessDefinition(String proc_def_id);

    public Task findCurrentTaskByInstanceId(String proc_ins_id);
}
