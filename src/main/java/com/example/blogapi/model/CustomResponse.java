package com.example.blogapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse {

    private String id;

    private String message;

    private Object object;


    public CustomResponse(String id,String message) {
        this.message = message;
        this.id = id;
    }

    public CustomResponse(String id,String message, Object object) {
        this.message = message;
        this.id = id;
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setStatus(String id) {
        this.id = id;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "CustomResponse{" +
                "message='" + message + '\'' +
                ", status=" + id +
                ", object=" + object +
                '}';
    }
}
