package com.example.spring.blogApp.service;

import com.example.spring.blogApp.entity.Comment;
import com.example.spring.blogApp.payload.CommentDto;

import java.util.List;

public interface CommentService {


    CommentDto createComment(long postId,CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long PostId);

    CommentDto getCommentById(long postId,long commentId);

    CommentDto updateComment(long postId,long commentId,CommentDto commentRequest);







}
