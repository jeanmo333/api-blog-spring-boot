package com.apispringboot.blog.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class BlogAppException extends  RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    private HttpStatus status;
    private String mensage;

    public BlogAppException(HttpStatus status, String mensage) {
        super();
        this.status = status;
        this.mensage = mensage;

    }


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMensage() {
        return mensage;
    }

    public void setMensage(String mensage) {
        this.mensage = mensage;
    }
}
