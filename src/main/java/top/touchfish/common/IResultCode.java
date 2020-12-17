package top.touchfish.common;

/**
 * @author chengccn
 * @version 1.0.0
 * @ClassName IResultCode.java
 * @Description
 * @createTime 2020-12-14 18:53:00
 */
public interface IResultCode {

	/**
	 * 获取状态码
	 * @return 状态码
	 */
	Integer getCode();

	/**
	 * 获取返回消息
	 * @return 返回消息
	 */
	String getMessage();

}
