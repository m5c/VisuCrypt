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

  @Test
  public void testUpdateStock() throws IOException {
    final String targetDir = "/tmp";

    Launcher
        .main(new String[] {"/Users/schieder/Code/VisuCrypt/src/test/resources/test-image.png",
            targetDir});

    // Verify output images exist in /tmp directory (expected target location)
    File share1File = new File(targetDir + "/share1.png");
    File share2File = new File(targetDir + "/share2.png");
    Assert.assertTrue("Expected a test share at /tmp/share1.png, but the file does not exist.",
        share1File.exists());
    Assert.assertTrue("Expected a test share at /tmp/share2.png, but the file does not exist.",
        share2File.exists());

    // TODO: Actually inspect produces files to see if they correspond to the expected outcome.

    // finally clean up generated test shares
    share1File.delete();
    share2File.delete();
  }

}