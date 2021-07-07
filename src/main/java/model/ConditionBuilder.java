package model;

import model.Condition;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ConditionBuilder {

    public List<Condition> buildConditions(HttpServletRequest request) {
        List<Condition> conditions = new ArrayList<>();
        Enumeration<String> parameters = request.getParameterNames();
        String parameter = null;
        String value = null;

        while (parameters.hasMoreElements()) {
            parameter = parameters.nextElement();
            value = request.getParameter(parameter);

            if (value != null && !value.isBlank()) {

            }
        }

        return conditions;
    }
}
