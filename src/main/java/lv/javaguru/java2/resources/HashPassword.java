package lv.javaguru.java2.resources;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;

public class HashPassword {

    public static String hashPassword (HttpServletRequest req) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] passwordHashed = messageDigest.digest(req.getParameter("password").getBytes("UTF-8"));

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < passwordHashed.length; i++) {
                sb.append(Integer.toHexString((passwordHashed[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
