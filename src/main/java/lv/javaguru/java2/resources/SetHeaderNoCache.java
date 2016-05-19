package lv.javaguru.java2.resources;

import javax.servlet.http.HttpServletResponse;

public class SetHeaderNoCache {

    public static void setNoCache (HttpServletResponse response){
        response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
        response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
        response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
        response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
    }

}
