package eu.kartoffelquadrat.visucrypt;

import java.security.SecureRandom;

public class OneTimePadGenerator {
    /**
     * creates a chunk
     */
    public static boolean[][] generatePad(int width, int height) {

        boolean[][] result = new boolean[width][height];
        SecureRandom random = new SecureRandom();

        // for each line. create as many random bytes as the line has elements ToDo: enhance later, one byte can generate multiple bools...
        for(int y = 0; y < height; y++) {
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

    /*
    //converts an array of bytes into a boolean array
    public static boolean[] booleanArrayFromByteArray(byte[] x) {
        boolean[] y = new boolean[x.length * 8];
        int position = 0;
        for (byte z : x) {
            boolean[] temp = booleanArrayFromByte(z);
            System.arraycopy(temp, 0, y, position, 8);
            position += 8;
        }
        return y;
    }*/

    // converts one byte into an 4-sized boolean array
    public static boolean booleanFromByte(byte x) {
        //boolean bs[] = new boolean[1]; // TODO: can be extended -> later, for increased performance.
        return ((x & 0x01) != 0);
        //bs[1] = ((x & 0x02) != 0);
        //bs[2] = ((x & 0x04) != 0);
        //bs[3] = ((x & 0x08) != 0);
        //return bs;
    }
}
