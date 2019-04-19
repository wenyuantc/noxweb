package com.edoai.framework.noxweb.common.extension.exception;

import com.edoai.framework.noxweb.common.extension.api.IErrorCode;

/**
 * @author Nox (HuangWenYuan)
 * @description: API请求异常
 * @create: 2019-04-19 11:32
 **/
public class ApiException extends RuntimeException {
    /**
     * 错误码
     */
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
