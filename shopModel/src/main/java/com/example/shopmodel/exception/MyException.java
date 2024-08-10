package com.example.shopmodel.exception;

public class MyException extends RuntimeException{
    private int code;

    public MyException(){

    }
    public MyException(int code,String msg){
        super(msg);
        this.code=code;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
