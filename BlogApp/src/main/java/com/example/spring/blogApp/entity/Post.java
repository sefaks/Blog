package com.example.spring.blogApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="posts", uniqueConstraints = {@UniqueConstraint(columnNames =  "title")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="title",nullable = false)
    private String title;

    private String notTitle;
    
    @Column(name="description",nullable = false)
    private String description;

    @Column(name="content",nullable = false)
    private String content;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

}
