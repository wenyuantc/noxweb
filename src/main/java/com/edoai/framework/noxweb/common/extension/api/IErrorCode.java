package com.edoai.framework.noxweb.common.extension.api;

/**
 * @author Nox (HuangWenYuan)
 * @description: 错误码接口
 * @create: 2019-04-19 11:31
 **/
public interface IErrorCode {
    /**
     * 错误编码 -1、失败 0、成功
     */
    long getCode();

    /**
     * 错误描述
     */
    String getMsg();
}
