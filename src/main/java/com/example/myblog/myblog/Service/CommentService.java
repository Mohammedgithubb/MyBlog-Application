package com.example.myblog.myblog.Service;

import com.example.myblog.myblog.Dto.CommentDto;
import com.example.myblog.myblog.Service.Impl.CommentServiceImpl;

import java.util.List;

public interface CommentService {

    public CommentDto createComment(long postId, CommentDto commentDto);

    public void deleteCommentById(long postId, long commentId);

    public List<CommentDto> getCommentsByPostId(long postId);


    public CommentDto updateComment(long commentId, CommentDto commentDto);
}
