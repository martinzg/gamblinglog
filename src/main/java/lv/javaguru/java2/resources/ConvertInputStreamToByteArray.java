package lv.javaguru.java2.resources;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConvertInputStreamToByteArray {

    public static byte[] getBytesFromInputStream(InputStream inputStream) throws IOException {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream())
        {
            byte[] buffer = new byte[0xFFFF];
            for (int len; (len = inputStream.read(buffer)) != -1;)
                os.write(buffer, 0, len);
            os.flush();
            return os.toByteArray();
        }
    }

}
