/**
 * Visual up-scaler implementation. Boosts a provided share to double its dimensions (four times the
 * pixel amount) by boosting every individual pixel cell to double the dimensions.
 *
 * @author Maximilian Schiedermeier
 */

package eu.kartoffelquadrat.visucrypt;

/**
 * Utility class. Offers method that transforms a bool[][] and transforms it into a bigger bool[][]
 * with quadrupled dimensions.
 */
public class Upscaler {

  /**
   * Uses designated diagonal patterns to extend raw entropy false and true values. It can be seen
   * as the transformation from a compact share (not usable for visual cryptography) to a visual
   * share (usable for visual cryptography)
   *
   * <p>False (False) becomes: FT/TF.
   *
   * <p>True (White) becomes TF/FT.
   *
   * @param compactShare as a share in its compressed state i.e. not yet visually perceivable.
   * @return a visual share of double the dimensions where every position is boosted to a pattern.
   */
  public static boolean[][] upscale(boolean[][] compactShare) {
    boolean[][] visualShare = new boolean[compactShare.length * 4][compactShare[0].length * 4];

    // iterate over columns and lines and replace each boolean in the original by the corresponding
    // 2x2 pattern in the target.
    for (int x = 0; x < compactShare.length; x++) {
      for (int y = 0; y < compactShare[0].length; y++) { // each 0 represents a 2x2 block
        // 0X
        // XX
        visualShare[4 * x][4 * y] = compactShare[x][y];
        visualShare[4 * x + 1][4 * y] = compactShare[x][y];
        visualShare[4 * x][4 * y + 1] = compactShare[x][y];
        visualShare[4 * x + 1][4 * y + 1] = compactShare[x][y];

        // X0 -> Inverted
        // XX
        visualShare[4 * x + 2][4 * y] = !compactShare[x][y];
        visualShare[4 * x + 3][4 * y] = !compactShare[x][y];
        visualShare[4 * x + 2][4 * y + 1] = !compactShare[x][y];
        visualShare[4 * x + 3][4 * y + 1] = !compactShare[x][y];

        // XX
        // 0X -> Inverted
        visualShare[4 * x][4 * y + 2] = !compactShare[x][y];
        visualShare[4 * x + 1][4 * y + 2] = !compactShare[x][y];
        visualShare[4 * x][4 * y + 3] = !compactShare[x][y];
        visualShare[4 * x + 1][4 * y + 3] = !compactShare[x][y];

        // XX
        // X0
        visualShare[4 * x + 2][4 * y + 2] = compactShare[x][y];
        visualShare[4 * x + 3][4 * y + 2] = compactShare[x][y];
        visualShare[4 * x + 2][4 * y + 3] = compactShare[x][y];
        visualShare[4 * x + 3][4 * y + 3] = compactShare[x][y];
      }
    }

    return visualShare;
  }
}
