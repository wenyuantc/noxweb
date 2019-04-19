package com.edoai.framework.noxweb.common.extension.enums;

import com.edoai.framework.noxweb.common.extension.api.IErrorCode;

/**
 * @author Nox (HuangWenYuan)
 * @description: REST API错误码
 * @create: 2019-04-19 11:28
 **/
public enum ApiErrorCode implements IErrorCode {
    /**
     * 失败
     */
    FAILED(-1, "api.failed"),
    /**
     * 成功
     */
    SUCCESS(0, "api.success");

    private final long code;
    private final String msg;

    ApiErrorCode(final long code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ApiErrorCode fromCode(long code) {
        ApiErrorCode[] ecs = ApiErrorCode.values();
        for (ApiErrorCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return String.format(" ErrorCode:{code=%s, msg=%s} ", code, msg);
    }
}
