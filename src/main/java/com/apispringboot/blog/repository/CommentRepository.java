package com.apispringboot.blog.repository;

import com.apispringboot.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findByPostId(long postId);
}
