package top.touchfish.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author chengccn
 * @version 1.0.0
 * @ClassName FileInfo.java
 * @Description
 * @createTime 2020-12-17 23:54:00
 */
@Data
@Builder
@AllArgsConstructor
public class FileInfoVo {

	public String fileName;

	public String filePath;

}
