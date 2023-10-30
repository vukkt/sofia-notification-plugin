package com.eurodyn.utils;

import lombok.experimental.UtilityClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@UtilityClass
public class ImageUtils {

    public static String minimizeImage(String base64Image, int newWidth) {
        try {
            // Decode the Base64 string to a byte array
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);

            // Load the image from the byte array
            BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(imageBytes));

            // Calculate the new height to maintain aspect ratio
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            int newHeight = (int) ((double) newWidth / originalWidth * originalHeight);

            // Resize the image
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = newImage.createGraphics();
            g2d.drawImage(resizedImage, 0, 0, null);
            g2d.dispose();

            // Encode the resized image to Base64
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(newImage, "jpeg", baos);
            byte[] resizedImageBytes = baos.toByteArray();
            String minimizedBase64Image = Base64.getEncoder().encodeToString(resizedImageBytes);

            return minimizedBase64Image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
