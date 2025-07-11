package com.myapp.board.controller;

import com.myapp.board.dto.ApiResponse;
import com.myapp.board.dto.CreatePostRequest;
import com.myapp.board.dto.PostDto;
import com.myapp.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PostController {
    
    private final PostService postService;
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<PostDto>>> getAllPosts() {
        try {
            List<PostDto> posts = postService.getAllPosts();
            return ResponseEntity.ok(ApiResponse.success(posts));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDto>> getPostById(@PathVariable Long id) {
        try {
            PostDto post = postService.getPostById(id);
            return ResponseEntity.ok(ApiResponse.success(post));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<PostDto>> createPost(@RequestBody CreatePostRequest request, Authentication authentication) {
        try {
            PostDto post = postService.createPost(request, authentication.getName());
            return ResponseEntity.ok(ApiResponse.success(post));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDto>> updatePost(@PathVariable Long id, @RequestBody CreatePostRequest request, Authentication authentication) {
        try {
            PostDto post = postService.updatePost(id, request, authentication.getName());
            return ResponseEntity.ok(ApiResponse.success(post));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deletePost(@PathVariable Long id, Authentication authentication) {
        try {
            postService.deletePost(id, authentication.getName());
            return ResponseEntity.ok(ApiResponse.success("Post deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
    
    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<PostDto>>> getMyPosts(Authentication authentication) {
        try {
            List<PostDto> posts = postService.getPostsByUser(authentication.getName());
            return ResponseEntity.ok(ApiResponse.success(posts));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
} 