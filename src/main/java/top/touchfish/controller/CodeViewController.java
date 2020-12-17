package top.touchfish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.touchfish.common.R;
import top.touchfish.dto.CodeViewDto;
import top.touchfish.dto.FileListDto;
import top.touchfish.service.CodeViewService;

import java.io.FileNotFoundException;

/**
 * @author chengccn
 * @version 1.0.0
 * @ClassName CodeViewController.java
 * @Description 生成的代码在线预览
 * @createTime 2020-12-17 20:32:00
 */
@RestController
@RequestMapping("code")
public class CodeViewController {

	@Autowired
	CodeViewService codeViewService;

	/**
	 * 获取生成的代码列表
	 * @param fileListDto
	 * @return
	 * @throws FileNotFoundException
	 */
	@PostMapping("listFiles")
	public R getCodeView(@RequestBody FileListDto fileListDto) throws FileNotFoundException {
		return R.success(codeViewService.listFiles(fileListDto));
	}

	/**
	 * 查看生成代码详情
	 * @param codeViewDto
	 * @return
	 */
	@PostMapping("showCode")
	public R showCode(@RequestBody CodeViewDto codeViewDto){
		return R.success(codeViewService.showCode(codeViewDto));
	}
}
