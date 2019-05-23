package com.wxs.service.impl;

import com.wxs.mapper.CommentMapper;
import com.wxs.mapper.VedioMapper;
import com.wxs.po.Comments;
import com.wxs.po.Userlogin;
import com.wxs.po.Vedio;
import com.wxs.service.CommentService;
import com.wxs.service.VedioService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VedioServiceImpl implements VedioService {

    @Autowired
    private VedioMapper vedioMapper;

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public void addVedio(String name, String saveFilePath, Integer cid) {
        Object principal = SecurityUtils.getSubject().getPrincipal();
        System.out.println(principal.toString());
        vedioMapper.addVedio(name, saveFilePath, cid, principal.toString(), System.currentTimeMillis() + "");
    }

    @Override
    public List<Vedio> getByCid(Integer cid) {
        return vedioMapper.getByCid(cid);
    }

    @Override
    public String getPath(Integer vid) {
        return vedioMapper.getPath(vid);
    }

    @Override
    public String getCourseName(Integer vid) {
        return vedioMapper.getCourseName(vid);
    }

}
