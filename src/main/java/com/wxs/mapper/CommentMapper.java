package com.wxs.mapper;

import com.wxs.po.Comments;

import java.util.List;

public interface CommentMapper {
    List<Comments> findByPid(Integer vid, int i);

    void addComment(Integer qid, String textarea, long l, Integer courseId, String s);
}
