package top.touchfish.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author chengccn
 * @version 1.0.0
 * @ClassName PageResult.java
 * @Description
 * @createTime 2020-12-14 18:53:00
 */
@Data
@AllArgsConstructor
public class PageResult<T> {
    /**
     * 总条数
     */
    private Long total;

    /**
     * 页码
     */
    private int pageNumber;

    /**
     * 每页结果数
     */
    private int pageSize;

    /**
     * 结果集
     */
    private List<T> list;
}

