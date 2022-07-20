package de.projectp.escaped.resource.image;

import de.enwaffel.randomutils.file.FileOrPath;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.InputStream;
import java.util.Hashtable;

public class SpriteImage extends BufferedImage implements ImageProvider {

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
        return fromImage(raw(fileOrPath));
    }

    public static SpriteImage fromInputStream(InputStream is) {
        return fromImage(_raw(is));
    }

    public static BufferedImage raw(FileOrPath fileOrPath) {
        try {
            return ImageIO.read(fileOrPath.getFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    }

    public static BufferedImage _raw(InputStream is) {
        try {
            return ImageIO.read(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public String toString() {
        return "SpriteImage@"+Integer.toHexString(hashCode())
                +": type = "+getType()
                +" "+getColorModel()+" "+getRaster();
    }

    @Override
    public SpriteImage getImage() {
        return this;
    }

}
