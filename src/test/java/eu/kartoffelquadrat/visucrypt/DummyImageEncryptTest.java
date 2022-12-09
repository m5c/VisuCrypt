/**
 * Standard use case that tries to process a provided 2x2 test image of the test resources folder.
 * Creates two shares and verifies that the superposition of the created shares corresponds to the
 * expected outcome.
 *
 * @author Maximilian Schiedermeier
 */

package eu.kartoffelquadrat.visucrypt;

import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class DummyImageEncryptTest {

  public static final String TEST_FILE = "src/test/resources/test-image.png";
  public static final String TARGET_DIR = "/tmp/";
  public static final String[] SHARES =
      new String[] {"share1." + Launcher.OUTPUT_FORMAT, "share2." + Launcher.OUTPUT_FORMAT};


  @Test
  public void testEncryptSampleImage() throws IOException {

    // Test creation of shares for sample test image
    Launcher.main(new String[] {TEST_FILE, TARGET_DIR});

    // Verify output images exist in /tmp directory (expected target location)
    File share1File = new File(TARGET_DIR + "share1." + Launcher.OUTPUT_FORMAT);
    File share2File = new File(TARGET_DIR + "share2." + Launcher.OUTPUT_FORMAT);
    Assert.assertTrue("Expected a test share at /tmp/share1." + Launcher.OUTPUT_FORMAT +
            ", but the file does not exist.",
        share1File.exists());
    Assert.assertTrue("Expected a test share at /tmp/share2." + Launcher.OUTPUT_FORMAT+
        ", but the file does not exist.",
        share2File.exists());

    // Verify shares have double dimensions of sample image
    boolean[][] original = BinaryImageLoader.loadImage(new File(TEST_FILE));
    boolean[][] share1 = BinaryImageLoader.loadImage(new File(TARGET_DIR + SHARES[0]));
    boolean[][] share2 = BinaryImageLoader.loadImage(new File(TARGET_DIR + SHARES[1]));
    Assert.assertTrue("Share 1 does not have double dimensions of original image on x. Original: " +
            original.length + " Share 1: " + share1.length,
        original.length * 2 == share1.length);
    Assert.assertTrue("Share 1 does not have double dimensions of original image on y. Original: " +
            original[0].length + " Share 1: " + share1[0].length,
        original[0].length * 2 == share1[0].length);
    Assert.assertTrue("Share 2 does not have double dimensions of original image on x. Original: " +
            original.length + " Share 2: " + share2.length,
        original.length * 2 == share2.length);
    Assert.assertTrue("Share 2 does not have double dimensions of original image on y. Original: " +
            original[0].length + " Share 2: " + share2[0].length,
        original[0].length * 2 == share2[0].length);

    // Create superposition and verify visual XOR corresponds to the expected outcome.
    // Test image has white column left, black column right, so the outcome should be a 4x4 matrix,
    // left two columns should be dithered, right two columns should be solid black.
    boolean[][] visualXor = new VisualXorer().combine(share1, share2);

    // Verify solid black test stripe on two most right columns:
    for (int x : new int[] {2, 3}) {
      for (int y : new int[] {0, 1, 2, 3}) {
        Assert.assertFalse(
            "Visual overlay should be false (black) and black control strip, but at least one cell is true (white): " +
                visualXor[x][y], visualXor[x][y]);
      }
    }

    // Verify dithered pattern on two most left columns:
    // Verify solid black test stripe on two most right columns:
    for (int x : new int[] {0, 1}) {
      int ditherValue =
          boolToInt(visualXor[x][0]) + boolToInt(visualXor[x][1]) + boolToInt(visualXor[x][2]) +
              boolToInt(visualXor[x][3]);
      Assert.assertTrue(
          "Visual overlay should be dithered, but the combined pixels in column " + x +
              " do not add up to as many black as white pixels. Expected: 2, found " + ditherValue,
          ditherValue == 2);
    }

    // finally clean up generated test shares
    share1File.delete();
    share2File.delete();
  }

  /**
   * Helper method to convert bool values to int. False converts to 0, True to 1.
   *
   * @param b as an input boolean value
   * @return 0 or 1, depending on the value provided as input.
   */
  private int boolToInt(boolean b) {
    if (b) {
      return 1;
    } else {
      return 0;
    }
  }
}