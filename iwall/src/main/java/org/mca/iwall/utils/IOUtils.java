package org.mca.iwall.utils;

import java.io.IOException;
import java.io.InputStream;

public class IOUtils {

    public static byte [] toByteArray(InputStream is) {
        try {
            return org.apache.commons.io.IOUtils.toByteArray(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
