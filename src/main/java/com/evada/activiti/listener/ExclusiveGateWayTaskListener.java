package com.evada.activiti.listener;

import com.evada.activiti.model.LeaveModel;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class ExclusiveGateWayTaskListener implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		LeaveModel model = (LeaveModel) delegateTask.getVariable("leaveModel");
		delegateTask.setAssignee(model.getNextAssignee());
	}

}
