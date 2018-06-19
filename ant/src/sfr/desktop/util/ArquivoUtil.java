package gov.goias.sfr.desktop.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ArquivoUtil {

    public static File copiarInputStreamParaArquivo(final InputStream in, final File file ){
        try {
            final OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
        }
        return file;
    }

}