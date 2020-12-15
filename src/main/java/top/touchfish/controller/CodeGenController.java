package top.touchfish.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.touchfish.common.R;
import top.touchfish.dto.GenInfoDto;
import top.touchfish.dto.TableInfoDto;
import top.touchfish.service.CodeGenService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chengccn
 * @version 1.0.0
 * @ClassName CodeGenController.java
 * @Description
 * @createTime 2020-12-14 22:51:00
 */
@RestController()
public class CodeGenController {
    @Autowired
    CodeGenService codeGenService;

    /**
     * 查询表信息
     *
     * @param tableInfoDto
     * @return
     */
    @PostMapping("getTableInfo")
    public R getTableInfo(@RequestBody TableInfoDto tableInfoDto) {
        return R.success(codeGenService.getTableInfo(tableInfoDto));
    }

    /**
     * 下载生成的代码
     *
     * @param genInfoDto
     * @param response
     * @throws IOException
     */
    @PostMapping("generatorCode")
    public void generatorCode(@RequestBody GenInfoDto genInfoDto, HttpServletResponse response) throws IOException {
        byte[] bytes = codeGenService.generatorCode(genInfoDto);
        response.reset();
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s.zip", "codegen"));
        response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(bytes.length));
        response.setContentType("application/octet-stream; charset=UTF-8");

        IoUtil.write(response.getOutputStream(), Boolean.TRUE, bytes);
        // 删除文件
        String codegen = ResourceUtils.getURL("classpath:codegen").getPath();
        FileUtil.del(codegen);
    }

}
