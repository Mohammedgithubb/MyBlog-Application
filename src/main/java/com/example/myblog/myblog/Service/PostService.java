package com.example.myblog.myblog.Service;

import com.example.myblog.myblog.Dto.PostDto;
import com.example.myblog.myblog.Dto.PostResponse;

public interface PostService {
    

    public PostDto createPost(PostDto postDto);

    public void deletePostById(long id);

    public PostDto getPostById(long id);

    public PostDto updatePost(long id, PostDto postDto);

    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}
