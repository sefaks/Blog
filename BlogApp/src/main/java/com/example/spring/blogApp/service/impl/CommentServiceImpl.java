package com.example.spring.blogApp.service.impl;

import com.example.spring.blogApp.entity.Comment;
import com.example.spring.blogApp.entity.Post;
import com.example.spring.blogApp.exception.BlogAPIException;
import com.example.spring.blogApp.exception.ResourceNotFoundException;
import com.example.spring.blogApp.payload.CommentDto;
import com.example.spring.blogApp.repository.CommentRepository;
import com.example.spring.blogApp.repository.PostRepository;
import com.example.spring.blogApp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private ModelMapper mapper;
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository,ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto){

        Comment comment = mapToEntity(commentDto);

        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));

        comment.setPost(post);

        Comment commentDb = commentRepository.save(comment);

       return mapToDTO(commentDb);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long PostId) {

        List<Comment> comments = commentRepository.findByPostId(PostId);

        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {

        Post post = postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post","id",postId));

        //retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("Comment","id",commentId));

        if(comment.getPost().getId() != post.getId()) {
            throw  new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
        return mapToDTO(comment);
        }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentRequest) {

        Post post = postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if (comment.getPost().getId() != post.getId()) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        comment.setEmail(commentRequest.getEmail());
        comment.setName(commentRequest.getName());
        comment.setBody(commentRequest.getBody());

        Comment updatedComment = commentRepository.save(comment);

        return mapToDTO(updatedComment);

    }

    @Override
    public void deleteComment(long postId, long commentId) {

        Post post = postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if (comment.getPost().getId() != post.getId()) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        commentRepository.delete(comment);


    }

    private CommentDto mapToDTO(Comment comment){

        CommentDto  commentDto = mapper.map(comment,CommentDto.class);
     //   commentDto.setId(comment.getId());
      //  commentDto.setBody(comment.getBody());
      //  commentDto.setEmail(comment.getEmail());
      //  commentDto.setName(comment.getName());

        return commentDto;
    }
    // convert CommentDTO to entity
    private Comment mapToEntity(CommentDto commentDto){

        Comment comment = mapper.map(commentDto,Comment.class);

     //   comment.setId (commentDto.getId());
      //  comment.setBody( commentDto.getBody());
      //  comment.setEmail( commentDto.getEmail());
      //  comment.setName( commentDto.getName());

        return comment;
    }
}
