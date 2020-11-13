package com.golden.cloud.common;

import com.alibaba.fastjson.JSONObject;

public class ApiResult {
    private String actionResult = "";
    private Object data = null;
    private String message = "";

    public String getActionResult() {
        return actionResult;
    }

    public void setActionResult(String actionResult) {
        this.actionResult = actionResult;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiResult() {
    }

    public ApiResult(String actionResult) {
        this.actionResult = actionResult;
    }

    public ApiResult(String actionResult, Object data, String message) {
        this.actionResult = actionResult;
        this.data = data;
        this.message = message;
    }

    public static ApiResult success(Object data) {
        return new ApiResult("1", data, "");
    }

    public static ApiResult error(String errMessage) {
        return error("-1", null, errMessage);
    }

    public static ApiResult error(String errorCode, String errMessage) {
        return error(errorCode, null, errMessage);
    }

    public static ApiResult error(String errorCode, Object data, String errMessage) {
        return new ApiResult(errorCode, data, errMessage);
    }

    public String toJSONString() {
        JSONObject result = new JSONObject();
        result.put("actionResult", this.actionResult);
        result.put("data", this.data);
        result.put("message", this.message);
        return result.toJSONString();
    }

    @Override
    public String toString() {
        return toJSONString();
    }
}
