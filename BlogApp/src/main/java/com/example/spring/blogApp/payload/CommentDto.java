package com.example.spring.blogApp.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto{

        private long id;

        @NotEmpty(message = "name should not  be empty.")
        private String name;

        @Email
        @NotEmpty(message = "email should not  be empty.")
        private String email;

        @Size(min = 10,message = "Comment body must be minimum 10 characters.")
        @NotEmpty
        private String body;

}