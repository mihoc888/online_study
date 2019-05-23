package com.wxs.mapper;

import com.wxs.po.Vedio;

import java.util.List;

public interface VedioMapper {
    void addVedio(String name, String saveFilePath,
                  Integer cid, String id, String s);

    List<Vedio> getByCid(Integer cid);

    String getPath(Integer vid);

    String getCourseName(Integer vid);

    void delete(Integer vid);
    // 文件名，储存路径，课程id，用户登录名，时间戳

}
