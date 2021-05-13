package controller;

public class RequestNotValidException extends RuntimeException {

    public RequestNotValidException() {
        super();
    }

    public RequestNotValidException(String msg) {
        super(msg);
    }
}
