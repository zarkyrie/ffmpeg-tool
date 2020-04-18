package cn.liangjieheng;


import cn.liangjieheng.locator.FFMPEGLocator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author liangjieheng
 * @version 1.0
 * @since 2017/8/31 10:45
 */
public class FFPROBEExecutor {

    private FFMPEGLocator ffmpegLocator;

    private static final String SPACE = " ";

    public FFPROBEExecutor(FFMPEGLocator ffmpegLocator) {
        this.ffmpegLocator = ffmpegLocator;
    }

    public String exec(String filePath) {
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        Process process = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String command = ffmpegLocator.getFFPROBEPath() + SPACE + filePath;
            process = Runtime.getRuntime().exec(command);
            inputStreamReader = new InputStreamReader(process.getErrorStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                stringBuilder.append(str).append("\n");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException("bufferedReader close error", e);
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    throw new RuntimeException("inputStreamReader close error", e);
                }
            }
            if (process != null) {
                process.destroy();
            }
        }
    }
}
