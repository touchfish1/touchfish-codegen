package top.touchfish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.touchfish.common.R;
import top.touchfish.dto.*;
import top.touchfish.service.ModifyTemplateService;

import java.io.FileNotFoundException;

/**
 * @author chengccn
 * @version 1.0.0
 * @ClassName ModifyTemplateController.java
 * @Description 在线模板
 * @createTime 2020-12-17 21:55:00
 */
@RestController
@RequestMapping("template")
public class ModifyTemplateController {

	@Autowired
	ModifyTemplateService modifyTemplateService;

	/**
	 * 模板文件列表
	 * @param templateListDto
	 * @return
	 * @throws FileNotFoundException
	 */
	@PostMapping("listFiles")
	public R getCodeView(@RequestBody TemplateListDto templateListDto) throws FileNotFoundException {
		return R.success(modifyTemplateService.listFiles(templateListDto));
	}

	/**
	 * 获取模板文件内容
	 * @param fileDto
	 * @return
	 */
	@PostMapping("showCode")
	public R showCode(@RequestBody FileDto fileDto) {
		return R.success(modifyTemplateService.showCode(fileDto));
	}

	/**
	 * 修改模板
	 * @param templateDto
	 * @return
	 */
	@PostMapping("modifyTemplate")
	public R modifyTemplate(@RequestBody TemplateDto templateDto) throws FileNotFoundException {
		return R.success(modifyTemplateService.modifyTemplate(templateDto));
	}

}
