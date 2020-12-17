package top.touchfish.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author chengccn
 * @version 1.0.0
 * @ClassName GenFileListVo.java
 * @Description 生成的文件列表
 * @createTime 2020-12-17 23:38:00
 */
@Data
@Builder
public class GenFileListVo {

	public List<FileInfoVo> entity;

	public List<FileInfoVo> service;

	public List<FileInfoVo> mapper;

	public List<FileInfoVo> xml;

	public List<FileInfoVo> controller;

	public List<FileInfoVo> serviceImpl;

	public List<FileInfoVo> js;

	public List<FileInfoVo> view;

}
