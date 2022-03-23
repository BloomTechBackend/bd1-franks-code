package com.amazon.ata.immutabilityandfinal.classroom.primephoto.converter;

import com.amazon.ata.immutabilityandfinal.classroom.primephoto.model.ConversionType;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.model.Pixel;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.model.PrimePhoto;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.model.RGB;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.util.PrimePhotoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts an image to a grey scale version.
 */
public class GreyscaleConverter implements PrimePhotoConverter {

    public String convert(final PrimePhoto image, final String imageName) {
        List<Pixel> pixels = new ArrayList<>();

        for (Pixel pixel : image.getPixels()) {
            RGB rgb = pixel.getRGB();
            // rgb.toGreyScale();  // original code
            rgb = rgb.toGreyScale(); // new code corrects requirement to assign the conversion to
                                     //     this thread's copy of rgb object
                                     // prior to making rgb immutable there was only one RGB object
                                     //       shared by all threads (that was the problem)
                                     // now each  thread has their RGB object so we need to assign
                                     //     the converted value to the thread's RGB object
                                     // like doing     aString.upperCase();
                                     //    rather than aString = aString.upperCase();
            pixels.add(new Pixel(pixel.getX(), pixel.getY(), rgb));
        }

        PrimePhoto convertedImage = new PrimePhoto(pixels, image.getHeight(), image.getWidth(), image.getType());

        return PrimePhotoUtil.savePrimePhoto(convertedImage, imageName, ConversionType.GREYSCALE);
    }
}
