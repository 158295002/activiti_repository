package com.evada.activiti.util;

import org.springframework.core.env.Environment;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dingqin
 * @date 2018/1/22
 */
public class SessionUtil {

    public static String getCurrentUser(){

        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)attributes;
            HttpServletRequest httpServletRequest = ((HttpServletRequest) servletRequestAttributes.getRequest());
            Object user = httpServletRequest.getSession().getAttribute("user");
            if (user != null && user instanceof String) {
                return (String) user;
            }
            return "";
    }

    public static void addSessionAttribute(String attrName,String attrValue){
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)attributes;
        HttpServletRequest httpServletRequest = ((HttpServletRequest) servletRequestAttributes.getRequest());
        httpServletRequest.getSession().setAttribute(attrName,attrValue);
    }
}
