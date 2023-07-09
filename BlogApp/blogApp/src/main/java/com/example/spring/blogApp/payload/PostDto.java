package com.example.spring.blogApp.payload;

import lombok.Data;
import org.hibernate.annotations.Target;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Data
public class PostDto {

    private Long id;
    private String title;
    private String description;
    private String content;
}
