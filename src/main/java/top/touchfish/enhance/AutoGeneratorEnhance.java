package top.touchfish.enhance;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author chengccn
 * @version 1.0.0
 * @ClassName AutoGenneratorImpl.java
 * @Description 代码生成增强 主要是释放TableInfo
 * @createTime 2020-12-14 23:48:00
 */
public class AutoGeneratorEnhance extends AutoGenerator {
    ConfigBuilder configBuilder;
    private AbstractTemplateEngine templateEngine;
    /**
     * 复写父类的方法 释放TableInfo
     *
     * @param configBuilder
     * @return
     */
    @Override
    public List<TableInfo> getAllTableInfoList(ConfigBuilder configBuilder) {
        return super.getAllTableInfoList(configBuilder);
    }

    /**
     * 通过dataSourceConfig构建ConfigBuilder
     *
     * @param dataSourceConfig
     * @return
     */
    public ConfigBuilder builder(DataSourceConfig dataSourceConfig) {
        return new ConfigBuilder(null, dataSourceConfig, null, null, null);
    }

}
