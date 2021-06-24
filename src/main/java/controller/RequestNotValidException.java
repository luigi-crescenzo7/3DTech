package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestNotValidException extends RuntimeException {
    private int errorCode;

    public RequestNotValidException() {
        super();
    }

    public RequestNotValidException(String msg) {
        super(msg);
    }

    public RequestNotValidException(int errorCode) {
        this.errorCode = errorCode;
    }

    public void dispatchErrors(HttpServletRequest request, HttpServletResponse response) {
        /*switch(){
            default
        }*/
    }
}
