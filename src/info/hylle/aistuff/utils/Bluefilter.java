package info.hylle.aistuff.utils;

import java.awt.image.RGBImageFilter;

/**
 * Created by IntelliJ IDEA.
 * User: Martin Hylleberg
 * Date: 09-10-2009
 * Time: 17:05:06
 * To change this template use File | Settings | File Templates.
 */
public class Bluefilter extends RGBImageFilter {
    public Bluefilter() {
        canFilterIndexColorModel = true;
    }

    public int filterRGB(int x, int y, int rgb) {
        if (x == -1) {
        }
        return rgb & 0xf00000f0;
    }
}