package eu.kartoffelquadrat.visucrypt;

/**
 * Utility class. Offers method that transforms a bool[][] and transforms it into a booger bool[][] with quadrupled dimensions.
 * Uses deisgnated patterns to extend false and true.
 * It can be seen as the transformation from a compact share (not usable for visual cryptography) to a visual share (usable for visual cryptography)
 *
 * False becomes:
 * FT
 * TF
 *
 * True becomes
 * TF
 * FT
 */
public class Upscaler {

    public static boolean[][] upscale(boolean[][] compactShare)
    {
        boolean[][] visualShare = new boolean[compactShare.length*4][compactShare[0].length*4];

        // iterate over colons and lines and replace each boolean in the original by the corresponding 2x2 pattern in the target.
        for(int x = 0 ; x < compactShare.length; x++)
        {
            for(int y = 0 ; y < compactShare[0].length; y++)
            { // each 0 represents a 2x2 block
                // 0X
                // XX
                visualShare[4*x][4*y] =     compactShare[x][y];
                visualShare[4*x+1][4*y] =   compactShare[x][y];
                visualShare[4*x][4*y+1] =   compactShare[x][y];
                visualShare[4*x+1][4*y+1] = compactShare[x][y];

                // X0 -> Inverted
                // XX
                visualShare[4*x+2][4*y] =   !compactShare[x][y];
                visualShare[4*x+3][4*y] =   !compactShare[x][y];
                visualShare[4*x+2][4*y+1] = !compactShare[x][y];
                visualShare[4*x+3][4*y+1] = !compactShare[x][y];

                // XX
                // 0X -> Inverted
                visualShare[4*x][4*y+2] =   !compactShare[x][y];
                visualShare[4*x+1][4*y+2] = !compactShare[x][y];
                visualShare[4*x][4*y+3] =   !compactShare[x][y];
                visualShare[4*x+1][4*y+3] = !compactShare[x][y];

                // XX
                // X0
                visualShare[4*x+2][4*y+2] = compactShare[x][y];
                visualShare[4*x+3][4*y+2] = compactShare[x][y];
                visualShare[4*x+2][4*y+3] = compactShare[x][y];
                visualShare[4*x+3][4*y+3] = compactShare[x][y];
            }
        }

        return visualShare;
    }
}
