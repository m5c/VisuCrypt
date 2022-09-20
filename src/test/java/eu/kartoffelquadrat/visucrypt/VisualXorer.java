package eu.kartoffelquadrat.visucrypt;

/**
 * Implementation of a visual XORer. The human eye does not a apply an actual XOR on the shares but
 * a NOR. If at least one of the shares at the provided position is false (black) the output will be
 * perceived as black. Only two trues (white) are perceived as true (white).
 */
public class VisualXorer implements Xorer {

  /**
   * Combines two bool rasters into a logical NOR (which is the equivalent of a visual XOR).
   *
   * @param binaryRaster1 a 2D boolean array that represents a share or image.
   * @param binaryRaster2 a 2D boolean array that represents a share or image.
   * @return logical NOR combination of the shares provided.
   */
  @Override
  public boolean[][] combine(boolean[][] binaryRaster1, boolean[][] binaryRaster2) {

    // Prepare output array of same dimensions
    boolean[][] result = new boolean[binaryRaster1.length][binaryRaster2[0].length];

    // Produce second share value for every position by XOR or original image and first share value
    for (int x = 0; x < binaryRaster1.length; x++) {
      for (int y = 0; y < binaryRaster1[x].length; y++) {
        result[x][y] = !(binaryRaster1[x][y] || binaryRaster2[x][y]);
      }
    }

    // Return outcome as second share
    return result;
  }
}
