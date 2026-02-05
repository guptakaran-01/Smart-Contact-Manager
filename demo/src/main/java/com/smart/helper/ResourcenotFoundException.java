package com.smart.helper;

public class ResourcenotFoundException  extends RuntimeException {

    public ResourcenotFoundException(String message){
        super(message);
    }

    public ResourcenotFoundException(){
        super("Resource not found");
    }
}
