/**
 * Launcher class for Visual Cryptography implementation. Consumes path to original image and stores
 * to result images in tmp directory.
 *
 * @author Maximilian Schiedermeier
 */

package eu.kartoffelquadrat.visucrypt;

/**
 * Launcher class for Visual Cryptography implementation.
 */
public class Launcher {

  /**
   * Main method consumes path to original image and stores to result images in tmp directory.   *
   *
   * @param args location of input image as string encoding path to resource
   */
  public static void main(String[] args) {

    // load image as binary array
    boolean[][] inputImageBinary = BinaryImageLoader.loadImage(args[0]);
    System.out.println("Image imported. Detected size: " + inputImageBinary.length);

    // create a random one time pad of same size, also as boolean 2D array
    boolean[][] oneTimePad =
        OneTimePadGenerator.generatePad(inputImageBinary.length, inputImageBinary[0].length);
    System.out.println("Pad generated.");

    // create XOR-ed outcome of input_image and one_time_pad
    // Idea: white pixels in original image require identical booleans in both shares. Black pixels
    // require complementary pixels.

    boolean[][] secondShare = BoolArrayXorer.weave(inputImageBinary, oneTimePad);
    System.out.println("Second share created.");

    // convert the compact boolean-shares to double sized visual shares
    boolean[][] visualShare1 = Upscaler.upscale(oneTimePad);
    boolean[][] visualShare2 = Upscaler.upscale(secondShare);

    // Finally export both shares as pngs back to the filesystem, so they can be printed
    VisualShareExporter.exportVisualShare(visualShare1, "/tmp/share1.png");
    VisualShareExporter.exportVisualShare(visualShare2, "/tmp/share2.png");
  }
}
