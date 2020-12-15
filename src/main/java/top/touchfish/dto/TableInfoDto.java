package top.touchfish.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chengccn
 * @version 1.0.0
 * @ClassName TableInfoDto.java
 * @Description
 * @createTime 2020-12-14 23:29:00
 */
@Data
public class TableInfoDto implements Serializable {
    private String url;
    private String username;
    private String password;
}
