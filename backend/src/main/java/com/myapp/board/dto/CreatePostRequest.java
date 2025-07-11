package com.myapp.board.dto;

import lombok.Data;

@Data
public class CreatePostRequest {
    private String title;
    private String content;
} 