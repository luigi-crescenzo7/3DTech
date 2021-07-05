package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RequestNotValidException extends RuntimeException {
    private int errorCode;
    private List<String> errors;

    public RequestNotValidException() {
        super();
    }

    public RequestNotValidException(int errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public RequestNotValidException(List<String> errors, int errorCode) {
        this.errors = errors;
        this.errorCode = errorCode;
    }

    public RequestNotValidException(int errorCode) {
        this.errorCode = errorCode;
    }

    public void dispatchErrors(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (errorCode == HttpServletResponse.SC_BAD_REQUEST) {
            request.setAttribute("alert", getErrors());
        } else {
            response.sendError(errorCode, getErrors().get(0));
        }
    }

    public List<String> getErrors() {
        return errors;
    }
}
