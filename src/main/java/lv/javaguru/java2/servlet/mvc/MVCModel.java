package lv.javaguru.java2.servlet.mvc;

public class MVCModel {

    private String jspName;
    private Object data;
    private String message;


    public MVCModel(String jspName, Object data, String message) {
        this.jspName = jspName;
        this.data = data;
        this.message = message;
    }

    public String getJspName() {
        return jspName;
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

}
