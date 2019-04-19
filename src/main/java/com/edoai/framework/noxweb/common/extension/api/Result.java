package com.edoai.framework.noxweb.common.extension.api;

import com.edoai.framework.noxweb.common.extension.enums.ApiErrorCode;
import com.edoai.framework.noxweb.common.extension.exception.ApiException;
import com.edoai.framework.noxweb.common.utils.ApplicationContextUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Locale;
import java.util.Optional;

/**
 * @author Nox (HuangWenYuan)
 * @description:
 * @create: 2019-04-19 11:30
 **/
@Data
@Accessors(chain = true)
@Component
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 6484048303060492716L;

    private static ResourceBundleMessageSource messageSource;

    /**
     * 业务错误码
     */
    private long code;
    /**
     * 结果集
     */
    private T data;
    /**
     * 描述
     */
    private String msg;

    public Result() {
        // to do nothing
    }



    public Result(IErrorCode errorCode) {
        errorCode = Optional.ofNullable(errorCode).orElse(ApiErrorCode.FAILED);
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public static <T> Result<T> ok(T data) {
        ApiErrorCode aec = ApiErrorCode.SUCCESS;
        if (data instanceof Boolean && Boolean.FALSE.equals(data)) {
            aec = ApiErrorCode.FAILED;
        }
        return restResult(data, aec);
    }

    public static <T> Result<T> failed(String msg) {
        return restResult(null, ApiErrorCode.FAILED.getCode(), msg);
    }

    public static <T> Result<T> failed(IErrorCode errorCode) {
        return restResult(null, errorCode);
    }

    public static <T> Result<T> restResult(T data, IErrorCode errorCode) {
        return restResult(data, errorCode.getCode(), errorCode.getMsg());
    }

    private static <T> Result<T> restResult(T data, long code, String msg) {
        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundleMessageSource messageSource = ApplicationContextUtil.getBean(ResourceBundleMessageSource.class);
        Result<T> apiResult = new Result<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(messageSource.getMessage(msg, null, locale));
        return apiResult;
    }

    public boolean ok() {
        return ApiErrorCode.SUCCESS.getCode() == code;
    }

    /**
     * 服务间调用非业务正常，异常直接释放
     */
    public T serviceData() {
        if (!ok()) {
            throw new ApiException(this.msg);
        }
        return data;
    }
}
