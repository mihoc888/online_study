package com.wxs.service.impl;

import com.wxs.mapper.CommentMapper;
import com.wxs.po.Comments;
import com.wxs.service.CommentService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 根据 vid 查出 它下面的所有评论.
     *
     * @param vid
     * @return
     */
    @Override
    public ArrayList<ArrayList<Comments>> getComments(Integer vid) {
        ArrayList<ArrayList<Comments>> result = new ArrayList<ArrayList<Comments>>();
        // 查出一级id , pid = 0
        List<Comments> comments = commentMapper.findByPid(vid,0);// 一级列表 ( 学生评论 )
        for (Comments comment : comments) {
            ArrayList<Comments> list = new ArrayList<Comments>();
            list.add(comment);// 先将学生评论封装好
            Integer id = comment.getId();
            List<Comments> comments1 = commentMapper.findByPid(vid, id);// 二级列表 ( 教师评论 )
            // 由于评论是设计成 list :  学生评论 、教师评论 所以二级列表 永远只有一个
            if (comments1.size()!=0) {
                Comments comments2 = comments1.get(0); // 教师评论
                list.add(comments2);// 封装 教师评论
            }
            // 控制学生的评论 pid 总是 0 , 教师评论总是 不为0 --> 只有二级结构
            // 将封装好的list 加入 result 集合
            result.add(list);
        }

         return result;
    }

    @Override
    public void addComment(Integer qid, String textarea,Integer courseId) {
        Object principal = SecurityUtils.getSubject().getPrincipal();
        commentMapper.addComment(qid,textarea,System.currentTimeMillis(),courseId,principal.toString());//
    }
}
