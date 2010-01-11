package org.mca.iwall.utils;

import java.io.IOException;
import java.io.InputStream;

public class IOUtils {

    public static byte [] toByteArray(InputStream is) throws IOException {
        return org.apache.commons.io.IOUtils.toByteArray(is);
    }
}
