package top.touchfish.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.google.common.base.CaseFormat;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import top.touchfish.common.CodeGenConstant;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    /**
     * 查询TableInfo接口
     *
     * @param tableInfoDto
     * @return
     */
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

    /**
     * 生成代码下载
     *
     * @param genInfoDto
     * @return
     */
    @Override
    public byte[] generatorCode(GenInfoDto genInfoDto) throws IOException {
        // 生成文件
        generatorFile(genInfoDto);
        // 打包压缩
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        String codegen = ResourceUtils.getURL("classpath:codegen" ).getPath();
        ZipUtils.doCompress(codegen, zip);
        IoUtil.close(zip);
        // 下载
        return outputStream.toByteArray();
    }

    /**
     * 使用mp的代码生成软件生成软件
     *
     * @param genInfoDto
     */
    public void generatorFile(GenInfoDto genInfoDto) throws FileNotFoundException {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        final String outPath = ResourceUtils.getURL("classpath:" ).getPath() + "/codegen";
        gc.setOutputDir(outPath);
        gc.setAuthor(genInfoDto.getAuthor());
        gc.setOpen(false);
        //实体属性 Swagger2 注解
        gc.setSwagger2(genInfoDto.getSwagger());
        gc.setServiceName("%sService" );
        mpg.setGlobalConfig(gc);
        // 设置数据源
        mpg.setDataSource(dsc);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(genInfoDto.getModuleName());
        pc.setParent(genInfoDto.getPackageName());
        mpg.setPackageInfo(pc);
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

            }

            @Override
            public Map<String, Object> prepareObjectMap(Map<String, Object> objectMap) {
                // 获取到的table
                TableInfo table = (TableInfo) objectMap.get("table" );
                // 获取到的entity
                String entity = (String) objectMap.get("entity" );
                // 在配置中放入小驼峰的className 即为entity的小驼峰写法
                objectMap.put("className", toLowerCaseFirstOne(entity));
                // 放入pk主键名称
                TableField tableField = table.getFields().stream().filter(f -> f.isKeyFlag()).findFirst().get();
                objectMap.put("pkName", tableField.getPropertyName());
                objectMap.put("pkType", tableField.getColumnType().getType());
                return objectMap;
            }
        };
        String xmlPath = "/templates/mapper.xml.vm";
        // js模板
        String apiPath = "/templates/api.js.vm";
        // vue模板
        String vuePath = "/templates/view.vue.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(xmlPath) {
            @SneakyThrows
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return outPath + "/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        focList.add(new FileOutConfig(apiPath) {
            @SneakyThrows
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return outPath + "/vue/api/" + pc.getModuleName()
                        + "/" + toLowerCaseFirstOne(tableInfo.getEntityName()) + ".js";
            }
        });
        focList.add(new FileOutConfig(vuePath) {
            @SneakyThrows
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return outPath + "/vue/view/" + pc.getModuleName()
                        + "/" + toLowerCaseFirstOne(tableInfo.getEntityName()) + ".vue";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        strategy.setEntityLombokModel(genInfoDto.getLombok());
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(false);
        strategy.setInclude(genInfoDto.getTableNames());
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_" );
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }

    /**
     * 根据url判断数据库驱动
     *
     * @param url
     * @return
     */
    private String checkDriverName(String url) {
        String driverClassName = null;
        if (url.contains(CodeGenConstant.MYSQL_DRIVER_STR)) {
            driverClassName = CodeGenConstant.MYSQL_DRIVER;
        } else if (url.contains(CodeGenConstant.ORACLE_DRIVER_STR)) {
            driverClassName = CodeGenConstant.ORACLE_DRIVER;
        } else if (url.contains(CodeGenConstant.SQLSERVER_DRIVER_STR)) {
            driverClassName = CodeGenConstant.SQLSERVER_DRIVER;
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
            for (String field : tableInfo.getFieldNames().split("," )) {
                fields.add(field);
            }
            tableInfoListVo.setFields(fields);
            listVos.add(tableInfoListVo);
        }
        return listVos;
    }

    /**
     * 首字母转小写
     *
     * @param s
     * @return
     */
    private String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }
}
