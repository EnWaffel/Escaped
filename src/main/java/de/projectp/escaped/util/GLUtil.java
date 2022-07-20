package de.projectp.escaped.util;

import de.projectp.escaped.resource.image.SpriteImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GLUtil {

    public static double _toGLPosition(double d, boolean x) {
        /*
        double result;
        double dividedValue = (d / 640);
        double referenceNum = x ? -1 : 1;
        if (x) result = referenceNum + dividedValue; else result = referenceNum - dividedValue;
        return result;
         */
        return d;
    }

    public static double toGLPosition(double d, boolean x) {
        return d / 1000;
    }

    public static SpriteImage missingTexture(double width, double height) {
        SpriteImage image = new SpriteImage((int) width, (int) height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, image.getWidth() / 2, image.getHeight() / 2);
        g.setColor(new Color(196, 7, 234, 100));
        g.fillRect(0, image.getHeight() / 2, image.getWidth() / 2, image.getHeight() / 2);
        g.setColor(Color.BLACK);
        g.fillRect(image.getWidth() / 2, image.getHeight() / 2, image.getWidth() / 2, image.getHeight() / 2);
        g.setColor(new Color(196, 7, 234, 100));
        g.fillRect(image.getWidth() / 2, 0, image.getWidth() / 2, image.getHeight() / 2);
        return image;
    }

}
