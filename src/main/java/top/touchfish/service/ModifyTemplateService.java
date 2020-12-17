package top.touchfish.service;

import top.touchfish.dto.FileDto;
import top.touchfish.dto.TemplateDto;
import top.touchfish.dto.TemplateListDto;

import java.io.FileNotFoundException;

/**
 * @author chengccn
 * @version 1.0.0
 * @ClassName ModifyTemplateService.java
 * @Description
 * @createTime 2020-12-17 22:00:00
 */
public interface ModifyTemplateService {

	/**
	 * 模板文件列表
	 * @param templateListDto
	 * @return
	 * @throws FileNotFoundException
	 */
	Object listFiles(TemplateListDto templateListDto) throws FileNotFoundException;

	/**
	 * 获取模板文件内容
	 * @param fileDto
	 * @return
	 */
	Object showCode(FileDto fileDto);

	/**
	 * 修改模板
	 * @param templateDto
	 * @return
	 */
	Object modifyTemplate(TemplateDto templateDto) throws FileNotFoundException;

}
