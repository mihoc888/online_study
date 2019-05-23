package com.wxs.service;

import com.wxs.po.Comments;
import com.wxs.po.Vedio;

import java.util.List;

public interface VedioService {
    void addVedio(String name, String saveFilePath, Integer cid);

    List<Vedio> getByCid(Integer cid);

    String getPath(Integer vid);

    String getCourseName(Integer vid);

    void delete(Integer vid);
}
