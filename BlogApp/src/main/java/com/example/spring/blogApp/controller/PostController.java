package com.example.spring.blogApp.controller;

import com.example.spring.blogApp.payload.PostDto;
import com.example.spring.blogApp.payload.PostResponse;
import com.example.spring.blogApp.service.PostService;
import com.example.spring.blogApp.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping("/posts")
    public PostResponse getAllPost(
            @RequestParam(value= "pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize,
            @RequestParam(value = "sortBy" , defaultValue = "id",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String  sortDir
    ){
       return  postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name=" id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable(name="id") long id){

        PostDto postResponse =  postService.updatePost(postDto,id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name ="id")  long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post delected succesfly.",HttpStatus.OK);

    }



}
