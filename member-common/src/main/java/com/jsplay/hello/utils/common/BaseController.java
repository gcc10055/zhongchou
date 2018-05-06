package com.jsplay.hello.utils.common;

import com.jsplay.hello.utils.bean.AJAXResult;

/**
 * Created by Administrator on 2018/4/22.
 */
public abstract class BaseController {

    private ThreadLocal<AJAXResult> results = new ThreadLocal<AJAXResult>();
    protected void start() {
        results.set(new AJAXResult());
    }

    protected Object end() {
        Object obj = results.get();
        results.remove();
        return obj;
    }

    protected void success( boolean flg ) {
        AJAXResult result = results.get();
        result.setSuccess(flg);
    }

    protected void success() {
        success(true);
    }

    protected void fail() {
        success(false);
    }

    protected void data( Object data ) {
        AJAXResult result = results.get();
        result.setData(data);
    }

}
