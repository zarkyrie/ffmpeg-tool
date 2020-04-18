package cn.liangjieheng.locator;

/**
 * @author liangjieheng
 * @version 1.0
 * @since 2017/8/31 10:28
 */
public class FFMPEGMacLocator extends FFMPEGDefaultLocator implements FFMPEGLocator {

    static {
        FFPROBE_PATH = getPath("/mac/ffprobe");
    }

    @Override
    public String getFFPROBEPath() {
        return FFPROBE_PATH;
    }
}
