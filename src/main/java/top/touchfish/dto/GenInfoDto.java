package top.touchfish.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chengccn
 * @version 1.0.0
 * @ClassName GenInfoDto.java
 * @Description
 * @createTime 2020-12-15 02:34:00
 */
@Data
public class GenInfoDto implements Serializable {
    private String[] tableNames;
    private String author;
    private String moduleName;
    private String packageName;
}
