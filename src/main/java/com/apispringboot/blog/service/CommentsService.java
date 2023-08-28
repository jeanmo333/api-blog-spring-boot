package com.apispringboot.blog.service;

import com.apispringboot.blog.Dto.CommentDto;

import java.util.List;

public interface CommentsService {
    public CommentDto createComment(long postId, CommentDto commentDto);

    public List<CommentDto> findAllCommentByPostId(long postId);

    public CommentDto findAllCommentById(Long postId,Long commentId);


    public CommentDto updateComment(Long postId,Long commentId,CommentDto commentDto);

    public void deleteComment(Long postId,Long commentId);
}
