package top.touchfish.enhance;

import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import lombok.Data;

/**
 * @author chengccn
 * @version 1.0.0
 * @ClassName TemplateConfigEnhance.java
 * @Description 模板增强 加入两个模板配置 后续可自定义增加
 * @createTime 2020-12-15 23:52:00
 */
@Data
public class TemplateConfigEnhance extends TemplateConfig {
    String api = "/templates/api.js";
    String view = "/templates/view.vue";
}
