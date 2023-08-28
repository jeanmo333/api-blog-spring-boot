package com.apispringboot.blog.service;

import com.apispringboot.blog.Dto.PostDto;
import com.apispringboot.blog.Dto.PostResponse;
import com.apispringboot.blog.exception.ResourceNotFoundExceptions;
import com.apispringboot.blog.model.Post;
import com.apispringboot.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


    @Autowired
    private PostRepository postRepository;
    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mappingEntity(postDto);

        Post newPost = postRepository.save(post);

        PostDto postResponse = mappingDTO(newPost);
        return postResponse;
    }

    @Override
    public PostResponse getAllPost(int pageNumber, int itemQuantity, String orderBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(orderBy).ascending():Sort.by(orderBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, itemQuantity, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> postList = posts.getContent();
        List<PostDto> content = postList.stream()
                    .map(post -> mappingDTO(post))
                    .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNumber(posts.getNumber());
        postResponse.setItemQuantity(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return  postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id)
                                  .orElseThrow(()-> new ResourceNotFoundExceptions("Publicacion", "id", id));
        return mappingDTO(post);
    }

    @Override
    public PostDto UpdatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundExceptions("Publicacion", "id", id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post postUpdate = postRepository.save(post);
        return mappingDTO(postUpdate);
    }

    @Override
    public void deletePost(long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundExceptions("Publicacion", "id", id));

        postRepository.delete(post);

    }


    // Convierte entidad a DTO
    private PostDto mappingDTO(Post post) {
       // PostDto postDto = modelMapper.map(post, PostDto.class);
        PostDto postDto = new PostDto();

        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());

        return postDto;
    }

    // Convierte de DTO a Entidad
    private Post mappingEntity(PostDto postDto) {
     //   Post post = modelMapper.map(postDto, Post.class);
        Post post = new Post();

        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return post;
    }
}
