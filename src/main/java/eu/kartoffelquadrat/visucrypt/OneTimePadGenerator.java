/**
 * Random one time pad generator. Creates 2D one time pads of desired dimensions.
 *
 * @author Maximilian Schiedermeier
 */

package eu.kartoffelquadrat.visucrypt;

import java.security.SecureRandom;

/**
 * Creates 2D one time pads of desired dimensions.
 */
public class OneTimePadGenerator {

  /**
   * Random one time pad generator. Creates 2D one time pads of desired dimensions.
   */
  public static boolean[][] generatePad(int width, int height) {

    boolean[][] result = new boolean[width][height];
    SecureRandom random = new SecureRandom();

    // For each line. create as many random bytes as the line has elements
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        // get a random boolean
        byte[] bytes = new byte[1];
        random.nextBytes(bytes);

        // add it at pads designated position
        result[x][y] = booleanFromByte(bytes[0]);
      }
    }
    return result;
  }

  /**
   * Strips a random byte value to a single bit value by extracting only the first position.
   *
   * @param b as a random input byte.
   * @return a single bit value, which is the first bit of the provided input byte.
   */
  public static boolean booleanFromByte(byte b) {
    return ((b & 0x01) != 0);
  }
}
