package com.example.spring.blogApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="posts", uniqueConstraints = {@UniqueConstraint(columnNames =  "title")})
@Getter
@Setter

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="title",nullable = false)
    private String title;
    @Column(name="description",nullable = false)
    private String description;

    @Column(name="content",nullable = false)
    private String content;
}
