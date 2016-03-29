package lv.javaguru.java2.servlet.mvc;

import javax.servlet.http.HttpServletRequest;

public class MVCModel implements MVCController {

    private String jspName;
    private Object data;


    public MVCModel(String jspName, Object data) {
        this.jspName = jspName;
        this.data = data;
    }

    public String getJspName() {
        return jspName;
    }

    public Object getData() {
        return data;
    }

    @Override
    public MVCModel processRequest(HttpServletRequest req) {
        return null;
    }
}
