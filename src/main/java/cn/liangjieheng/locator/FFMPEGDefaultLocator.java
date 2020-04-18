package cn.liangjieheng.locator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author liangjieheng
 * @version 1.0
 * @since 2017/9/5 15:54
 */
public abstract class FFMPEGDefaultLocator {

    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir") + "/ffmpeg";

    private static final String SLASH = "/";

    protected static  String FFPROBE_PATH;

    protected static String getPath(String prjResource) {
        String targetPath = TEMP_DIR + SLASH + prjResource.split(SLASH)[2];
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            inputStream = FFMPEGMacLocator.class.getResourceAsStream(prjResource);
            File file = new File(TEMP_DIR);
            if (!file.exists()) {
                file.mkdir();
            }
            File ffmpegFile = new File(targetPath);
            if (!ffmpegFile.exists()) {
                ffmpegFile.createNewFile();
                fileOutputStream = new FileOutputStream(targetPath);
                //复制ffprobe到系统临时目录
                byte[] bytes = new byte[1024];
                int count;
                while ((count = inputStream.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, count);
                }
                //授权
                Runtime.getRuntime().exec("chmod 775 " + targetPath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException("inputStream close error", e);
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException("fileOutputStream close error", e);
                }
            }
        }
        return targetPath;
    }
}
