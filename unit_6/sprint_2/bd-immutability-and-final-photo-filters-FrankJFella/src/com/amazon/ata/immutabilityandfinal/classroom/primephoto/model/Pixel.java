package com.amazon.ata.immutabilityandfinal.classroom.primephoto.model;

import java.util.Objects;

/**
 * Represents a single point in an image. Each pixel has a location in the image, and an associated RGB color.
 */
// Make the class immutable
//     adding final to class and member variables
//     performed defensive copy of references in ctor(s)
public final class Pixel {
    private final int x;
    private final int y;
    private final RGB rgb;

    // since RGB is a class, it passed as a reference, so use defensive copy
    public Pixel(int x, int y, RGB rgb) {
        this.x = x;
        this.y = y;
//      this.rgb = rgb;  // replaced by defensive copy
        this.rgb = new RGB(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), rgb.getTransparency());
    }

    public RGB getRGB() {
        return rgb;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, rgb);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Pixel pixel = (Pixel) obj;

        return pixel.x == this.x && pixel.y == this.y &&
           Objects.equals(pixel.rgb, this.rgb);
    }
}
