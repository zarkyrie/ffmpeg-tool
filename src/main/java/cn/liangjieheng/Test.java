package cn.liangjieheng;

/**
 * @author liangjieheng
 * @version 1.0
 * @since 2017/9/1 14:24
 */
public class Test {

    public static void main(String[] args) {
        String result = FFMPEGFactory.createFFPROBE().exec("xxxxx");
        System.out.println(result);
    }
}
