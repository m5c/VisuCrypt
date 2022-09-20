/**
 * Interface for various XORer implementations.
 *
 * @author Maximilian Schiedermeier
 */

package eu.kartoffelquadrat.visucrypt;

/**
 * Interface for various XORer implementations.
 */
public interface Xorer {

  /**
   * Creates a new 2D bool array of identical dimensions as provided image or share and one time
   * pad. The outcome is  another 2D boolean array resulting form a logical operation combinaed on
   * every pair of input values at iuneticl position.
   *
   * @param binaryRaster1 bool 2D array encoding an image or share
   * @param binaryRaster2 bool 2D array encoding an image or share
   * @return new 2d bool array representing second visual share.
   */
  boolean[][] combine(boolean[][] binaryRaster1, boolean[][] binaryRaster2);

}
