package de.projectp.escaped.resource;

import de.enwaffel.randomutils.file.FileOrPath;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.util.Hashtable;

public class SpriteImage extends BufferedImage {

    private final Point offset;

    public SpriteImage(int width, int height, int imageType) {
        super(width, height, imageType);
        this.offset = new Point();
    }

    public SpriteImage(int width, int height, int imageType, IndexColorModel cm) {
        super(width, height, imageType, cm);
        this.offset = new Point();
    }

    public SpriteImage(ColorModel cm, WritableRaster raster, boolean isRasterPremultiplied, Hashtable<?, ?> properties) {
        super(cm, raster, isRasterPremultiplied, properties);
        this.offset = new Point();
    }

    public SpriteImage(int width, int height, int imageType, Point offset) {
        super(width, height, imageType);
        this.offset = offset;
    }

    public SpriteImage(int width, int height, int imageType, IndexColorModel cm, Point offset) {
        super(width, height, imageType, cm);
        this.offset = offset;
    }

    public SpriteImage(ColorModel cm, WritableRaster raster, boolean isRasterPremultiplied, Hashtable<?, ?> properties, Point offset) {
        super(cm, raster, isRasterPremultiplied, properties);
        this.offset = offset;
    }

    public Point getOffset() {
        return offset;
    }

    public static SpriteImage fromImage(BufferedImage image) {
        ColorModel cm = image.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);
        return new SpriteImage(cm, raster, isAlphaPremultiplied, null);
    }

    public static SpriteImage fromFile(FileOrPath fileOrPath) {
        try {
            return fromImage(ImageIO.read(fileOrPath.getFile()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SpriteImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    }

}
