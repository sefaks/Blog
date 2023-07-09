package com.example.spring.blogApp.repository;

import com.example.spring.blogApp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

}
