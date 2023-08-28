package com.apispringboot.blog.service;

import com.apispringboot.blog.Dto.CommentDto;
import com.apispringboot.blog.exception.BlogAppException;
import com.apispringboot.blog.exception.ResourceNotFoundExceptions;
import com.apispringboot.blog.model.Comment;
import com.apispringboot.blog.model.Post;
import com.apispringboot.blog.repository.CommentRepository;
import com.apispringboot.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentsServiceImpl implements  CommentsService{


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mappingEntity(commentDto);

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundExceptions("Publicacion", "id", postId));

        comment.setPost(post);

        Comment newComment = commentRepository.save(comment);

        return mappingDTO(newComment);
    }

    @Override
    public List<CommentDto> findAllCommentByPostId(long postId) {
        List<Comment> comments =commentRepository.findByPostId(postId);
        return comments.stream()
                .map(post -> mappingDTO(post))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto findAllCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundExceptions("Publicacion", "id", postId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundExceptions("Commentario", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw  new BlogAppException(HttpStatus.BAD_REQUEST, "Comentario no pertenece a la publicacion");
         }
        return mappingDTO(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundExceptions("Publicacion", "id", postId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundExceptions("Commentario", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw  new BlogAppException(HttpStatus.BAD_REQUEST, "Comentario no pertenece a la publicacion");
        }

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment commentUpdate = commentRepository.save(comment);

        return mappingDTO(commentUpdate);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundExceptions("Publicacion", "id", postId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundExceptions("Commentario", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw  new BlogAppException(HttpStatus.BAD_REQUEST, "Comentario no pertenece a la publicacion");
        }

           commentRepository.delete(comment);

    }


    // Convierte entidad a DTO
    private CommentDto mappingDTO(Comment comment) {
        CommentDto commentDto = new CommentDto();

        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());

        return commentDto;
    }

    // Convierte de DTO a Entidad
    private Comment mappingEntity(CommentDto commentDto ) {
        Comment comment = new Comment();

        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());


        return comment;
    }
}
