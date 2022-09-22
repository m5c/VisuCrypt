/**
 * Share exporter that persists visual shares as png images to disk.
 *
 * @author Maximilian Schiedermeier
 */

package eu.kartoffelquadrat.visucrypt;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Helper class that transforms provided visual shares back to png format and persists them on
 * disk.
 */
public class VisualShareExporter {

  /**
   * Transforms provided visual shares back to png format and persists them on disk.
   *
   * @param visualShare as the boosted, graphic version of a compact share.
   * @param outputDir   as the location on file system where the share is stored as png file.
   * @param fileName    as the  name on disk to use for the persisted file.
   */
  public static void exportVisualShare(boolean[][] visualShare, File outputDir, String fileName,
                                       String imageFormat) {

    // set final dimensions
    BufferedImage bufferedImage =
        new BufferedImage(visualShare.length, visualShare[0].length, BufferedImage.TYPE_INT_ARGB);

    // iterate over share ( 2D array ) and set pixel by pixel in target buffered image
    for (int x = 0; x < visualShare.length; x++) {
      for (int y = 0; y < visualShare[0].length; y++) {
        // see: https://www.javamex.com/tutorials/graphics/bufferedimage_setrgb.shtml
        // All channels have same value (all three colours + alpha info)
        int channelValue = visualShare[x][y] ? 255 : 0;
        int level =
            (255 - channelValue << 24) | (channelValue << 16) | (channelValue << 8) | channelValue;
        bufferedImage.setRGB(x, y, level);
      }
    }

    // Finally write Buffered Image to file
    try {
      File outputFile = new File(outputDir.getAbsolutePath() + "/" + fileName + "."
          + imageFormat);
      ImageIO.write(bufferedImage, imageFormat, outputFile);
    } catch (IOException e) {
      System.out.println("Export Failed!");
    }
  }
}

