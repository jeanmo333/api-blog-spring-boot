package com.apispringboot.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExceptions extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    private String nameRecurce;
    private String nameColumn;
    private long valueColumn;




    public ResourceNotFoundExceptions (String nameRecurce, String nameColumn, long valueColumn) {
        super(String.format("%s no encontrada con : %s : '%s'", nameRecurce, nameColumn, valueColumn));
        this.nameRecurce = nameRecurce;
        this.nameColumn = nameColumn;
        this.valueColumn = valueColumn;
    }

    public String getNameRecurce() {
        return nameRecurce;
    }

    public void setNameRecurce(String nameRecurce) {
        this.nameRecurce = nameRecurce;
    }

    public String getNameColumn() {
        return nameColumn;
    }

    public void setNameColumn(String nameColumn) {
        this.nameColumn = nameColumn;
    }

    public long getValueColumn() {
        return valueColumn;
    }

    public void setValueColumn(long valueColumn) {
        this.valueColumn = valueColumn;
    }
}
