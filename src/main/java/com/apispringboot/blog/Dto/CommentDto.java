package com.apispringboot.blog.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;



public class CommentDto {

    private long id;

    @NotEmpty(message = "El nombre no debe ser vacio o nulo")
    private String name;

    @NotEmpty(message = "El email no debe ser vacio o nulo")
    @Email
    private String email;

    @NotEmpty
    @Size(min = 10,message = "El cuerpo del comentario debe tener al menos 10 caracteres")
    private String body;

    public CommentDto() {
        super();
    }

    public CommentDto(long id, String name, String email, String body) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
