/**
 * Helper class for loading of image from file system to 2D binary grid where every pixed is
 * represented by an array position. The image is filtered to a black and white representation and
 * afterwards all greyscale values are reduced to full black or full white. Black is represented by
 * "false", while white is represented by "true" in the result array.
 *
 * @author Maximilian Schiedermeier
 */

package eu.kartoffelquadrat.visucrypt;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Loads a file from disk and converts it into a 2D boolean array, where 0s indicate black, 1s
 * indicate white.
 */
public class BinaryImageLoader {

  /**
   * Helper method to load an image from disk.
   *
   * @param imagePath as file object toward resource location. Can be absolute or relative.
   * @return 2D boolean array representing the images max-contrast grid, all pixels black/white.
   * @throws IOException in case provided image paths cannot be accessed.
   */
  public static boolean[][] loadImage(File imagePath) throws IOException {

    //try to load image
    System.out.println("Trying to import from filesystem: " + imagePath);
    BufferedImage img = ImageIO.read(imagePath);

    // convert content of image into boolean 2D array
    boolean[][] binaryImage = new boolean[img.getWidth()][img.getHeight()];
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {

        // We don't care about colours -> take average of all colour channels as greyscale
        // brightness.
        float r = new Color(img.getRGB(j, i)).getRed();
        float g = new Color(img.getRGB(j, i)).getGreen();
        float b = new Color(img.getRGB(j, i)).getBlue();
        int grayScaled = (int) (r + g + b) / 3;

        // Greyscale is in range 0-255, we interpret anything below 128 as black (F),
        // above as white (T)
        binaryImage[j][i] = (grayScaled >= 128);
      }
    }
    return binaryImage;
  }
}
