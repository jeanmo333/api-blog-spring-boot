package com.apispringboot.blog.controller;

import com.apispringboot.blog.Dto.PostDto;
import com.apispringboot.blog.Dto.PostResponse;
import com.apispringboot.blog.model.Post;
import com.apispringboot.blog.service.PostService;
import com.apispringboot.blog.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

   // @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> savePost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }


    @GetMapping
    public PostResponse findAllPost(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_NUMBER_PAGE, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.ITEM_SIZE_DEFAULT, required = false) int itemQuantity,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.ORDER_BY_DEFAULT, required = false) String orderBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.ORDER_DIRECTION_DEFAULT, required = false) String sortDir
    ){
        return  postService.getAllPost(pageNumber, itemQuantity, orderBy, sortDir);
    }


    @GetMapping("/{id}")
    public  ResponseEntity<PostDto> findPostById(@PathVariable(name = "id") long id){
             return  ResponseEntity.ok(postService.getPostById(id));
    }


    @PutMapping("/{id}")
    public  ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto ,@PathVariable(name = "id") long id){

        PostDto postResponse = postService.UpdatePost(postDto, id);
        return new  ResponseEntity<>(postResponse, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("Publicacion eliminada con exito", HttpStatus.OK);
    }

}
