package cn.liangjieheng;

import cn.liangjieheng.locator.FFMPEGLocator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * 执行命令
 *
 * @author liangjieheng
 * @version 1.0
 * @since 2017/8/31 10:45
 */
public class FFMPEGExecutor {

    private FFMPEGLocator ffmpegLocator;

    private static final String SPACE = " ";

    FFMPEGExecutor(FFMPEGLocator ffmpegLocator) {
        this.ffmpegLocator = ffmpegLocator;
    }

    public String exec(String filePath) {
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String command = ffmpegLocator.getFFPROBEPath() + SPACE + filePath;
            Process process = Runtime.getRuntime().exec(command);
            inputStreamReader = new InputStreamReader(process.getErrorStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                stringBuilder.append(str).append("\n");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeReader(bufferedReader);
            closeReader(inputStreamReader);
        }
        return stringBuilder.toString();
    }

    private static void closeReader(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                reader = null;
                throw new RuntimeException(e);

            }
        }
    }
}
