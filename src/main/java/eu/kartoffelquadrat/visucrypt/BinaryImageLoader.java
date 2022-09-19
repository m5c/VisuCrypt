package eu.kartoffelquadrat.visucrypt;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Loads a file from disk and converts it into a 2D boolean array, where 0s indicate black, 1s indicate white.
 */
public class BinaryImageLoader {

    // Note: Image must be 512x512 px png
    public static boolean[][] loadImage(String imagePath)
    {
        //try to load image
        System.out.println("Trying to import from filesystem: "+imagePath);
        BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
            System.out.println("File could not be loaded. Aborting.");
        }

        // convert content of image into boolean 2D array
        boolean[][] binaryImage = new boolean[img.getWidth()][img.getHeight()];
		for (int i=0; i<img.getHeight(); i++){
			for (int j=0; j<img.getWidth(); j++){
			    // We don't care about colours -> take average of all colour channels as brightness.
				float r = new Color(img.getRGB(j, i)).getRed();
				float g = new Color(img.getRGB(j, i)).getGreen();
				float b = new Color(img.getRGB(j, i)).getBlue();
				int grayScaled = (int)(r+g+b)/3;

				// Optional: do a sobel edge detection first... Or imitate levels of gray with patterns -> leaves room for improvement.
                // Could also be made configurable via UI later

                // Greyscale is in range 0-255, we interpret anything below 128 as black (F), above as white (T)
                binaryImage[j][i] = (grayScaled>=128);
			}
		}
        return binaryImage;
    }
}
