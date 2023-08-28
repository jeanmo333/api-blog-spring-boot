package com.apispringboot.blog.service;

import com.apispringboot.blog.Dto.PostDto;
import com.apispringboot.blog.Dto.PostResponse;


public interface PostService {


    public PostDto createPost(PostDto postDto);

    public PostResponse getAllPost(int pageNumber, int itemQuantity, String orderBy, String sortDir);

    public PostDto getPostById(long id);

    public PostDto UpdatePost(PostDto postDto, long id);


    public void deletePost(long id);
}
