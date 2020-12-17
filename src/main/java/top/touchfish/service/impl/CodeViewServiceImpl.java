package top.touchfish.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import top.touchfish.common.CodeGenConstant;
import top.touchfish.dto.CodeViewDto;
import top.touchfish.dto.FileListDto;
import top.touchfish.service.CodeViewService;
import top.touchfish.vo.FileInfoVo;
import top.touchfish.vo.GenFileListVo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chengccn
 * @version 1.0.0
 * @ClassName CodeViewServiceImpl.java
 * @Description
 * @createTime 2020-12-17 22:01:00
 */
@Service
@Slf4j
public class CodeViewServiceImpl implements CodeViewService {

    /**
     * 查询生成的文件的列表
     *
     * @param fileListDto
     * @return
     * @throws FileNotFoundException
     */
    @Override
    public GenFileListVo listFiles(FileListDto fileListDto) throws FileNotFoundException {
        String moduleName = fileListDto.getModuleName();
        String packageName = "/" + fileListDto.getPackageName().replace(".", "/") + "/" + moduleName + "/";
        String basePath = ResourceUtils.getURL(CodeGenConstant.BASE_PATH).getPath();
        String entityPath = basePath + packageName + "entity";
        String controllerPath = basePath + packageName + "controller";
        String servicePath = basePath + packageName + "service";
        String serviceImplPath = basePath + packageName + "service/impl";
        String mapperPath = basePath + "/mapper/" + moduleName;
        String jsPath = basePath + "/vue//api/" + moduleName;
        String viewPath = basePath + "/vue/view/" + moduleName;
        String xmlPath = basePath + "/mapper/" + moduleName;

        return GenFileListVo.builder().entity(build(entityPath)).controller(build(controllerPath))
                .service(build(servicePath)).serviceImpl(build(serviceImplPath)).mapper(build(mapperPath))
                .js(build(jsPath)).view(build(viewPath)).xml(build(xmlPath)).build();
    }

    @Override
    public Object showCode(CodeViewDto codeViewDto) {
        FileReader fileReader = new FileReader(codeViewDto.getFilePath());
        return fileReader.readString();
    }

    /**
     * 传参构建List<FileInfoVo>
     *
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public List<FileInfoVo> build(String path) throws FileNotFoundException {
        return FileUtil.loopFiles(path).stream()
                .map(f -> FileInfoVo.builder().fileName(f.getName()).filePath(f.getPath()).build())
                .collect(Collectors.toList());
    }

}
