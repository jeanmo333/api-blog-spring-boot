package com.apispringboot.blog.Dto;

import java.util.List;

public class PostResponse {

    private List<PostDto> content;
    private int pageNumber;
    private int itemQuantity;
    private long totalElements;
    private int totalPages;
    private boolean last;


    public PostResponse() {
        super();
    }

    public PostResponse(List<PostDto> content, int pageNumber, int itemQuantity, long totalElements, int totalPages, boolean last) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.itemQuantity = itemQuantity;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }


    public List<PostDto> getContent() {
        return content;
    }

    public void setContent(List<PostDto> content) {
        this.content = content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}
