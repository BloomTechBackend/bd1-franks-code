package com.amazon.ata.immutabilityandfinal.classroom.primephoto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A class representing a PrimePhoto - contains dimensions, and a list of Pixels that make up the image.
 */
// Make the class immutable
//     adding final to class and member variables
//     performed defensive copy of references in ctor(s) and the getter for pixels (ArrayList)
public final class PrimePhoto {
    private final List<Pixel> pixels;
    private final int height;
    private final int width;
    // used when saving to a buffered image
    private final int type;

    // since List is a interface, it passed as a reference, so use defensive copy
    public PrimePhoto(List<Pixel> pixelList, int height, int width, int type) {
        if (pixelList.size() != (height * width)) {
            throw new IllegalArgumentException("Not enough pixels for the dimensions of the image.");
        }
//      this.pixels = pixelList;  // replaced by defensive copy
        this.pixels = new ArrayList<>(pixelList);  // create a new ArrayList with the parameter List
        this.height = height;
        this.width = width;
        this.type = type;
    }
// Since pixels is a reference to an List<>, we should pass a copy of it to user rather than the reference
// Defensive copy of a reference that is returned
    public List<Pixel> getPixels() {
        return new ArrayList<>(pixels);  // Defensive copy of returned reference
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getType() {
        return type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pixels, height, width, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        PrimePhoto photo = (PrimePhoto) obj;

        return photo.height == this.height && photo.width == this.width &&
            photo.type == photo.type && Objects.equals(photo.pixels, this.pixels);
    }

}
