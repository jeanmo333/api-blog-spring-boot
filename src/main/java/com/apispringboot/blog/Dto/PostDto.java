package com.apispringboot.blog.Dto;

import com.apispringboot.blog.model.Comment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class PostDto {

    private Long id;

    @NotEmpty
    @Size(min = 2,message = "El titulo de la publicación deberia tener al menos 2 caracteres")
    private String title;

    @NotEmpty
    @Size(min = 10,message = "La descripción de la publicación deberia tener al menos 10 caracteres")
    private String description;

    @NotEmpty
    private String content;

    private Set<Comment> comments;

//constructor
    public PostDto() {
        super();
    }

    public PostDto(Long id, String title, String description, String content, Set<Comment> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.comments = comments;
    }


    //getter setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
