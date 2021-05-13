package controller;

import org.graalvm.compiler.core.common.Fields;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.Random;

public class FieldsValidator {
    private static Random generator;


    public FieldsValidator(){
        generator = new Random();
    }

    public static boolean validateRequest(HttpServletRequest request){
        RequestValidator validator = new RequestValidator(request);
        Enumeration<String> enumeration = request.getParameterNames();

        while(enumeration.hasMoreElements()){
            validator.isRequired(enumeration.nextElement(), "Error message " + generator.nextInt());
        }

        if(validator.isRequestValid()){
            System.out.println("Richiesta valida");
            return true;
        }else{
            request.setAttribute("errors", validator.getList());
            return false;
        }
    }
}
