package com.evada.activiti.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author dingqin
 * @date 2018/1/23
 */
public class LeaveModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String processInstaneceId;

    private String processDefineId;

    private String name;

    private int leaveDays;

    private String reason;

    private String nextAssignee;

    private  String startDate;

    private   String endDate;

    public String getProcessInstaneceId() {
        return processInstaneceId;
    }

    public void setProcessInstaneceId(String processInstaneceId) {
        this.processInstaneceId = processInstaneceId;
    }

    public String getProcessDefineId() {
        return processDefineId;
    }

    public void setProcessDefineId(String processDefineId) {
        this.processDefineId = processDefineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLeaveDays() {
        if (startDate != null && endDate != null) {
            leaveDays = differentDaysByMillisecond(startDate,endDate);
        }
        return leaveDays;
    }

    public void setLeaveDays(int leaveDays) {
        this.leaveDays = leaveDays;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNextAssignee() {
        return nextAssignee;
    }

    public void setNextAssignee(String nextAssignee) {
        this.nextAssignee = nextAssignee;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    private   int differentDaysByMillisecond(String date1,String date2)
    {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            Date d1 = dateFormat.parse(date1);
            Date d2 = dateFormat.parse(date2);

            int days = (int) ((d2.getTime() - d1.getTime()) / (1000*3600*24));
            return days + 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
