package top.touchfish.common;

import lombok.Getter;

/**
 * @author chengccn
 * @version 1.0.0
 * @ClassName ResultCode.java
 * @Description
 * @createTime 2020-12-14 18:53:00
 */
@Getter
public enum ResultCode implements IResultCode {
    /**
     * 成功
     */
    OK(200, "成功"),
    /**
     * 失败
     */
    ERROR(500, "失败");

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}

