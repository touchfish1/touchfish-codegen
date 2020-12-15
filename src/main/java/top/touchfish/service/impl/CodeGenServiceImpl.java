package top.touchfish.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import top.touchfish.common.R;
import top.touchfish.dto.GenInfoDto;
import top.touchfish.dto.TableInfoDto;
import top.touchfish.enhance.AutoGeneratorEnhance;
import top.touchfish.service.CodeGenService;
import top.touchfish.util.ZipUtils;
import top.touchfish.vo.TableInfoListVo;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * @author chengccn
 * @version 1.0.0
 * @ClassName CodeGenServiceImpl.java
 * @Description
 * @createTime 2020-12-14 23:28:00
 */
@Service
@Slf4j
public class CodeGenServiceImpl implements CodeGenService {
    private DataSourceConfig dsc;

    @Override
    public R getTableInfo(TableInfoDto tableInfoDto) {
        dsc = new DataSourceConfig();
        dsc.setUrl(tableInfoDto.getUrl());
        // dsc.setSchemaName("public");
        dsc.setDriverName(checkDriverName(tableInfoDto.getUrl()));
        dsc.setUsername(tableInfoDto.getUsername());
        dsc.setPassword(tableInfoDto.getPassword());
        AutoGeneratorEnhance autoGenerator = new AutoGeneratorEnhance();
        return R.success(buildVo(autoGenerator.getAllTableInfoList(autoGenerator.builder(dsc))));
    }

    @Override
    public byte[] generatorCode(GenInfoDto genInfoDto) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        String genfile = ResourceUtils.getURL("classpath:genfile").getPath();
        String mapper = ResourceUtils.getURL("classpath:mapper").getPath();
        ZipUtils.doCompress(genfile, zip);
        ZipUtils.doCompress(mapper, zip);
        IoUtil.close(zip);
        // 删除文件
        FileUtil.del(genfile);
        FileUtil.del(mapper);
        return outputStream.toByteArray();
    }

    @Override
    public byte[] generatorCode1() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        String genfile = ResourceUtils.getURL("classpath:genfile").getPath();
        String mapper = ResourceUtils.getURL("classpath:mapper").getPath();
        ZipUtils.doCompress(genfile, zip);
        ZipUtils.doCompress(mapper, zip);
        IoUtil.close(zip);
        // 删除文件
        FileUtil.del(genfile);
        FileUtil.del(mapper);
        return outputStream.toByteArray();
    }

    /**
     * 根据url判断数据库驱动
     *
     * @param url
     * @return
     */
    private String checkDriverName(String url) {
        String driverClassName = null;
        if (url.contains("jdbc:mysql://")) {
            driverClassName = "com.mysql.jdbc.Driver";
        } else if (url.contains("jdbc:oracle:thin:@//")) {
            driverClassName = "oracle.jdbc.driver.OracleDriver";
        } else if (url.contains("jdbc:jtds:sqlserver://")) {
            driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        }
        return driverClassName;
    }

    /**
     * 构建前端返回对象
     *
     * @param tableInfos
     * @return
     */
    private List<TableInfoListVo> buildVo(List<TableInfo> tableInfos) {
        List<TableInfoListVo> listVos = new ArrayList<>();
        for (TableInfo tableInfo : tableInfos) {
            TableInfoListVo tableInfoListVo = new TableInfoListVo();
            BeanUtils.copyProperties(tableInfo, tableInfoListVo);
            List<String> fields = new ArrayList<>();
            for (String field : tableInfo.getFieldNames().split(",")) {
                fields.add(field);
            }
            tableInfoListVo.setFields(fields);
            listVos.add(tableInfoListVo);
        }
        return listVos;
    }
}
