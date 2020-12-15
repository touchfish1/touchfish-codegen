package tou.touchfish;

import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import top.touchfish.enhance.AutoGeneratorEnhance;

import java.util.List;

/**
 * @author chengccn
 * @version 1.0.0
 * @ClassName TableInfoTest.java
 * @Description
 * @createTime 2020-12-14 23:41:00
 */
@RunWith(SpringRunner.class)
public class TableInfoTest {
    /**
     * 测试复写代码
     */
    @Test
    public void getTableInfo(){
        // 代码生成器
        AutoGeneratorEnhance mpg = new AutoGeneratorEnhance();
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/touchfish_admin?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&useSSL=false&serverTimezone=Asia/Shanghai");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        // 测试复写的获取TableInfoList
        List<TableInfo> allTableInfoList = mpg.getAllTableInfoList(mpg.builder(dsc));
        allTableInfoList.forEach(tableInfo -> System.out.println(tableInfo));
    }

}
