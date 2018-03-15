package com.evada.activiti.model;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;

import java.util.Date;

/**
 * @author dingqin
 * @date 2018/1/8
 */
public class HistoricTaskInstanceDTO {

    private String status;

    private HistoricTaskInstance historicTaskInstance;

    private String proc_def_name;

    private String nextAssignee;

    private Task currentTask;

    public String getStatus() {
        if (status == null){
            status = currentTask==null?"已结束":"审批中";
        }
        return status;
    }

    public HistoricTaskInstance getHistoricTaskInstance() {
        return historicTaskInstance;
    }

    public void setHistoricTaskInstance(HistoricTaskInstance historicTaskInstance) {
        this.historicTaskInstance = historicTaskInstance;
    }

    public String getProc_def_name() {
        return proc_def_name;
    }

    public void setProc_def_name(String proc_def_name) {
        this.proc_def_name = proc_def_name;
    }

    public String getNextAssignee() {
        if (currentTask == null) {
            return "";
        }
        return currentTask.getAssignee();
    }


    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }
}
