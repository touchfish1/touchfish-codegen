package top.touchfish.service;

import top.touchfish.dto.CodeViewDto;
import top.touchfish.dto.FileListDto;
import top.touchfish.vo.GenFileListVo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author chengccn
 * @version 1.0.0
 * @ClassName CodeViewService.java
 * @Description
 * @createTime 2020-12-17 21:59:00
 */
public interface CodeViewService {

	/**
	 * 获取生成的文件列表
	 * @return
	 * @throws FileNotFoundException
	 */
	GenFileListVo listFiles(FileListDto fileListDto) throws FileNotFoundException;

	Object showCode(CodeViewDto codeViewDto);
}
