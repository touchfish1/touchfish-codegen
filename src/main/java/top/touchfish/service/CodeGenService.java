package top.touchfish.service;

import top.touchfish.common.R;
import top.touchfish.dto.GenInfoDto;
import top.touchfish.dto.TableInfoDto;

import java.io.IOException;

/**
 * @author chengccn
 * @version 1.0.0
 * @ClassName CodeGenService.java
 * @Description
 * @createTime 2020-12-14 23:27:00
 */
public interface CodeGenService {
    /**
     * 查询TableInfo接口
     *
     * @param tableInfoDto
     * @return
     */
    public R getTableInfo(TableInfoDto tableInfoDto);

    /**
     * 生成代码下载
     *
     * @param genInfoDto
     * @return
     */
    public byte[] generatorCode(GenInfoDto genInfoDto) throws IOException;

    public byte[] generatorCode1() throws IOException;
}
