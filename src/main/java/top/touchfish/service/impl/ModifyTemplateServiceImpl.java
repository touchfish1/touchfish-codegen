package top.touchfish.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import top.touchfish.dto.FileDto;
import top.touchfish.dto.TemplateDto;
import top.touchfish.dto.TemplateListDto;
import top.touchfish.service.ModifyTemplateService;
import top.touchfish.vo.FileInfoVo;

import java.io.FileNotFoundException;
import java.util.stream.Collectors;

/**
 * @author chengccn
 * @version 1.0.0
 * @ClassName ModifyTemplateServiceImpl.java
 * @Description
 * @createTime 2020-12-17 22:01:00
 */
@Service
@Slf4j
public class ModifyTemplateServiceImpl implements ModifyTemplateService {

    /**
     * 模板文件列表
     *
     * @param templateListDto
     * @return
     * @throws FileNotFoundException
     */
    @Override
    public Object listFiles(TemplateListDto templateListDto) throws FileNotFoundException {
        // 现在就只有一套前端模板 写死路径 以后多了几套从参数中选择
        String templatesPath = ResourceUtils.getURL("classpath:templates").getPath();

        return FileUtil.loopFiles(templatesPath).stream()
                .map(f -> FileInfoVo.builder().fileName(f.getName()).filePath(f.getPath()).build())
                .collect(Collectors.toList());
    }

    /**
     * 获取模板文件内容
     *
     * @param fileDto
     * @return
     */
    @Override
    public Object showCode(FileDto fileDto) {
        FileReader fileReader = new FileReader(fileDto.getFilePath());
        return fileReader.readString();
    }

    /**
     * 修改模板
     *
     * @param templateDto
     * @return
     */
    @Override
    public Object modifyTemplate(TemplateDto templateDto) throws FileNotFoundException {
        // 现在就只有一套前端模板 写死路径 以后多了几套从参数中选择
        /**
         * 1 修改两个地方的模板文件
         *   a) 项目代码目录的templates下的模板文件
         *   b) 项目当前classpath:templates下的模板文件
         */
        String projectPath = System.getProperty("user.dir");
        String projectTemplatesPath = projectPath + "resources" + "templates" + templateDto.getFileName();
        // 修改project下的文件
        FileWriter projectWriter = new FileWriter(projectTemplatesPath);
        projectWriter.write(templateDto.getModifyData());
        // 修改classpath下的文件
        FileWriter writer = new FileWriter(templateDto.getFilePath());
        writer.write(templateDto.getModifyData());
        return null;
    }

}
