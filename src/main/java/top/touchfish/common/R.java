package top.touchfish.common;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chengccn
 * @version 1.0.0
 * @ClassName R.java
 * @Description
 * @createTime 2020-12-09 00:47:00
 */
@Data
@NoArgsConstructor
public class R<T> {
	/**
	 * 状态码
	 */
	private Integer code;

	/**
	 * 返回消息
	 */
	private String message;

	/**
	 * 状态
	 */
	private boolean status;

	/**
	 * 返回数据
	 */
	private T data;

	public R(Integer code, String message, boolean status, T data) {
		this.code = code;
		this.message = message;
		this.status = status;
		this.data = data;
	}

	public R(IResultCode resultCode, boolean status, T data) {
		this.code = resultCode.getCode();
		this.message = resultCode.getMessage();
		this.status = status;
		this.data = data;
	}

	public R(IResultCode resultCode, boolean status) {
		this.code = resultCode.getCode();
		this.message = resultCode.getMessage();
		this.status = status;
		this.data = null;
	}

	public static <T> R success() {
		return new R<>(ResultCode.OK, true);
	}

	public static <T> R message(String message) {
		return new R<>(ResultCode.OK.getCode(), message, true, null);
	}

	public static <T> R success(T data) {
		return new R<>(ResultCode.OK, true, data);
	}

	public static <T> R fail() {
		return new R<>(ResultCode.ERROR, false);
	}

	public static <T> R fail(IResultCode resultCode) {
		return new R<>(resultCode, false);
	}

	public static <T> R fail(Integer code, String message) {
		return new R<>(code, message, false, null);
	}

	public static <T> R fail(IResultCode resultCode, T data) {
		return new R<>(resultCode, false, data);
	}

	public static <T> R fail(Integer code, String message, T data) {
		return new R<>(code, message, false, data);
	}

}
