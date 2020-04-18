package cn.liangjieheng;

import cn.liangjieheng.model.Info;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liangjieheng
 * @version 1.0
 * @since 2017/9/7 09:53
 */
public class FFMPEGMapper {

    /**
     * 获取时长正则规则
     */
    private static final Pattern DURATION_PATTERN = Pattern.compile("^*Duration:(\\d\\d):(\\d\\d):(\\d\\d)\\.(\\d\\d)", Pattern.CASE_INSENSITIVE);
    /**
     * 获取分辨率正则规则
     */
    private static final Pattern VIDEO_PATTERN = Pattern.compile("^*Stream#\\S+Video\\S+,(\\d*)x(\\d*)", Pattern.CASE_INSENSITIVE);

    private static Info analisic(String result) {
        Info info = new Info();
        //提取宽和高
        Matcher m1 = VIDEO_PATTERN.matcher(result);
        if (m1.find()) {
            info.setWidth(Integer.parseInt(m1.group(1)));
            info.setHeight(Integer.parseInt(m1.group(2)));
        } else {
            throw new RuntimeException("");
        }
        Matcher m2 = DURATION_PATTERN.matcher(result);
        if (m2.find()) {
            int hours = Integer.parseInt(m2.group(1));
            int minutes = Integer.parseInt(m2.group(2));
            int seconds = Integer.parseInt(m2.group(3));
            int dec = Integer.parseInt(m2.group(4));
            int duration = (dec * 100) + (seconds * 1000) + (minutes * 60 * 1000)
                    + (hours * 60 * 60 * 1000);
            info.setDuration((double)duration);

        }
        return info;
    }
}
