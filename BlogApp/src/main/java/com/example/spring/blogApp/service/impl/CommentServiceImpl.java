package com.example.spring.blogApp.service.impl;

import com.example.spring.blogApp.entity.Comment;
import com.example.spring.blogApp.entity.Post;
import com.example.spring.blogApp.exception.ResourceNotFoundException;
import com.example.spring.blogApp.payload.CommentDto;
import com.example.spring.blogApp.repository.CommentRepository;
import com.example.spring.blogApp.repository.PostRepository;
import com.example.spring.blogApp.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto){

        Comment comment = mapToEntity(commentDto);

        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));

        comment.setPost(post);

        Comment commentDb = commentRepository.save(comment);

       return mapToDTO(commentDb);

    }

    private CommentDto mapToDTO(Comment comment){

        CommentDto  commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setBody(comment.getBody());
        commentDto.setEmail(comment.getEmail());
        commentDto.setName(comment.getName());

        return commentDto;
    }

    // convert CommentDTO to entity
    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId (commentDto.getId());
        comment.setBody( commentDto.getBody());
        comment.setEmail( commentDto.getEmail());
        comment.setName( commentDto.getName());

        return comment;
    }
}
