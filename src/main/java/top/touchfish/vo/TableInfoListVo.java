package top.touchfish.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author chengccn
 * @version 1.0.0
 * @ClassName TableInfoListVo.java
 * @Description
 * @createTime 2020-12-15 01:27:00
 */
@Data
public class TableInfoListVo implements Serializable {

	public String name;

	public String comment;

	public List<String> fields;

}
