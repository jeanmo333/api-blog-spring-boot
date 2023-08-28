package com.apispringboot.blog.controller;

import com.apispringboot.blog.Dto.CommentDto;
import com.apispringboot.blog.Dto.PostDto;
import com.apispringboot.blog.service.CommentsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentsService commentsService;

    @GetMapping("/posts/{postId}/comments")
    List<CommentDto> findAllCommentByPostId(
            @PathVariable(value = "postId") Long postId){
        return commentsService.findAllCommentByPostId(postId);

    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
   public ResponseEntity<CommentDto> findCommentById(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "commentId") Long commentId
            ){
        CommentDto commentDto = commentsService.findAllCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK)  ;

    }


    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable(value = "postId") Long postId,
            @Valid
            @RequestBody CommentDto commentDto){

        return new ResponseEntity<>(commentsService.createComment(postId, commentDto), HttpStatus.CREATED);

    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public  ResponseEntity<CommentDto> updateComment(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "commentId") Long commentId,
            @Valid
            @RequestBody CommentDto commentDto
    ){

        CommentDto commentUpdate = commentsService.updateComment(postId, commentId, commentDto);
        return new  ResponseEntity<>(commentUpdate, HttpStatus.OK);
    }


    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId,@PathVariable(value = "commentId") Long commentId){
        commentsService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comentario eliminado con exito",HttpStatus.OK);
    }

}
