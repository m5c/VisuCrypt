package eu.kartoffelquadrat.visucrypt;

public class BoolArrayXorer {

    public static boolean[][] weave(boolean[][] original, boolean[][] oneTimePad)
    {
        // actually requires a line by line verification of the dimensions match... TODO: add this line by line verification!
        boolean[][] result = new boolean[original.length][original[0].length];

        // ... this is done throughout the fusion process
        for(int x = 0; x < original.length; x++)
        {
            for (int y = 0; y < original[x].length; y++) {
                result[x][y] = original[x][y] ^ oneTimePad[x][y];
            }
        }

        return result;
    }
}
