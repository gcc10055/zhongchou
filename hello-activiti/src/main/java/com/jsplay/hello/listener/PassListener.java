package com.jsplay.hello.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/**
 * Created by Administrator on 2018/5/5.
 */
public class PassListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution execution) throws Exception {
        System.out.println("流程审核通过 ！");
    }
}
