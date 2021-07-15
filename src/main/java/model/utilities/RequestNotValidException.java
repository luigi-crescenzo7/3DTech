package model.utilities;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RequestNotValidException extends RuntimeException {
    private final int errorCode;
    private List<String> errors;

    public RequestNotValidException(int errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public RequestNotValidException(List<String> errors, int errorCode) {
        this.errors = errors;
        this.errorCode = errorCode;
    }

    public void dispatchErrors(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (errorCode == HttpServletResponse.SC_BAD_REQUEST) {
            request.setAttribute("errorMessages", getErrors());
            request.getRequestDispatcher("/WEB-INF/results/" + request.getAttribute("returnBack")).forward(request, response);
        } else {
            response.sendError(errorCode, super.getMessage());
        }
    }

    public List<String> getErrors() {
        return errors;
    }
}
