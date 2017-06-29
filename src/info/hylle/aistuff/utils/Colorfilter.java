package info.hylle.aistuff.utils;

import java.awt.image.RGBImageFilter;

/**
 * Created by IntelliJ IDEA.
 * User: Martin Hylleberg
 * Date: 09-10-2009
 * Time: 17:05:06
 * To change this template use File | Settings | File Templates.
 */

public class Colorfilter extends RGBImageFilter {
    boolean red, green, blue;
    public Colorfilter(boolean r, boolean g, boolean b) {
        red = r;
        green = g;
        blue = b;
        canFilterIndexColorModel = true;
    }

    public int filterRGB(int x, int y, int rgb) {
        // Filter the colors
            int r = red ? 0: ((rgb >> 16) & 0xff);
            int g = green ? 0: ((rgb >> 8) & 0xff);
            int b = blue ? 0: ((rgb >> 0) & 0xff);

            // Return the result
            return (rgb & 0xff000000) | (r << 16) | (g << 8) | (b << 0);

//        if (x == -1) {
//        }
//        return rgb & 0xf0f00000;
    }
}
