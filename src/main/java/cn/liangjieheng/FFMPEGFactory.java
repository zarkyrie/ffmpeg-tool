package cn.liangjieheng;


import cn.liangjieheng.locator.FFMPEGLinuxLocator;
import cn.liangjieheng.locator.FFMPEGMacLocator;
import cn.liangjieheng.enums.OSEnum;

/**
 * 工厂类
 *
 * @author liangjieheng
 * @version 1.0
 * @since 2017/9/1 10:04
 */
public class FFMPEGFactory {

    private static final FFPROBEExecutor FFPROBE;

    static {
        String os = System.getProperty("os.name");
        if (os.toUpperCase().contains(OSEnum.MAC.toString())) {
            FFPROBE = new FFPROBEExecutor(new FFMPEGMacLocator());
        } else if (os.toUpperCase().contains(OSEnum.LINUX.toString())) {
            FFPROBE = new FFPROBEExecutor(new FFMPEGLinuxLocator());
        } else {
            throw new RuntimeException("获取操作系统错误");
        }
    }

    public static FFPROBEExecutor createFFPROBE() {
        return FFPROBE;
    }

}
