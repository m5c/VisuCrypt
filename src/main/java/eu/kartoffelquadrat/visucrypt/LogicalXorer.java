/**
 * Applies a provided 2D one-time pad to the 2D boolean representation of an image. Produces a new
 * output 2D array, where every array position carries the XORed outcome of original image and one
 * time pad on the corresponding position.
 *
 * @author Maximilian Schiedermeier
 */

package eu.kartoffelquadrat.visucrypt;


/**
 * Utils class with a single util method that applies the XOR operation on all binary pixel values
 * of a secret image and a provided one time pad.
 */
public class LogicalXorer implements Xorer {

  /**
   * Creates a new 2D bool array of identical dimensions as provided image and one time pad, which
   * serves as second share. The second share is the XOR combination of original image and first
   * share (one time pad).
   *
   * @param original   bool 2D array encoding the original image, full depth black and white mode.
   * @param oneTimePad bool 2D array encoding a one time pad of equal dimensions as original image.
   * @return new 2d bool array representing second visual share.
   */
  public boolean[][] combine(boolean[][] original, boolean[][] oneTimePad) {

    // Prepare output array of same dimensions
    boolean[][] result = new boolean[original.length][original[0].length];

    // Produce second share value for every position by XOR or original image and first share value
    for (int x = 0; x < original.length; x++) {
      for (int y = 0; y < original[x].length; y++) {
        result[x][y] = !original[x][y] ^ oneTimePad[x][y];
      }
    }

    // Return outcome as second share
    return result;
  }
}
