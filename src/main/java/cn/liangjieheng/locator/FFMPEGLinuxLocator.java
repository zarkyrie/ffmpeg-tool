package cn.liangjieheng.locator;

/**
 * @author liangjieheng
 * @version 1.0
 * @since 2017/9/5 11:20
 */
public class FFMPEGLinuxLocator extends FFMPEGDefaultLocator implements FFMPEGLocator {

    static {
        FFPROBE_PATH = getPath("/linux/ffprobe");
    }

    @Override
    public String getFFPROBEPath() {
        return FFPROBE_PATH;
    }
}
