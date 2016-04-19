package lv.javaguru.java2.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;

@Component
public class htmlSelectParser {
    public ArrayList<Long> parse(HttpServletRequest req, String key) {
        ArrayList<Long> options = new ArrayList<>();
        Enumeration<String> parameters = req.getParameterNames();
        while (parameters.hasMoreElements()) {
            String param = parameters.nextElement();
            if (param.startsWith(key)) {
                options.add(Long.parseLong(param.replaceAll(key, "")));
            }
        }
        return options;
    }
}
