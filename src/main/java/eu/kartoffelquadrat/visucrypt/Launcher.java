/**
 * Launcher class for Visual Cryptography implementation. Consumes path to original image and stores
 * to result images in tmp directory.
 *
 * @author Maximilian Schiedermeier
 */

package eu.kartoffelquadrat.visucrypt;

import java.io.File;
import java.io.IOException;

/**
 * Launcher class for Visual Cryptography implementation.
 */
public class Launcher {

  // Use pdf or svg for best results.
  // png and jpgs are rasterized formats that lead to dithering artefacts on upsacling and
  // potentially lower quality of prints.
  public static final String OUTPUT_FORMAT = "png";

  /**
   * Main method consumes path to original image and stores to result images in tmp directory.
   *
   * @param args location of input image as string encoding path to resource.
   * @throws IOException in case reading or writing images from disk fails.
   */
  public static void main(String[] args) throws IOException {

    final File targetDir = extractTargetDir(args);

    // load image as binary array
    boolean[][] inputImageBinary = BinaryImageLoader.loadImage(new File(args[0]));
    System.out.println("Image imported. Detected size: " + inputImageBinary.length);

    // create a random one time pad of same size, also as boolean 2D array
    boolean[][] oneTimePad =
        OneTimePadGenerator.generatePad(inputImageBinary.length, inputImageBinary[0].length);
    System.out.println("Pad generated.");

    // create XOR-ed outcome of input_image and one_time_pad
    // Idea: white pixels in original image require identical booleans in both shares. Black pixels
    // require complementary pixels.
    boolean[][] secondShare = new LogicalXorer().combine(inputImageBinary, oneTimePad);
    System.out.println("Second share created.");

    // convert the compact boolean-shares to double sized visual shares
    boolean[][] visualShare1 = Upscaler.upscale(oneTimePad);
    boolean[][] visualShare2 = Upscaler.upscale(secondShare);

    // Finally export both shares as pngs back to the filesystem, so they can be printed
    VisualShareExporter.exportVisualShare(visualShare1, targetDir, "share1", OUTPUT_FORMAT);
    VisualShareExporter.exportVisualShare(visualShare2, targetDir, "share2", OUTPUT_FORMAT);
  }

  /**
   * Helper method to determine where to store target shares. If no second runtime argument was
   * provided, the target shares are stored alongside the original input image. Otherwise the
   * explicitly provided target dir is used.
   *
   * @param args as the user provided runtime arguments.
   * @return file object reference to the target dir for produces shares.
   */
  private static File extractTargetDir(String[] args) {

    // Verify an input image is specified.
    if (args.length == 0) {
      throw new Error("No input image file system location provided as first argument.");
    }

    // Reject any input that provides more arguments than expected
    if (args.length > 2) {
      throw new Error("No input image file system location provided as first argument.");
    }

    // If no second argument was provided, use parent dir of input file as target dir
    File targetDir;
    if (args.length == 1) {
      targetDir = new File(args[0]).getParentFile();
    } else {
      targetDir = new File(args[1]);
    }
    return targetDir;

  }
}
