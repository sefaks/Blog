package com.example.spring.blogApp.service.impl;

import com.example.spring.blogApp.entity.Post;
import com.example.spring.blogApp.exception.ResourceNotFoundException;
import com.example.spring.blogApp.payload.PostDto;
import com.example.spring.blogApp.repository.PostRepository;
import com.example.spring.blogApp.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
        }

    @Override
    public PostDto createPost(PostDto postDto) {

        //convert Dto to entity
        Post post = mapToEntity(postDto);

        Post newPost = postRepository.save(post);

        //convert entity to DTO
        PostDto postResponse = mapToDTO(newPost);

        return postResponse;
    }
    @Override
    public List<PostDto> getAllPosts(int pageNo,int pageSize) {

        Pageable pageable = PageRequest.of(pageNo,pageSize);

        Page<Post> posts = postRepository.findAll(pageable);

        //get content for page object
        List<Post> listOfPosts = posts.getContent();

        return listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    }
    @Override
    public PostDto getPostById(long id) {
       Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));

       return mapToDTO(post);
    }
    @Override
    public PostDto updatePost(PostDto postDto,long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);

    }
    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));

        postRepository.delete(post);
    }

    //convert entity to PostDTO
    private PostDto mapToDTO(Post post){

        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());

        return postDto;
    }

    // convert PostDTO to entity
    private Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setId (postDto.getId());
        post.setTitle( postDto.getTitle());
        post.setDescription( postDto.getDescription());
        post.setContent( postDto.getContent());

        return post;
    }


}
