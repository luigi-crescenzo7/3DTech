package controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class RequestValidator {
    private final HttpServletRequest request;
    private final List<String> list;

    public RequestValidator(HttpServletRequest request){
        this.request = request;
        this.list = new ArrayList<>();
    }

    public void isRequired(String name, String errorMessage){
        String parameter = request.getParameter(name);
        if(parameter == null){
            list.add(errorMessage);
        }
    }

    public boolean isRequestValid(){
        return list.isEmpty();
    }

    public List<String> getList(){
        return list;
    }
}