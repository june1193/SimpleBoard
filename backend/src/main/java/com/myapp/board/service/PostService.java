package com.myapp.board.service;

import com.myapp.board.dto.CreatePostRequest;
import com.myapp.board.dto.PostDto;
import com.myapp.board.entity.Post;
import com.myapp.board.entity.User;
import com.myapp.board.repository.PostRepository;
import com.myapp.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    
    public List<PostDto> getAllPosts() {
        return postRepository.findAllWithUser().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        
        return convertToDto(post);
    }
    
    public PostDto createPost(CreatePostRequest request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setUser(user);
        
        Post savedPost = postRepository.save(post);
        return convertToDto(savedPost);
    }
    
    public PostDto updatePost(Long id, CreatePostRequest request, String username) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        
        if (!post.getUser().getUsername().equals(username)) {
            throw new RuntimeException("You can only update your own posts");
        }
        
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        
        Post updatedPost = postRepository.save(post);
        return convertToDto(updatedPost);
    }
    
    public void deletePost(Long id, String username) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        
        if (!post.getUser().getUsername().equals(username)) {
            throw new RuntimeException("You can only delete your own posts");
        }
        
        postRepository.delete(post);
    }
    
    public List<PostDto> getPostsByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return postRepository.findByUserId(user.getId()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    private PostDto convertToDto(Post post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setUsername(post.getUser().getUsername());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());
        return dto;
    }
} 