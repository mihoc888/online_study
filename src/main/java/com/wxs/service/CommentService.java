package com.wxs.service;

import com.wxs.po.Comments;

import java.util.ArrayList;
import java.util.List;

public interface CommentService {
    ArrayList<ArrayList<Comments>> getComments(Integer vid);

    void addComment(Integer qid, String textarea, Integer courseId);

}
