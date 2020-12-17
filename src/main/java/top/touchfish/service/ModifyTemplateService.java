package top.touchfish.service;

import top.touchfish.dto.TemplateDto;
import top.touchfish.dto.TemplateListDto;

/**
 * @author chengccn
 * @version 1.0.0
 * @ClassName ModifyTemplateService.java
 * @Description
 * @createTime 2020-12-17 22:00:00
 */
public interface ModifyTemplateService {

    Object listFiles(TemplateListDto templateListDto);

    Object showCode(TemplateListDto templateListDto);

    Object modifyTemplate(TemplateDto templateDto);
}
