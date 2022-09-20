/**
 * Standard use case that tries to process a provided 2x2 test image of the test resources folder.
 * Creates two shares and verifies that the superposition of the created shares corresponds to the
 * expected outcome.
 *
 * @author Maximilian Schiedermeier
 */

package eu.kartoffelquadrat.visucrypt;

import org.junit.Test;

public class DummyImageEncryptTest {

  @Test
  public void testUpdateStock() {
    Launcher
        .main(new String[] {"/Users/schieder/Code/VisuCrypt/src/test/resources/test-image.png"});

    // TODO: also verify the outcome images make sense. Parse them again, verify their superposition
    //  bool values.
  }

}