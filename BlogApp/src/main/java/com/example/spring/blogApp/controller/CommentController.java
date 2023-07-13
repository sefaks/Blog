package com.example.spring.blogApp.controller;


import com.example.spring.blogApp.payload.CommentDto;

import com.example.spring.blogApp.service.CommentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @Valid @RequestBody CommentDto commentDto){

       return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/get/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value= "postId") long postId){

        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId,
                                                  @PathVariable(value = "id") long commentId){

        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{id}/update")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value="postId") long postId,
                                                    @PathVariable(value="id") long commentId,
                                                    @Valid @RequestBody CommentDto commentRequest){

        CommentDto updatedComment = commentService.updateComment(postId,commentId,commentRequest);
        return new ResponseEntity<>(updatedComment,HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}/comments/{id}/delete")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId")long postId,
                                                 @PathVariable(value="id")long commentId){

        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("Comment delected succesfly.",HttpStatus.OK);
    }
}
